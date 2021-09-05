package ro.dcatalin.demo.dao;

import java.util.List;

import ro.dcatalin.demo.data.AccountEntity;

public interface AccountDao {

	public List<AccountEntity> getAllAccounts();

	public AccountEntity getAccountById (Integer accountId);

	public List<AccountEntity> getAccountsByCustomer(Integer clientId);

	public void savAccount(AccountEntity account);

	public void delAccount(Integer accountId);

	public void withdraw(Integer accountId, Double amount);

	public void addMoney(Integer accountId, Double amount); 
}
