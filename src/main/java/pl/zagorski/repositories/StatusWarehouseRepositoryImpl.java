package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Status;
import pl.zagorski.domain.StatusWarehouse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("StatusWarehouseDao")
public class StatusWarehouseRepositoryImpl implements StatusWarehouseDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(StatusWarehouse status) {
        em.persist(status);
        em.flush();
    }

    @Override
    public void edit(StatusWarehouse status) {
        em.merge(status);
        em.flush();
    }

    @Override
    public List<StatusWarehouse> findAll() {
        TypedQuery<StatusWarehouse> q = em.createQuery("Select c from StatusWarehouse c", StatusWarehouse.class);
        return q.getResultList();
    }

    @Override
    public StatusWarehouse findOne(int id) {
        StatusWarehouse status= em.find(StatusWarehouse.class, id);
        return status;
    }

    @Override
    public List<StatusWarehouse> orderByName() {
        TypedQuery<StatusWarehouse> q = em.createQuery("Select c from StatusWarehouse c order by name", StatusWarehouse.class);
        return q.getResultList();
    }

    @Override
    public StatusWarehouse getStatusByName(String name) {
        TypedQuery<StatusWarehouse> q = em.createQuery("Select c from StatusWarehouse c where c.name = :name", StatusWarehouse.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public StatusWarehouse getInStockStatus() {
        TypedQuery<StatusWarehouse> q = em.createQuery("Select c from StatusWarehouse c where c.name = :name", StatusWarehouse.class);
        return q.setParameter("name", "na magazynie").getSingleResult();
    }
}
