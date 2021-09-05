package ro.dcatalin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.dcatalin.demo.dao.ClientDao;
import ro.dcatalin.demo.data.ClientEntity;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientRepository;

	@Override
	@Transactional
	public List<ClientEntity> getClients() {
		return clientRepository.getAllClients();
	}

	@Override
	@Transactional
	public void savClient(ClientEntity theClient) {
		clientRepository.savClient(theClient);
	}

	@Override
	@Transactional
	public ClientEntity getClientById(Integer clientId) {
		return clientRepository.getClientById(clientId);
	}

	@Override
	@Transactional
	public ClientEntity getClient(String clientEmail) {
		Integer clientId = clientRepository.getClientId(clientEmail);
		return clientRepository.getClientById(clientId);
	}

	@Override
	@Transactional
	public void deleteClient(Integer clientId) {
		clientRepository.delClient(clientId);
	}

}
