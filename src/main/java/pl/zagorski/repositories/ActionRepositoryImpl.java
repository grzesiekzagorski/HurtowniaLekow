package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("ActionDao")
public class ActionRepositoryImpl implements ActionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Action action) {
        em.persist(action);
        em.flush();
    }

    @Override
    public void edit(Action action) {
        em.merge(action);
        em.flush();
    }

    @Override
    public List<Action> findAll() {
        TypedQuery<Action> q = em.createQuery("Select c from Action c",Action.class);
        return q.getResultList();
    }

    @Override
    public Action findOne(int id) {
        Action action = em.find(Action.class, id);
        return action;
    }

    @Override
    public List<Action> orderByName() {
        TypedQuery<Action> q = em.createQuery("Select c from Action c order by name", Action.class);
        return q.getResultList();
    }

    @Override
    public Action getActionByName(String name) {
        TypedQuery<Action> q = em.createQuery("Select c from Action c where c.name = :name", Action.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<String> showPositionsOfThisAction(String name) {
        TypedQuery<String> query = em.createQuery("SELECT p.name FROM Position p JOIN p.actions a WHERE a.name= :name",String.class);
        return query.setParameter("name",name).getResultList();
    }
}
