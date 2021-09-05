package ro.dcatalin.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.dcatalin.demo.dao.ClientDao;
import ro.dcatalin.demo.data.ClientEntity;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ClientEntity getClientById(Integer clientId) {
		Session currentSession = sessionFactory.getCurrentSession();
		ClientEntity theClient = currentSession.get(ClientEntity.class,clientId);
		return theClient;
	}

	@Override
	public Integer getClientId(String clientEmail) {
		Session currentSession = sessionFactory.getCurrentSession();
		Integer clientId = 0;
		Query<ClientEntity> theQuery = 
				currentSession.createNativeQuery("SELECT * FROM tbl_client", ClientEntity.class);
		List<ClientEntity> clientList = theQuery.getResultList();
		for (ClientEntity crtEnt : clientList) {
			if (clientEmail.equals(crtEnt.getClientEmail())) {
				clientId = crtEnt.getClientId();
			}
		}

//		Query<Integer> askForID =
//				currentSession.createNativeQuery("SELECT ClientId FROM tbl_client WHERE Email = :clientMail",Integer.class);
//		askForID.setParameter("clientMail", clientEmail);
//		clientId = askForID.getSingleResult();
		return clientId;
	}

	@Override
	public List<ClientEntity> getAllClients() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<ClientEntity> theQuery = 
				currentSession.createNativeQuery("SELECT * FROM tbl_client", ClientEntity.class);
		List<ClientEntity> clientList = theQuery.getResultList();
		return clientList;
	}

	@Override
	public void savClient(ClientEntity client) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(client);
	}

	@Override
	public void delClient(Integer clientId) {
		Session currentSession = sessionFactory.getCurrentSession();
		ClientEntity theClient = currentSession.get(ClientEntity.class,clientId);
		currentSession.delete(theClient);
	}

	@Override
	public List<ClientEntity> getClientsById(List<Integer> clientIds) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<ClientEntity> theQuery = currentSession.createQuery("SELECT ClientId FROM tbl_client WHERE ClientId IN :clientId",ClientEntity.class);
		theQuery.setParameter("clientId",clientIds);
		return theQuery.getResultList();
	}

}
