package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Client;
import pl.zagorski.domain.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("ClientDao")
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
        return q.getResultList();
    }

    @Override
    public Client findOne(int id) {
        Client client = em.find(Client.class, id);
        return client;
    }

    @Override
    public List<Client> orderByName() {
        TypedQuery<Client> q = em.createQuery("Select c from Client c order by name", Client.class);
        return q.getResultList();
    }

    @Override
    public List<Client> getClientsByName(String name) {
        TypedQuery<Client> q = em.createQuery("Select c from Client c where c.name = :name", Client.class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showAllClients() {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.id,c.name,c.phone,c.street,c.house_number,c.city,c.postal_code,p.name from Client c JOIN c.province p", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showClientsByName(String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.id,c.name,c.phone,c.street,c.house_number,c.city,c.postal_code,p.name " +
                "from Client c JOIN c.province p where c.name = :name", Object[].class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showClientById(int id) {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.id,c.name,c.phone,c.street,c.house_number,c.city,c.postal_code,p.name " +
                "from Client c JOIN c.province p where c.id = :id", Object[].class);
        return q.setParameter("id",id).getResultList();
    }


}
