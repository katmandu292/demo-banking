package ro.dcatalin.demo.service;

import java.util.List;

import ro.dcatalin.demo.data.ClientEntity;

public interface ClientService {

	public List<ClientEntity> getClients();

	public void savClient(ClientEntity theClient);

	public ClientEntity getClientById(Integer clientId);

	public ClientEntity getClient(String clientEmail);

	public void deleteClient(Integer clientId);
}
