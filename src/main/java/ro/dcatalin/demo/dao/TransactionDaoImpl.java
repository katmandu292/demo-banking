package ro.dcatalin.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.dcatalin.demo.data.TransactEntity;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<TransactEntity> getTransactionsByList(List<Integer> transactionsList) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<TransactEntity> theQuery = 
				currentSession.createNativeQuery("SELECT * FROM tbl_tranzactii WHERE TranzactId IN (:transList)", TransactEntity.class);
		theQuery.setParameter("transList",transactionsList);
		return theQuery.getResultList();
	}

	@Override
	public List<TransactEntity> getAllTransactions() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<TransactEntity> theQuery = 
				currentSession.createNativeQuery("SELECT * FROM tbl_tranzactii", TransactEntity.class);
		return theQuery.getResultList();
	}

	@Override
	public TransactEntity getTransactionById(Integer transactId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(TransactEntity.class, transactId);
	}

	@Override
	public List<TransactEntity> getTransactionsByAccountId(Integer acctId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<TransactEntity> theQuery = 
				currentSession.createNativeQuery("SELECT * FROM tbl_tranzactii WHERE AcctSrcId = :accountId OR AcctTrgId = :accountId", TransactEntity.class);
		theQuery.setParameter("accountId",acctId);
		return theQuery.getResultList();
	}

	@Override
	public void savTransaction(TransactEntity theTransaction) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theTransaction);
	}

	@Override
	public void delTransaction(Integer transactId) {
		Session currentSession = sessionFactory.getCurrentSession();
		TransactEntity targetedTransaction = currentSession.get(TransactEntity.class, transactId);
		currentSession.delete(targetedTransaction);
	}

}
