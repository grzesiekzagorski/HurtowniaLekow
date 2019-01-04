package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Character;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("CharacterDao")
public class CharacterRepositoryImpl implements CharacterDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Character character) {
        em.persist(character);
        em.flush();
    }

    @Override
    public void edit(Character character) {
        em.merge(character);
        em.flush();
    }

    @Override
    public List<Character> findAll() {
        TypedQuery<Character> q = em.createQuery("Select c from Character c", Character.class);
        return q.getResultList();
    }

    @Override
    public Character findOne(int id) {
        Character character= em.find(Character.class, id);
        return character;
    }

    @Override
    public List<Character> orderByName() {
        TypedQuery<Character> q = em.createQuery("Select c from Character c order by name", Character.class);
        return q.getResultList();
    }

    @Override
    public Character getCharacterByName(String name) {
        TypedQuery<Character> q = em.createQuery("Select c from Character c where c.name = :name", Character.class);
        return q.setParameter("name", name).getSingleResult();
    }
}
