package pl.zagorski.buildQuery;

import pl.zagorski.domain.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class SupplierQueryTest {
    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Object[]> q = entityManager.createQuery("Select c.id,c.name,c.street,c.house_number,c.city,c.postal_code,p.name " +
                "from Supplier c JOIN c.province p where c.name = :name", Object[].class);
        List<Object[]> results = q.setParameter("name","Farmed").getResultList();

        entityManager.close();
        entityManagerFactory.close();

        for (Object[] result : results){
            System.out.println(result[0].toString() + "|" + result[1].toString() + "|"+result[2].toString() + "|"+ "|" + result[3].toString() + "|" + result[4].toString()+"|" + result[5].toString() + "|" + result[6].toString());
        }
    }
}
