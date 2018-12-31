package pl.zagorski.TestDataBase;

import pl.zagorski.domain.Client;
import pl.zagorski.domain.Province;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Client client = new Client();
        client.setName("Apteka Dr.Max");
        client.setCity("Warszawa");
        client.setStreet("Patriotów");
        client.setHouse_number("309A");
        client.setPhone("543234123");
        client.setPostal_code("05-022");

        Province province = entityManager.find(Province.class,1);
        List<Client> clientProvince = province.getClients();
        clientProvince.add(client);
        province.setClients(clientProvince);

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.merge(province);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
