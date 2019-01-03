package pl.zagorski.buildDataBase;

import pl.zagorski.domain.Province;
import pl.zagorski.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTableProvince_6 {
    public static void main(String[]args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Province province1 = new Province();
        province1.setName("Dolnośląskie");
        Province province2 = new Province();
        province2.setName("Kujawsko-Pomorskie");
        Province province3 = new Province();
        province3.setName("Małopolskie");
        Province province4 = new Province();
        province4.setName("Warmińsko-Mazurskie");
        Province province5 = new Province();
        province5.setName("Podlaskie");
        Province province6 = new Province();
        province6.setName("Lubelskie");
        Province province7 = new Province();
        province7.setName("Świętokrzyskie");
        Province province8 = new Province();
        province8.setName("Mazowieckie");
        Province province9 = new Province();
        province9.setName("Podkarpackie");
        Province province10 = new Province();
        province10.setName("Opolskie");
        Province province11 = new Province();
        province11.setName("Wielkopolskie");
        Province province12 = new Province();
        province12.setName("Lubuskie");

        entityManager.getTransaction().begin();
        entityManager.persist(province1);
        entityManager.persist(province2);
        entityManager.persist(province3);
        entityManager.persist(province4);
        entityManager.persist(province5);
        entityManager.persist(province6);
        entityManager.persist(province7);
        entityManager.persist(province8);
        entityManager.persist(province9);
        entityManager.persist(province10);
        entityManager.persist(province11);
        entityManager.persist(province12);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
