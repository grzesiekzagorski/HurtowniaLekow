package pl.zagorski.buildDataBase;

import pl.zagorski.domain.Province;
import pl.zagorski.domain.Status;
import pl.zagorski.domain.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTableSupplier_7 {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Province province = entityManager.find(Province.class,10);
        Province province1 = entityManager.find(Province.class,7);

        Supplier supplier = new Supplier();
        supplier.setCity("Warszawa");
        supplier.setHouse_number("22a");
        supplier.setStreet("Puławska");
        supplier.setPostal_code("05-224");
        supplier.setName("Farmed");
        supplier.setProvince(province);

        Supplier supplier1 = new Supplier();
        supplier1.setCity("Kraków");
        supplier1.setHouse_number("10b");
        supplier1.setStreet("Geodetów");
        supplier1.setPostal_code("02-832");
        supplier1.setName("Farmpol Sp. z o.o.");
        supplier1.setProvince(province1);

        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.persist(supplier1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
