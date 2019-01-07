package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Prescription;
import pl.zagorski.domain.Producer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("PrescriptionDao")
public class PrescriptionRepositoryImpl implements PrescriptionDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Prescription prescription) {
        em.persist(prescription);
        em.flush();
    }

    @Override
    public void edit(Prescription prescription) {
        em.merge(prescription);
        em.flush();
    }

    @Override
    public List<Prescription> findAll() {
        TypedQuery<Prescription> q = em.createQuery("Select c from Prescription c", Prescription.class);
        return q.getResultList();
    }

    @Override
    public Prescription findOne(int id) {
        Prescription prescription= em.find(Prescription.class, id);
        return prescription;
    }

    @Override
    public List<Prescription> orderByName() {
        TypedQuery<Prescription> q = em.createQuery("Select c from Prescription c order by name", Prescription.class);
        return q.getResultList();
    }

    @Override
    public Prescription getPrescriptionByName(String name) {
        TypedQuery<Prescription> q = em.createQuery("Select c from Prescription c where c.name = :name", Prescription.class);
        return q.setParameter("name", name).getSingleResult();
    }
}
