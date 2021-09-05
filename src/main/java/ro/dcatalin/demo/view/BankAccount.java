package ro.dcatalin.demo.view;

import java.util.ArrayList;
import java.util.List;

import ro.dcatalin.demo.data.AccountEntity;
import ro.dcatalin.demo.data.ClientEntity;
import ro.dcatalin.demo.data.TransactEntity;

public class BankAccount {

	private AccountEntity accountWrapper;

	private Integer accountId;

	private String accountIban;

	private String bankName;

	private ClientEntity acctOwner;

	private String ownerName;

	private Double finalSum;

	private Integer statusId;

	private String acctStatus;

	private List<TransactEntity> transactionList;

	public BankAccount() {
		super();
		this.transactionList = new ArrayList<TransactEntity>();
	}

	public BankAccount(AccountEntity accountEntity, ClientEntity acctOwner) {
		super();
		this.transactionList = new ArrayList<TransactEntity>();
		setAccountWrapper(accountEntity);
		setAcctOwner(acctOwner);
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountIban() {
		return accountIban;
	}

	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public ClientEntity getAcctOwner() {
		return acctOwner;
	}

	public void setAcctOwner(ClientEntity acctOwner) {
		this.acctOwner = acctOwner;
		StringBuilder x = new StringBuilder();
		x.append(acctOwner.getClientGivenName());
		x.append(" ");
		x.append(acctOwner.getClientFamilyName());
		setOwnerName(x.toString());
	}

	public Double getFinalSum() {
		return finalSum;
	}

	public void setFinalSum(Double finalSum) {
		this.finalSum = finalSum;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public AccountEntity getAccountWrapper() {
		return accountWrapper;
	}

	public void setAccountWrapper(AccountEntity accountWrapper) {
		this.accountWrapper = accountWrapper;
		setAccountId(accountWrapper.getAccountId());
		setAccountIban(accountWrapper.getAccountIban());
		setBankName(accountWrapper.getBankName());
		setFinalSum(accountWrapper.getFinalSum());
		setStatusId(accountWrapper.getStatusId());
	}

	public String getAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public List<TransactEntity> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactEntity> transactionList) {
		this.transactionList = transactionList;
	}

	public void addTransaction(TransactEntity theTransaction) {

		this.transactionList.add(theTransaction);
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
		switch (statusId) {
		   case 1:
			   setAcctStatus("activated");
			   break;
		   case 2:
			   setAcctStatus("in approval");
			   break;
		   default:
			   setAcctStatus("in approval");
		}
	}

	@Override
	public String toString() {
		return "BankAccount [ " + accountId + ", " + accountIban + ", " + bankName
				+ ", " + ownerName
				+ ", Sum: " + finalSum + ", " + acctStatus + "]";
	}

}
