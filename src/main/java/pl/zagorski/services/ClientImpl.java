package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Client;

import java.util.List;

public interface ClientImpl {
    void save(Client client);
    void edit(Client client);
    List<Client> findAll();
    Client findOne(int id);
    List<Client> orderByName();
    List<Client> getClientsByName(@Param("name") String name);
    List<Object[]> showAllClients();
    List<Object[]> showClientsByName(@Param("name") String name);
    List<Object[]> showClientById(@Param("id") int id);
}
