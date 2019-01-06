package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("SupplierDao")
public class SupplierRepositoryImpl implements SupplierDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Supplier supplier) {
        em.persist(supplier);
        em.flush();
    }

    @Override
    public void edit(Supplier supplier) {
        em.merge(supplier);
        em.flush();
    }

    @Override
    public List<Supplier> findAll() {
        TypedQuery<Supplier> q = em.createQuery("Select c from Supplier c", Supplier.class);
        return q.getResultList();
    }

    @Override
    public Supplier findOne(int id) {
        Supplier supplier = em.find(Supplier.class, id);
        return supplier;
    }

    @Override
    public List<Supplier> orderByName() {
        TypedQuery<Supplier> q = em.createQuery("Select c from Supplier c order by name", Supplier.class);
        return q.getResultList();
    }

    @Override
    public List<Supplier> getSuppliersByName(String name) {
        TypedQuery<Supplier> q = em.createQuery("Select c from Supplier c where c.name = :name", Supplier.class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showAllSuppliers() {
        TypedQuery<Object[]> q = em.createQuery("Select c.id,c.name,c.street,c.house_number,c.city,c.postal_code,p.name " +
                "from Supplier c JOIN c.province p order by c.id", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showSupplierByName(String name) {
        TypedQuery<Object[]> q = em.createQuery("Select c.id,c.name,c.street,c.house_number,c.city,c.postal_code,p.name " +
                "from Supplier c JOIN c.province p where c.name = :name", Object[].class);
        return q.setParameter("name", name).getResultList();
    }

    @Override
    public List<Object[]> showSupplierById(int id) {
        TypedQuery<Object[]> q = em.createQuery("Select c.id,c.name,c.street,c.house_number,c.city,c.postal_code,p.name " +
                "from Supplier c JOIN c.province p where c.id = :id", Object[].class);
        return q.setParameter("id", id).getResultList();
    }


}
