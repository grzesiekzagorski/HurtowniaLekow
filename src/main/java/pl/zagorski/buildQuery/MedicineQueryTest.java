package pl.zagorski.buildQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MedicineQueryTest {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        TypedQuery<Object[]> q = entityManager.createQuery("SELECT m.id,m.name,m.price,m.discount,m.portion,p.name,c.name," +
                "pr.name FROM Medicine m JOIN m.character c JOIN m.prescription p JOIN m.producer pr order by m.name", Object[].class);

        List<Object[]> results = q.getResultList();

        for (Object[] result : results) {
            System.out.println(result[0].toString() + "|" + result[1].toString() + "|"+result[3].toString() + "|" + Double.parseDouble(result[2].toString()) * 100 + "%" + "|" + result[4].toString() + "|" + result[5].toString()+"|" + result[6].toString() + "|" + result[7].toString());
        }


        entityManager.close();
        entityManagerFactory.close();


    }

}
