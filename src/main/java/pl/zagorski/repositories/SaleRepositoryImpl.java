package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Sale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("SaleDao")
public class SaleRepositoryImpl implements SaleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Sale sale) {
        em.persist(sale);
        em.flush();
    }

    @Override
    @Transactional
    public void edit(Sale sale) {
        em.merge(sale);
        em.flush();
    }

    @Override
    public List<Sale> findAll() {
        TypedQuery<Sale> q = em.createQuery("Select c from Sale c", Sale.class);
        return q.getResultList();
    }

    @Override
    public Sale findOne(int id) {
        Sale sale = em.find(Sale.class, id);
        return sale;
    }

    @Override
    public List<Object[]> showAllSales() {
        TypedQuery<Object[]> q = em.createQuery("SELECT s.id,w.id,m.name,s.amount,s.sale_date,c.name,e.name,e.surname " +
                "FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.order x " +
                "JOIN w.order o JOIN o.medicine m JOIN s.client c", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showAllSalesOrderByMedicineName() {
        TypedQuery<Object[]> q = em.createQuery("SELECT s.id,w.id,m.name,s.amount,sp.name,c.name,e.name,e.surname " +
                "FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.order x JOIN x.supplier sp " +
                "JOIN w.order o JOIN o.medicine m JOIN s.client c ORDER BY m.name", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showAllSalesOrderBySaleAmount() {
        TypedQuery<Object[]> q = em.createQuery("SELECT s.id,w.id,m.name,s.amount,sp.name,c.name,e.name,e.surname " +
                "FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.order x JOIN x.supplier sp " +
                "JOIN w.order o JOIN o.medicine m JOIN s.client c ORDER BY s.amount", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showSalesByMedicineName(String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT s.id,w.id,m.name,s.amount,s.sale_date,c.name,e.name,e.surname " +
                "FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.order x JOIN w.order o JOIN o.medicine m " +
                "JOIN s.client c WHERE m.name = :name", Object[].class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showSaleById(int id) {
        TypedQuery<Object[]> q = em.createQuery("SELECT s.id,w.id,m.name,s.amount,s.sale_date,c.name,e.name,e.surname " +
                "FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.order x JOIN w.order o JOIN o.medicine m " +
                "JOIN s.client c WHERE s.id = :id", Object[].class);
        return q.setParameter("id", id).getResultList();
    }

    @Override
    public List<Object[]> showSaleByIdAndMedicineName(int id,String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT s.id,w.id,m.name,s.amount,s.sale_date,c.name,e.name,e.surname " +
                "FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.order x JOIN w.order o JOIN o.medicine m " +
                "JOIN s.client c WHERE s.id = :id AND m.name = :name", Object[].class);
        return q.setParameter("id", id).setParameter("name",name).getResultList();
    }


}


