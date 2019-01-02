package pl.zagorski.TestQuery;

import pl.zagorski.domain.Action;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class PositionQueryTest {
    
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Wyswietlanie akcji jakie ma dany pracownik(podaje imie i nazwisko)

        //   List<Object[]> results = entityManager.createQuery("SELECT m.name FROM Medicine m JOIN Producer p ON m.producer_id = p.id").getResultList();
        String lek = "Davercin";
        Object result = entityManager.createQuery("SELECT m.name FROM Character c JOIN c.medicines m where m.name=:lek").setParameter("lek", lek).getSingleResult();


        entityManager.close();
        entityManagerFactory.close();
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(result.toString());

    }

}
