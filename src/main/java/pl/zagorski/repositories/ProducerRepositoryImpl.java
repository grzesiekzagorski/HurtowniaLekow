package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Producer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("ProducerDao")
public class ProducerRepositoryImpl implements ProducerDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Producer producer) {
        em.persist(producer);
        em.flush();
    }

    @Override
    public void edit(Producer producer) {
        em.merge(producer);
        em.flush();
    }

    @Override
    public List<Producer> findAll() {
        TypedQuery<Producer> q = em.createQuery("Select c from Producer c", Producer.class);
        return q.getResultList();
    }

    @Override
    public Producer findOne(int id) {
        Producer producer= em.find(Producer.class, id);
        return producer;
    }

    @Override
    public List<Producer> orderByName() {
        TypedQuery<Producer> q = em.createQuery("Select c from Producer c order by name", Producer.class);
        return q.getResultList();
    }

    @Override
    public Producer getProducerByName(String name) {
        TypedQuery<Producer> q = em.createQuery("Select c from Producer c where c.name = :name", Producer.class);
        return q.setParameter("name", name).getSingleResult();
    }
}
