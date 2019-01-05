package pl.zagorski.buildQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActionQueryTest {
    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<String> query = entityManager.createQuery("SELECT p.name FROM Position p JOIN p.actions a WHERE a.name= :name",String.class);

        List<String> results = query.setParameter("name","baza leków").getResultList();

        entityManager.close();
        entityManagerFactory.close();

        for (String result : results) {
            System.out.println(result);
        }



    }
}
