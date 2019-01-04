package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Producer;
import pl.zagorski.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("StatusDao")
public class StatusRepositoryImpl implements StatusDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Status status) {
        em.persist(status);
        em.flush();
    }

    @Override
    public void edit(Status status) {
        em.merge(status);
        em.flush();
    }

    @Override
    public List<Status> findAll() {
        TypedQuery<Status> q = em.createQuery("Select c from Status c", Status.class);
        return q.getResultList();
    }

    @Override
    public Status findOne(int id) {
        Status status= em.find(Status.class, id);
        return status;
    }

    @Override
    public List<Status> orderByName() {
        TypedQuery<Status> q = em.createQuery("Select c from Status c order by name", Status.class);
        return q.getResultList();
    }

    @Override
    public Status getStatusByName(String name) {
        TypedQuery<Status> q = em.createQuery("Select c from Status c where c.name = :name", Status.class);
        return q.setParameter("name", name).getSingleResult();
    }
}
