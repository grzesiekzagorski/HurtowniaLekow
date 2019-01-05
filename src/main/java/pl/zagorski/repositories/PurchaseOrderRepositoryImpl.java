package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.PurchaseOrder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("PurchaseOrderDao")
public class PurchaseOrderRepositoryImpl implements PurchaseOrderDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        em.persist(purchaseOrder);
        em.flush();
    }

    @Override
    public void edit(PurchaseOrder purchaseOrder) {
        em.merge(purchaseOrder);
        em.flush();
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
        TypedQuery<Object[]> query = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m " +
                "order by m.name", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showPurchaseOrdersByName(String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m " +
                "where m.name = :name", Object[].class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showAllPurchaseOrders() {
        TypedQuery<Object[]> q = em.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m", Object[].class);
        return q.getResultList();
    }


}
