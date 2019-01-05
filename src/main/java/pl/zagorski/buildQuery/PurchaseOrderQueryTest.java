package pl.zagorski.buildQuery;

import pl.zagorski.domain.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PurchaseOrderQueryTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        TypedQuery<String[]> query = entityManager.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,e.name,e.surname FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m order by m.name", String[].class);

        TypedQuery<Medicine> q22 = entityManager.createQuery("Select c from Medicine c where c.name = :name", Medicine.class);

        List<String[]> results = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("************************************************************");

           for (String[] akcja: results){
               System.out.println(akcja[0]);
        }

    }
}
