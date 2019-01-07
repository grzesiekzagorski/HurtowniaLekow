package pl.zagorski.buildQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeliveryQueryTest {
    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

       // TypedQuery<Object[]> query = entityManager.createQuery("SELECT d.id,p.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name,e.name,e.surname FROM Delivery d JOIN d.order p JOIN Medicine m JOIN Supplier s JOIN Employee e",Object[].class);

        TypedQuery<Object[]> query = entityManager.createQuery("SELECT d.id,o.id,m.name,d.amount,d.delivery_date,d.expiration_date,s.name FROM Warehouse d " +
                "JOIN d.supplier s JOIN d.order o JOIN o.medicine m WHERE d.id = :id ",Object[].class);

        List<Object[]> results = query.setParameter("id",1).getResultList();

        entityManager.close();
        entityManagerFactory.close();

        for (Object[] result : results) {
            System.out.println(result[0].toString() + "|" + result[1].toString() + "|"+result[2].toString()+ "|" + result[3].toString() + "|" + result[4].toString() + "|" + result[5].toString()+"|" + result[6].toString());

        }
        //+ "|" + result[3].toString() + "|" + result[4].toString() + "|" + result[5].toString()+"|" + result[6].toString() + "|" + result[7].toString()+ "|" + result[8].toString()


    }
}
