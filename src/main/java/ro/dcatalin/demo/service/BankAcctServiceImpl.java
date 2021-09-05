package ro.dcatalin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.dcatalin.demo.dao.AccountDao;
import ro.dcatalin.demo.data.AccountEntity;

@Service
public class BankAcctServiceImpl implements BankAcctService {

	@Autowired
	private AccountDao bankAcctRepo;

	@Override
	@Transactional
	public List<AccountEntity> getAllAccounts() {
		return bankAcctRepo.getAllAccounts();
	}

	@Override
	@Transactional
	public List<AccountEntity> getAllClientAccounts(Integer clientId) {
		return bankAcctRepo.getAccountsByCustomer(clientId);
	}

	@Override
	@Transactional
	public AccountEntity getBankAcct(Integer accountId) {
		return bankAcctRepo.getAccountById(accountId);
	}

	@Override
	@Transactional
	public void persistAccount(AccountEntity bankAcct) {
		bankAcctRepo.savAccount(bankAcct);

	}

	@Override
	@Transactional
	public void deleteBankAccount(Integer accountId) {
		bankAcctRepo.delAccount(accountId);
	}

	@Override
	@Transactional
	public Double withdrawMoney(Integer acctId, Double amount) {
		AccountEntity bankAcct = bankAcctRepo.getAccountById(acctId);
		Double crtBalance = bankAcct.getFinalSum();
		bankAcct.setFinalSum(crtBalance - amount);
		bankAcctRepo.savAccount(bankAcct);
		return bankAcct.getFinalSum();
	}

	@Override
	@Transactional
	public Double addMoney(Integer acctId, Double amount) {
		AccountEntity bankAcct = bankAcctRepo.getAccountById(acctId);
		Double crtBalance = bankAcct.getFinalSum();
		bankAcct.setFinalSum(crtBalance + amount);
		bankAcctRepo.savAccount(bankAcct);
		return bankAcct.getFinalSum();
	}

	@Override
	@Transactional
	public Integer getMaxAcctId() {
		List<AccountEntity> allCcounts = bankAcctRepo.getAllAccounts();
		Integer maxId = 0;
		for (AccountEntity crtAcct : allCcounts) {
			if (crtAcct.getAccountId() > maxId) {
				maxId = crtAcct.getAccountId();
			}
		}
		return maxId;
	}

}
