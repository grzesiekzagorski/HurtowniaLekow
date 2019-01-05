package pl.zagorski.buildQuery;

import pl.zagorski.domain.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PositionQueryTest {
    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<String> query = entityManager.createQuery("SELECT a.name FROM Position p JOIN p.actions a WHERE p.name= :name",String.class);

        List<String> results = query.setParameter("name","dział obsługi klienta").getResultList();

        entityManager.close();
        entityManagerFactory.close();

        for (String result : results) {
            System.out.println(result);
        }



    }
}
