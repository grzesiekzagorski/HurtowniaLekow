package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Action;
import pl.zagorski.domain.Delivery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("deliveryDao")
public class DeliveryRepositoryImpl implements DeliveryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Delivery delivery) {
        em.persist(delivery);
        em.flush();
    }

    @Override
    public void edit(Delivery delivery) {
        em.merge(delivery);
        em.flush();
    }

    @Override
    public List<Delivery> findAll() {
        TypedQuery<Delivery> q = em.createQuery("Select c from Delivery c", Delivery.class);
        return (List<Delivery>) q.getResultList();
    }

    @Override
    public Delivery findOne(int id) {
        Delivery delivery = em.find(Delivery.class, id);
        return delivery;
    }

    @Override
    public List<Delivery> orderByName() {
        TypedQuery<Delivery> query = em.createNamedQuery("orderByName", Delivery.class);
        return query.getResultList();
    }

    @Override
    public Delivery getDeliveryByName(String name) {
        TypedQuery<Delivery> q = em.createQuery("Select c from Action c where c.name = :name", Delivery.class);
        return q.setParameter("name", name).getSingleResult();
    }

}
