package pl.zagorski.buildQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class SaleQueryTest {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Object[]> query = entityManager.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m",Object[].class);

        TypedQuery<Object[]> q = entityManager.createQuery("SELECT s.id,w.id,m.name,s.amount,sp.name,c.name,e.name,e.surname FROM Sale s JOIN s.employee e JOIN s.warehouse w JOIN w.supplier sp JOIN w.order o JOIN o.medicine m JOIN s.client c", Object[].class);

        List<Object[]> results = q.getResultList();

        for (Object[] result : results) {
            System.out.println(result[0].toString() + "|" + result[1].toString() + "|"+result[2].toString() + "|" +result[3].toString() + "|" + result[4].toString() + "|" + result[5].toString()+"|" + result[6].toString() + "|" + result[7].toString());
        }


        entityManager.close();
        entityManagerFactory.close();


    }

}
