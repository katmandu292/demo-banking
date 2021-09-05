package ro.dcatalin.demo.service;

import java.util.List;
import ro.dcatalin.demo.data.AccountEntity;

public interface BankAcctService {

	public List<AccountEntity> getAllAccounts();

	public List<AccountEntity> getAllClientAccounts(Integer clientId);

	public AccountEntity getBankAcct(Integer acctId);

	public void persistAccount(AccountEntity bankAcct);

	public void deleteBankAccount(Integer bankAcct);

	public Double withdrawMoney(Integer acctId, Double amount);

	public Double addMoney(Integer acctId, Double amount);

	public Integer getMaxAcctId();
}
