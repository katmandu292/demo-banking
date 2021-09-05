package ro.dcatalin.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.dcatalin.demo.data.AccountEntity;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AccountEntity> getAllAccounts() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<AccountEntity> theQuery = 
				currentSession.createNativeQuery("SELECT * FROM tbl_conturi", AccountEntity.class);
		List<AccountEntity> acctList = theQuery.getResultList();
		return acctList;
	}

	@Override
	public AccountEntity getAccountById(Integer accountId) {
		Session currentSession = sessionFactory.getCurrentSession();
		AccountEntity bankAccount = currentSession.get(AccountEntity.class,accountId);
		return bankAccount;
	}

	@Override
	public List<AccountEntity> getAccountsByCustomer(Integer clientId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<AccountEntity> acctQuery = currentSession.createNativeQuery("SELECT * FROM tbl_conturi WHERE ClientId = :clientId", AccountEntity.class);
		acctQuery.setParameter("clientId", clientId);
		return acctQuery.getResultList();
	}

	@Override
	public void savAccount(AccountEntity bankAccount) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(bankAccount);
	}

	@Override
	public void delAccount(Integer accountId) {
		Session currentSession = sessionFactory.getCurrentSession();
		AccountEntity bankAccount = currentSession.get(AccountEntity.class,accountId);
		currentSession.delete(bankAccount);
	}

	@Override
	public void withdraw(Integer accountId, Double amount) {
		Session currentSession = sessionFactory.getCurrentSession();
		AccountEntity bankAccount = currentSession.get(AccountEntity.class,accountId);
		bankAccount.payMoney(amount);
		currentSession.saveOrUpdate(bankAccount);
	}

	@Override
	public void addMoney(Integer accountId, Double amount) {
		Session currentSession = sessionFactory.getCurrentSession();
		AccountEntity bankAccount = currentSession.get(AccountEntity.class,accountId);
		bankAccount.acceptPayment(amount);
		currentSession.saveOrUpdate(bankAccount);
	}

}
