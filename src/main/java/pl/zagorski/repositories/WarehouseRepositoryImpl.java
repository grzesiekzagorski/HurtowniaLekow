package pl.zagorski.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Delivery;
import pl.zagorski.domain.Sale;
import pl.zagorski.domain.Status;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.exceptions.ExceptionSample;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("WarehouseDao")
public class WarehouseRepositoryImpl implements WarehouseDao{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    SaleRepositoryImpl saleRepository;

    @Override
    @Transactional
    public void save(Warehouse warehouse) {
        em.persist(warehouse);
        em.flush();
    }

    @Override
    @Transactional
    public void edit(Warehouse warehouse) {
        em.merge(warehouse);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(Warehouse warehouse)throws ExceptionSample {
        for (Sale sale : saleRepository.findAll()) {
            if(sale.getWarehouse().getId() == warehouse.getId()){
                throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
            }
        }
        em.remove(warehouse);
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
    public Warehouse findOneByPurchaseOrderId(int id) {
        TypedQuery<Warehouse> q = em.createQuery("Select c from Warehouse c JOIN c.order o where o.id= :id", Warehouse.class);
        return q.setParameter("id",id).getSingleResult();
    }

    @Override
    public List<Object[]> showAllWarehouses() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,m.name,d.amount,b.name,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.order o JOIN o.medicine m JOIN o.supplier s JOIN d.status b",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByMedicineName() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN o.medicine m order by m.name",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByWarehouseAmount() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN o.medicine m order by d.amount",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showWarehousesByMedicineName(String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,k.id,m.name,d.amount,b.name,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN d.status b JOIN o.ordersDelivery k JOIN o.employee e JOIN o.medicine m WHERE m.name = :name ",Object[].class);
        return query.setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showWarehouseById(int id) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,k.id,m.name,d.amount,b.name,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN d.status b JOIN o.ordersDelivery k JOIN o.employee e JOIN o.medicine m WHERE d.id = :id ",Object[].class);
        return query.setParameter("id",id).getResultList();
    }

    @Override
    public List<Object[]> showWarehouseByIdAndMedicineName(int id, String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,k.id,m.name,d.amount,b.name,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN d.status b JOIN o.ordersDelivery k JOIN o.employee e JOIN o.medicine m WHERE d.id = :id AND m.name = :name",Object[].class);
        return query.setParameter("id",id).setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showWarehouseWhereStatusEqualsInStock() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,m.name,d.amount,b.name,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN d.status b JOIN o.medicine m WHERE b.name = :status ",Object[].class);
        return query.setParameter("status","na magazynie").getResultList();
    }

    @Override
    public List<Object[]> showWarehouseWhereStatusEqualsInStockOrOnSold() {
        TypedQuery<Object[]> query = em.createQuery("SELECT d.id,k.id,m.name,d.amount,b.name,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Warehouse d " +
                "JOIN d.order o JOIN o.supplier s JOIN d.status b JOIN d.employee e JOIN o.ordersDelivery k JOIN o.medicine m WHERE b.name= :status OR b.name = :status2 ",Object[].class);
        return query.setParameter("status","na magazynie").setParameter("status2","w sprzedaży").getResultList();
    }


}


