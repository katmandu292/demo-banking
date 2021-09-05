package ro.dcatalin.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
//port java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//port org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//port org.springframework.web.bind.annotation.RequestParam;
//port org.springframework.security.core.userdetails.User;

import ro.dcatalin.demo.data.AccountEntity;
import ro.dcatalin.demo.data.ClientEntity;
import ro.dcatalin.demo.data.TransactEntity;
import ro.dcatalin.demo.service.BankAcctService;
import ro.dcatalin.demo.service.ClientService;
import ro.dcatalin.demo.service.TransactionsService;
import ro.dcatalin.demo.view.BankAccount;
import ro.dcatalin.demo.view.BankClient;

@Controller
@RequestMapping("/bank")
public class NbrTwoController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private ClientService clientSvc;

	@Autowired
	private TransactionsService transactsSvc;

	@Autowired
	private BankAcctService accountsSvc;

	@RequestMapping(value = "/showTransacts", method = RequestMethod.GET)
	public String listTransactions(Model showTransacts) {

		List<TransactEntity> allRequiredTransactions = new ArrayList<TransactEntity>();
		ArrayList<String> grantedAuthorisations = getGrantedAuthorities();
		String userAuthName = getAuthenticatedUser();
		ClientEntity bankUser = clientSvc.getClient(userAuthName);
		List<AccountEntity> userAcctsList = accountsSvc.getAllClientAccounts(bankUser.getClientId());
		boolean isAuthorizedToCheck = grantedAuthorisations.contains("ROLE_CLIENT");

		if (!isAuthorizedToCheck) {
			return "access-denied";
		}

		for (AccountEntity crtAcct : userAcctsList) {
			List<TransactEntity> userTransactions = transactsSvc.getTransactionsByAccountId(crtAcct.getAccountId());
			for (TransactEntity crtTransaction : userTransactions) {
				allRequiredTransactions.add(crtTransaction);
			}
		}

		showTransacts.addAttribute("userName", userAuthName);
		showTransacts.addAttribute("transactsList",allRequiredTransactions);
		
		return "trans-page";
	}

	@RequestMapping(value = "/payMoney", method = RequestMethod.GET)
	public String getMoney(Model showClientModel) {

		LocalDateTime systemDate = LocalDateTime.now();
		String userAuthName = getAuthenticatedUser();
		ArrayList<String> grantedAuthorisations = getGrantedAuthorities();
		ClientEntity bankUser = clientSvc.getClient(userAuthName);
		TransactEntity theTransaction = new TransactEntity();
		theTransaction.setDatTranzact(systemDate);
		List<AccountEntity> userAcctsList = accountsSvc.getAllClientAccounts(bankUser.getClientId());
		BankClient selfProfile = new BankClient(userAcctsList,bankUser);
		selfProfile.addTransaction(theTransaction);
		HashMap<Integer, String> allAccountsIds = new HashMap<Integer, String>();
		HashMap<Integer, Double> allAccountsSums = new HashMap<Integer, Double>();

		boolean isAuthorizedToCash = grantedAuthorisations.contains("ROLE_CLIENT");
		String messg = "Client Authorization: " + isAuthorizedToCash;

		if (isAuthorizedToCash) {
			userAcctsList = accountsSvc.getAllClientAccounts(bankUser.getClientId());
		} else {
			logger.info(messg);
			return "access-denied";
		}

		for (AccountEntity acct : userAcctsList) {
			Integer accountId = acct.getAccountId();
			selfProfile.setAccountId(accountId);
			String accountIban = acct.getAccountIban();
			Double sumAmount = acct.getFinalSum();
			allAccountsIds.put(accountId,accountIban);
			allAccountsSums.put(accountId,sumAmount);
		}
		showClientModel.addAttribute("client",selfProfile);
		showClientModel.addAttribute("bankAccountsList",allAccountsIds);
		showClientModel.addAttribute("bankAccountsSums",allAccountsSums);
		showClientModel.addAttribute("message",messg);

		logger.info(messg);
		return "bring-page";
	}

	@RequestMapping(value = "/showAccounts", method = RequestMethod.GET)
	public String showClient(Model showClientModel) {

		ArrayList<String> grantedAuthorisations = getGrantedAuthorities();
		List<AccountEntity> userAcctsList = new ArrayList<AccountEntity>();
		List<BankAccount> accountsList = new ArrayList<BankAccount>();
		String userAuthName = getAuthenticatedUser();
		ClientEntity bankUser = clientSvc.getClient(userAuthName);

		boolean isAuthorizedToCheck = grantedAuthorisations.contains("ROLE_EMPLOYEE");
		String messg = "Employee Authorization: " + isAuthorizedToCheck;
		logger.info(messg);

		showClientModel.addAttribute("userName", userAuthName);

		if (isAuthorizedToCheck) {
			userAcctsList = accountsSvc.getAllAccounts();
		} else {
			userAcctsList = accountsSvc.getAllClientAccounts(bankUser.getClientId());
		}

		for (AccountEntity acct : userAcctsList) {
			ClientEntity clientEnt = clientSvc.getClientById(acct.getClientId());
			accountsList.add(new BankAccount(acct,clientEnt));
		}

		showClientModel.addAttribute("bankAccountsList",accountsList);
		showClientModel.addAttribute("message",messg);
		return "account-page";
	}

	@RequestMapping(value = "/manageAccts", method = RequestMethod.GET)
	public String showAccount(Model showClientModel) {

		List<AccountEntity> userAcctsList = new ArrayList<AccountEntity>();
		List<BankAccount> accountsList = new ArrayList<BankAccount>();
		ArrayList<String> grantedAuthorisations = getGrantedAuthorities();
		String userAuthName = getAuthenticatedUser();
		ClientEntity bankUser = clientSvc.getClient(userAuthName);
		showClientModel.addAttribute("userName", userAuthName);
		AccountEntity theNewAccount = new AccountEntity();
		showClientModel.addAttribute("new_account",theNewAccount);

		boolean isAuthorizedToCheck = grantedAuthorisations.contains("ROLE_CLIENT");
		String messg = "Client Authorization: " + isAuthorizedToCheck;
		logger.info(messg);

		userAcctsList = accountsSvc.getAllClientAccounts(bankUser.getClientId());

		for (AccountEntity acct : userAcctsList) {
			ClientEntity clientEnt = clientSvc.getClientById(acct.getClientId());
			accountsList.add(new BankAccount(acct,clientEnt));
		}

		BankClient selfProfile = new BankClient(userAcctsList,bankUser);
		showClientModel.addAttribute("client",selfProfile);
		showClientModel.addAttribute("bankAccountsList",accountsList);
		showClientModel.addAttribute("message", messg);
		return "self-page";
	}

	@RequestMapping(value = "/sendNewAcctCreation", method = RequestMethod.POST)
	public String newAccountRequest(@ModelAttribute("new_account") AccountEntity theBankAccount, Model newAcct) {

		String userAuthName = getAuthenticatedUser();
		ClientEntity bankUser = clientSvc.getClient(userAuthName);
		newAcct.addAttribute("userName", userAuthName);
		List<AccountEntity> userAcctsList = accountsSvc.getAllAccounts();
		boolean isNewIban = true;
		String messg = null;
		String newIBan = theBankAccount.getAccountIban();
		AccountEntity theNewAccount = new AccountEntity();

		logger.info("User " + bankUser.getClientId() + " submitted new Account: " + newIBan + " creation");

		for (AccountEntity crtAcct : userAcctsList) {
			if (crtAcct.getAccountIban().equals(newIBan)) {
				isNewIban = false;
			}
		}

		if (isNewIban) {
//			Integer maxId = accountsSvc.getMaxAcctId();
//			Integer newId = maxId + 21;
//			theNewAccount.setAccountId(newId);
			theNewAccount.setAccountIban(newIBan);
			theNewAccount.setBankName("INGB");
			theNewAccount.setClientId(bankUser.getClientId());
			theNewAccount.setFinalSum(0.0);
			theNewAccount.setStatusId(3);
			messg = "New account creation with IBAN " + theNewAccount.toString() + " was successfully registered";
			accountsSvc.persistAccount(theNewAccount);
		} else {
			messg = "User " + bankUser.getClientId() + " cannot use value " + newIBan + " for new account request";
		}
		
		logger.info(messg);
		newAcct.addAttribute("message", messg);
		
		return "self-page";
	}

	@RequestMapping(value = "/acceptPayment", method = RequestMethod.POST)
	public String registerPayment(@ModelAttribute("client") BankClient theClient) {
		LocalDateTime systemDate = LocalDateTime.now();
		String userAuthName = getAuthenticatedUser();
		TransactEntity theTransaction = new TransactEntity();
		Integer theClientId = theClient.getClientId();
		Integer theAccountId = theClient.getAccountId();
		AccountEntity theAccount = accountsSvc.getBankAcct(theAccountId);
		Double initialSum = theAccount.getFinalSum();
		theTransaction.setAcctTargetId(theAccountId);
		theTransaction.setMovedAmount(theClient.getTransaction().getMovedAmount());
		theTransaction.setDatTranzact(systemDate);
		ClientEntity bankUser = clientSvc.getClient(userAuthName);

		if (bankUser.getClientId() != theClientId) {
			logger.info("CrossCheck WARNING: attempt of takeover on behalf of customer " + theClientId);
		}
		
		Double theAddedSum = theClient.getTransaction().getMovedAmount();

		return "redirect:/bank/manageAccts";
		
	}

	private String getAuthenticatedUser() {
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	private ArrayList<String> getGrantedAuthorities() {
		ArrayList<String> grantedAuthorisations = new ArrayList<String>();
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> userAuthorizations = (Collection<GrantedAuthority>) auth.getAuthorities();

		for (GrantedAuthority crtAuthrt : userAuthorizations) {
			grantedAuthorisations.add(crtAuthrt.toString());
		}
		return grantedAuthorisations;
	}
}
