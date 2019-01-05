package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Delivery;
import pl.zagorski.domain.Warehouse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("WarehouseDao")
public class WarehouseRepositoryImpl implements WarehouseDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Warehouse warehouse) {
        em.persist(warehouse);
        em.flush();
    }

    @Override
    public void edit(Warehouse warehouse) {
        em.merge(warehouse);
        em.flush();
    }

    @Override
    public List<Warehouse> findAll() {
        TypedQuery<Warehouse> q = em.createQuery("Select c from Warehouse c", Warehouse.class);
        return q.getResultList();
    }

    @Override
    public Warehouse findOne(int id) {
        Warehouse warehouse = em.find(Warehouse.class, id);
        return warehouse;
    }

    @Override
    public List<Object[]> showAllWarehouses() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByMedicineName() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m order by m.name",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByWarehouseAmount() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m order by d.amount",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showWarehousesByMedicineName(String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m WHERE m.name = :name ",Object[].class);
        return query.setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showWarehouseById(int id) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m WHERE d.id = :id ",Object[].class);
        return query.setParameter("id",id).getResultList();
    }


}


