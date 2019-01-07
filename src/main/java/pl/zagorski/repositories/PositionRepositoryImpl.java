package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Position;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("PositionDao")
public class PositionRepositoryImpl implements PositionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Position position) {
        em.persist(position);
        em.flush();
    }

    @Override
    public void edit(Position position) {
        em.merge(position);
        em.flush();
    }

    @Override
    public List<Position> findAll() {
        TypedQuery<Position> q = em.createQuery("Select c from Position c",Position.class);
        return q.getResultList();
    }

    @Override
    public Position findOne(int id) {
        Position position = em.find(Position.class, id);
        return position;
    }

    @Override
    public List<Position> orderByName() {
        TypedQuery<Position> q = em.createQuery("Select c from Position c order by name", Position.class);
        return q.getResultList();
    }

    @Override
    public Position getPositionByName(String name) {
        TypedQuery<Position> q = em.createQuery("Select c from Position c where c.name = :name", Position.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<String> showActionsOfThisPosition(String name) {
        TypedQuery<String> query = em.createQuery("SELECT a.name FROM Position p JOIN p.actions a WHERE p.name= :name",String.class);
        return query.setParameter("name",name).getResultList();
    }
}
