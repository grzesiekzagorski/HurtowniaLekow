package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Client;
import pl.zagorski.repositories.ClientDao;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientImpl {

    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    @Transactional
    public void edit(Client client) {
        clientDao.edit(client);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findOne(int id) {
        return clientDao.findOne(id);
    }

    @Override
    public List<Client> orderByName() {
        return clientDao.orderByName();
    }

    @Override
    public List<Client> getClientsByName(String name) {
        return clientDao.getClientsByName(name);
    }

    @Override
    public List<Object[]> showAllClients() {
        return clientDao.showAllClients();
    }

    @Override
    public List<Object[]> showClientsByName(String name) {
        return clientDao.showClientsByName(name);
    }

    @Override
    public List<Object[]> showClientById(int id) {
        return clientDao.showClientById(id);
    }
}
