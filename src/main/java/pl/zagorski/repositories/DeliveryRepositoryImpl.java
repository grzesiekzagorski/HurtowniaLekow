package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Delivery;
import pl.zagorski.domain.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("DeliveryDao")
public class DeliveryRepositoryImpl implements DeliveryDao{

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
        return q.getResultList();
    }

    @Override
    public Delivery findOne(int id) {
        Delivery delivery = em.find(Delivery.class, id);
        return delivery;
    }

    @Override
    public List<Object[]> showAllDeliveries() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.supplier s JOIN d.order o JOIN o.medicine m",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllDeliveriesOrderByMedicineName() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.supplier s JOIN d.order o JOIN o.medicine m order by m.name",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllDeliveriesOrderByDeliveryAmount() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.supplier s JOIN d.order o JOIN o.medicine m order by d.amount",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showDeliveriesByMedicineName(String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.supplier s JOIN d.order o JOIN o.medicine m WHERE m.name = :name",Object[].class);
        return query.setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showDeliveryById(int id) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.supplier s JOIN d.order o JOIN o.medicine m WHERE d.id = :id",Object[].class);
        return query.setParameter("id",id).getResultList();
    }


}


