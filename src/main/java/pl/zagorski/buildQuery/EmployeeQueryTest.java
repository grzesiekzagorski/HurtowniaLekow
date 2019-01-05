package pl.zagorski.buildQuery;

import pl.zagorski.domain.Employee;
import pl.zagorski.domain.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeQueryTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        TypedQuery<String> query = entityManager.createQuery("SELECT a.name FROM Employee e JOIN e.position p JOIN p.actions a WHERE e.login = :login",String.class);
        List<String> results = query.setParameter("login","nadia.waleriewa").getResultList();

        //List<Object[]> results = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("************************************************************");

        System.out.println(results.size());

        for (String akcja : results){
            System.out.println(akcja);
        }





    }
}
