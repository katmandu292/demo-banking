package ro.dcatalin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.dcatalin.demo.dao.TransactionDao;
import ro.dcatalin.demo.data.TransactEntity;

@Service
public class TransactServiceImpl implements TransactionsService {

	@Autowired
	private TransactionDao transcationsRepo;

	@Override
	@Transactional
	public List<TransactEntity> getTransactionsByAccountId(Integer accountId) {
		return transcationsRepo.getTransactionsByAccountId(accountId);
	}

	@Override
	@Transactional
	public List<TransactEntity> getTransactionsByList(List<Integer> listTransactIds) {
		return transcationsRepo.getTransactionsByList(listTransactIds);
	}

	@Override
	@Transactional
	public TransactEntity getTransactionById(Integer transactId) {
		return transcationsRepo.getTransactionById(transactId);
	}

	@Override
	@Transactional
	public void savTransaction(TransactEntity theTransaction) {
		transcationsRepo.savTransaction(theTransaction);

	}

	@Override
	@Transactional
	public void delTransaction(Integer transactId) {
		transcationsRepo.delTransaction(transactId);
	}

}
