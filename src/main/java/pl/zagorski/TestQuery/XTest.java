package pl.zagorski.TestQuery;

import pl.zagorski.domain.Position;
import pl.zagorski.repositories.PositionRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class XTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Wyswietlanie akcji jakie ma dany pracownik(podaje imie i nazwisko)


//        String lek = "Davercin";
//        Object result = entityManager.createQuery("SELECT m.name FROM Character c JOIN c.medicines m where m.name=:lek").setParameter("lek", lek).getSingleResult();


        List<Object[]> results = entityManager.createQuery("SELECT m.name,m.id,m.discount,m.portion,m.price,c.name,p.name FROM Medicine m JOIN m.character c JOIN m.prescription p",Object[].class).getResultList();

        for (Object[] result : results) {
            System.out.println(result[0].toString()+"|"+result[1].toString()+"|"+result[2].toString()+"|"+result[3].toString()+"|"+result[4].toString()+"|"+result[5].toString()+"|"+result[6].toString());
        }

        entityManager.close();
        entityManagerFactory.close();


    }

}
