package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("clientDao")
public class ClientRepositoryImpl implements ClientDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Client client) {
        em.persist(client);
        em.flush();
    }

    @Override
    public void edit(Client client) {
        em.merge(client);
        em.flush();
    }

    @Override
    public List<Client> findAll() {
        TypedQuery<Client> q = em.createQuery("Select c from Client c", Client.class);
        return (List<Client>) q.getResultList();
    }

    @Override
    public Client findOne(int id) {
        Client client = em.find(Client.class, id);
        return client;
    }

    @Override
    public List<Client> orderByName() {
        TypedQuery<Client> query = em.createNamedQuery("orderByName", Client.class);
        return query.getResultList();
    }

    @Override
    public Client getClientByName(String name) {
        TypedQuery<Client> q = em.createQuery("Select c from Client c where c.name = :name", Client.class);
        return q.setParameter("name", name).getSingleResult();
    }
}
