package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Client;

import java.util.List;

public interface ClientDao {
    void save(Client client);
    void edit(Client client);
    List<Client> findAll();
    Client findOne(int id);
    List<Client> orderByName();
    Client getClientByName(@Param("name")String name);
}
