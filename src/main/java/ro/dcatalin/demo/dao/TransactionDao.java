package ro.dcatalin.demo.dao;

import java.util.List;

import ro.dcatalin.demo.data.TransactEntity;

public interface TransactionDao {

	public List<TransactEntity> getTransactionsByList(List<Integer> transactionsList);

	public List<TransactEntity> getAllTransactions();

	public TransactEntity getTransactionById(Integer transactId);

	public List<TransactEntity> getTransactionsByAccountId(Integer acctId);

	public void savTransaction(TransactEntity theTransaction);

	public void delTransaction(Integer transactId);

}
