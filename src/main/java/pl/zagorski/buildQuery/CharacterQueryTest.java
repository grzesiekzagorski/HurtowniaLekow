package pl.zagorski.buildQuery;

import pl.zagorski.domain.Character;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CharacterQueryTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Character> resultsCharacter = entityManager.createQuery("Select c from Character c", Character.class).getResultList();
        Character resultOneCharacter = entityManager.find(Character.class,1);
        Character characterByName = entityManager.createQuery("Select c from Character c where c.name = :name", Character.class).setParameter("name","tabletki").getSingleResult();
        List<Character> resultsCharacterOrderByName = entityManager.createQuery("Select c from Character c order by name", Character.class).getResultList();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("---------------------------findAll-------------------------");
        for(Character character : resultsCharacter){
            System.out.println(character.getId()+"|"+character.getName());
        }
        System.out.println("----------------------------findOne-----------------------");
        System.out.println(resultOneCharacter.getId()+"|"+resultOneCharacter.getName());
        System.out.println("---------------------------getCharacterByName-----------------");
        System.out.println(characterByName.getId()+"|"+characterByName.getName());
        System.out.println("---------------------------orderByName-----------------");
        for(Character character : resultsCharacterOrderByName){
            System.out.println(character.getId()+"|"+character.getName());
        }


    }
}
