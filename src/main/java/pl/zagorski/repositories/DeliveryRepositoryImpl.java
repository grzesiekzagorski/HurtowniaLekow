package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Delivery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("DeliveryDao")
public class DeliveryRepositoryImpl implements DeliveryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Delivery delivery) {
        em.persist(delivery);
        em.flush();
    }

    @Override
    @Transactional
    public void edit(Delivery delivery) {
        em.merge(delivery);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(Delivery delivery) {
        em.remove(delivery);
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
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllDeliveriesOrderByMedicineName() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s order by m.name", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllDeliveriesOrderByDeliveryAmount() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.id,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s order by o.amount", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showDeliveriesByMedicineName(String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s WHERE m.name = :name", Object[].class);
        return query.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showDeliveryById(int id) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s WHERE d.id = :id", Object[].class);
        return query.setParameter("id", id).getResultList();
    }

    @Override
    public List<Object[]> showDeliveryByIdAndMedicineName(int id, String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s WHERE d.id = :id AND m.name = :name", Object[].class);
        return query.setParameter("id", id).setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showDeliveryWhereItIsNotForSale() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,m.name,o.amount,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Delivery d " +
                "JOIN d.employee e JOIN d.order o JOIN o.medicine m JOIN o.supplier s JOIN o.orders w JOIN w.status sw WHERE sw.name = :name", Object[].class);
        return query.setParameter("name", "na magazynie").getResultList();
    }

}