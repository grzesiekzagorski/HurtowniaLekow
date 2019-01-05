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


        TypedQuery<Object[]> query = entityManager.createQuery("SELECT p.id,m.name,p.amount,p.date_of_order,s.name,e.name,e.surname " +
                "FROM PurchaseOrder p JOIN p.employee e JOIN p.status s JOIN p.medicine m " +
                "where p.id = :id", Object[].class);

        TypedQuery<Medicine> q22 = entityManager.createQuery("Select c from Medicine c where c.name = :name", Medicine.class);

        List<Object[]> results = query.setParameter("id","1").getResultList();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("************************************************************");

        for (Object[] result : results) {
            System.out.println(result[0].toString() + "|" + result[1].toString() + "|"+result[2].toString()+ "|" + result[3].toString() + "|" + result[4].toString() + "|" + result[5].toString()+"|" + result[6].toString());

        }

    }
}
