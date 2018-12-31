package pl.zagorski.TestDataBase;

import pl.zagorski.domain.*;
import pl.zagorski.domain.Character;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SupplierTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Supplier supplier = new Supplier();
        supplier.setName("Imed Poland Sp. z o.o.");
        supplier.setCity("Warszawa");
        supplier.setStreet("Puławska");
        supplier.setHouse_number("112a");
        supplier.setPostal_code("02-819");

        Province province = entityManager.find(Province.class,1);
        List<Supplier> supplierProvince = province.getSuppliers();
        supplierProvince.add(supplier);
        province.setSuppliers(supplierProvince);

        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.merge(province);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
