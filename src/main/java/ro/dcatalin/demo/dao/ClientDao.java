package ro.dcatalin.demo.dao;

import java.util.List;

import ro.dcatalin.demo.data.ClientEntity;

public interface ClientDao {

	public ClientEntity getClientById(Integer clientId);

	public List<ClientEntity> getClientsById(List<Integer> clientIds);

	public Integer getClientId(String clientEmail);

	public List<ClientEntity> getAllClients();

	public void savClient(ClientEntity client);

	public void delClient(Integer clientId);
}
