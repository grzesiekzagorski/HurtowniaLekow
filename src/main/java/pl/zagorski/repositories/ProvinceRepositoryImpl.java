package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Province;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("ProvinceDao")
public class ProvinceRepositoryImpl implements ProvinceDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Province province) {
        em.persist(province);
        em.flush();
    }

    @Override
    public void edit(Province province) {
        em.merge(province);
        em.flush();
    }

    @Override
    public List<Province> findAll() {
        TypedQuery<Province> q = em.createQuery("Select c from Province c", Province.class);
        return q.getResultList();
    }

    @Override
    public Province findOne(int id) {
        Province province = em.find(Province.class, id);
        return province;
    }

    @Override
    public List<Province> orderByName() {
        TypedQuery<Province> q = em.createQuery("Select c from Province c order by name", Province.class);
        return q.getResultList();
    }

    @Override
    public Province getProvinceByName(String name) {
        TypedQuery<Province> q = em.createQuery("Select c from Province c where c.name = :name", Province.class);
        return q.setParameter("name", name).getSingleResult();
    }
}
