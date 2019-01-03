package pl.zagorski.buildDataBase;

import pl.zagorski.domain.Client;
import pl.zagorski.domain.Province;
import pl.zagorski.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTableClient_10 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Province province = entityManager.find(Province.class, 3);
        Province province1 = entityManager.find(Province.class, 5);
        Province province2 = entityManager.find(Province.class, 7);

        Client client = new Client();
        client.setCity("Poznań");
        client.setHouse_number("109a");
        client.setName("Apteka Dr.Max");
        client.setStreet("Marszałkowska");
        client.setPostal_code("03-324");
        client.setPhone("987678654");
        client.setProvince(province);

        Client client1 = new Client();
        client1.setCity("Wrocław");
        client1.setHouse_number("127");
        client1.setName("Apteka Melissa");
        client1.setStreet("Strzelecka");
        client1.setPostal_code("07-980");
        client1.setPhone("543678987");
        client1.setProvince(province1);

        Client client2 = new Client();
        client2.setCity("Gdańsk");
        client2.setHouse_number("54a");
        client2.setName("Apteka Gemini");
        client2.setStreet("Narutowicza");
        client2.setPostal_code("06-543");
        client2.setPhone("890789678");
        client2.setProvince(province2);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}

