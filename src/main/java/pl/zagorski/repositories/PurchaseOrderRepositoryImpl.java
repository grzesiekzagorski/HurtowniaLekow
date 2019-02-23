package pl.zagorski.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.services.WarehouseImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("PurchaseOrderDao")
public class PurchaseOrderRepositoryImpl implements PurchaseOrderDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    WarehouseImpl warehouseDao;

    @Override
    @Transactional
    public void save(PurchaseOrder purchaseOrder) {
        em.persist(purchaseOrder);
        em.flush();
    }

    @Override
    @Transactional
    public void edit(PurchaseOrder purchaseOrder)throws ExceptionSample {
        for (Warehouse warehouse : warehouseDao.findAll()) {
            if(warehouse.getOrder().getId() == purchaseOrder.getId()){
                throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
            }
        }
        em.merge(purchaseOrder);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(PurchaseOrder purchaseOrder)throws ExceptionSample {
        for (Warehouse warehouse : warehouseDao.findAll()) {
            if(warehouse.getOrder().getId() == purchaseOrder.getId()){
                throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
            }
        }
        em.remove(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> findAll() {
        TypedQuery<PurchaseOrder> q = em.createQuery("Select c from PurchaseOrder c", PurchaseOrder.class);
        return q.getResultList();
    }

    @Override
    public PurchaseOrder findOne(int id) {
        PurchaseOrder purchaseOrder = em.find(PurchaseOrder.class, id);
        return purchaseOrder;
    }

    @Override
    public List<Object[]> orderByName() {
        TypedQuery<Object[]> query = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,e.name," +
                "e.surname,d.name FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m JOIN p.supplier d " +
                "order by m.name", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showPurchaseOrdersByName(String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,d.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m JOIN p.supplier d " +
                "where m.name = :name", Object[].class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showPurchaseOrderById(int id) {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,d.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m JOIN p.supplier d " +
                "where p.id = :id", Object[].class);
        return q.setParameter("id", id).getResultList();
    }

    @Override
    public List<Object[]> showPurchaseOrderByIdAndName(int id,String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,d.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m JOIN p.supplier d " +
                "where p.id = :id AND m.name= :name", Object[].class);
        return q.setParameter("id", id).setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showAllPurchaseOrders() {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,d.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m JOIN p.supplier d ORDER BY p.id", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showAllPurchaseOrdersThatAreNotDelivered() {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name FROM PurchaseOrder p JOIN p.status s JOIN p.medicine m " +
                "where s.name = :notDelivered ORDER BY p.id", Object[].class);
        return q.setParameter("notDelivered","złożony").getResultList();
    }


}
