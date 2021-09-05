package ro.dcatalin.demo.service;

import java.util.List;

import ro.dcatalin.demo.data.TransactEntity;

public interface TransactionsService {

	public List<TransactEntity> getTransactionsByAccountId(Integer accountId);

	public List<TransactEntity> getTransactionsByList(List<Integer> listTransavIds);

	public TransactEntity getTransactionById(Integer transactId);

	public void savTransaction(TransactEntity theTransaction);

	public void delTransaction(Integer transactId);

}
