package ro.dcatalin.demo.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ro.dcatalin.demo.data.AccountEntity;
import ro.dcatalin.demo.data.ClientEntity;
import ro.dcatalin.demo.data.TransactEntity;

public class BankClient {

	private Integer clientId;

	private String clientGivenName;

	private String clientFamilyName;

	private String clientFullName;

	private String clientUserName;

	private Integer accountId;

	private Double accountSum;

	private List<AccountEntity> acctsList;

	private List<TransactEntity> transactionList;

	private ClientEntity clientWrapper;

	public BankClient() {
		this.acctsList = new ArrayList<AccountEntity>();
		this.transactionList = new ArrayList<TransactEntity>();
	}

	public BankClient(ClientEntity clientWrapper) {
		super();
		this.clientWrapper = clientWrapper;
		setClientWrapper(clientWrapper);
		this.acctsList = new ArrayList<AccountEntity>();
		this.transactionList = new ArrayList<TransactEntity>();
	}

	public BankClient(List<AccountEntity> acctsList, ClientEntity clientWrapper) {
		super();
		this.transactionList = new ArrayList<TransactEntity>();
		this.acctsList = acctsList;
		this.clientWrapper = clientWrapper;
		setClientWrapper(clientWrapper);
	}

	public Integer getClientId() {
		return clientId;
	}

	public String getClientGivenName() {
		return clientGivenName;
	}

	public String getClientFamilyName() {
		return clientFamilyName;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public String getClientUserName() {
		return clientUserName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getAccountId(String accountIban) {

		Integer thisIsTheAccount = null;

		for (AccountEntity theAcct : acctsList) {
			if (accountIban.equalsIgnoreCase(theAcct.getAccountIban())) {
				thisIsTheAccount = theAcct.getAccountId();
			}
		}
		return thisIsTheAccount;
	}

	public Double getAccountSum() {
		return accountSum;
	}

	public List<AccountEntity> getAcctsList() {
		return acctsList;
	}

	public List<TransactEntity> getTransactionList() {
		return transactionList;
	}

	public TransactEntity getTransaction() {
		 int listSize = this.transactionList.size();

		 if (listSize == 0) {
			 return new TransactEntity();
		 }

		 return this.transactionList.get(0);
	}

	public ClientEntity getClientWrapper() {
		return clientWrapper;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public void setClientGivenName(String clientGivenName) {
		this.clientGivenName = clientGivenName;
	}

	public void setClientFamilyName(String clientFamilyName) {
		this.clientFamilyName = clientFamilyName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	public void setAccountSum(Double accountSum) {
		this.accountSum = accountSum;
	}

	public void addTransaction(TransactEntity theTransaction) {
		this.transactionList.add(theTransaction);
	}

	public void setTransactionList(List<TransactEntity> transactionList) {
		this.transactionList = transactionList;
	}

//	public void setTransaction(TransactEntity theTransaction) {
//		Integer theSource = theTransaction.getAcctSourceId();
//		Integer theTarget = theTransaction.getAcctTargetId();
//		String theTimeString = theTransaction.getDatTranzact().toString();
//
//		for (TransactEntity crtTransact : this.transactionList) {
//
//			if (!theTransaction.getDatTranzact().toString().equals(crtTransact.getDatTranzact().toString())) {
//				addTransaction(theTransaction);
//			}
//
//			else if (!theTransaction.getAcctSourceId().equals(crtTransact.getAcctSourceId())) {
//				addTransaction(theTransaction);
//			}
//
//			else if (!theTransaction.getAcctTargetId().equals(crtTransact.getAcctTargetId())) {
//				addTransaction(theTransaction);
//			}
//		}
//	}

	public void setAcctsList(List<AccountEntity> acctsList) {
		this.acctsList = acctsList;
		AccountEntity thisAccount = acctsList.get(0);
		setAccountId(thisAccount.getAccountId());
		setAccountSum(thisAccount.getFinalSum());
	}

	public void addAcctToList(AccountEntity theAccount) {
		this.acctsList.add(theAccount);
	}

	public void setClientWrapper(ClientEntity clientWrapper) {
		this.clientWrapper = clientWrapper;
		Integer clIdent = clientWrapper.getClientId();
		StringBuilder fullClientName = new StringBuilder();
		fullClientName.append(clientWrapper.getClientGivenName());
		fullClientName.append(" ");
		fullClientName.append(clientWrapper.getClientFamilyName());
		setClientId(clientWrapper.getClientId());
		setClientFamilyName(clientWrapper.getClientFamilyName());
		setClientUserName(clientWrapper.getClientEmail());
		setClientGivenName(clientWrapper.getClientGivenName());
		setClientFullName(fullClientName.toString());
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {

		StringBuilder acctsText = new StringBuilder();
		StringBuilder transctsText = new StringBuilder();

		for (AccountEntity crtAccount : this.acctsList) {
			acctsText.append(crtAccount.toString());
			acctsText.append(",");
		}

		for (TransactEntity crtTransaction : this.transactionList) {
			transctsText.append(crtTransaction);
			acctsText.append(",");
		}

		return "BankClient [clientId " + clientId + ", " + clientFullName
				+ ", acctsList: " + acctsText.toString() + "," + "Transactions: "
				+ transctsText + clientUserName + "]";
	}

	
}
