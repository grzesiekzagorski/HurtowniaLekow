package pl.zagorski.buildDataBase;

import pl.zagorski.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTableDelivery_8 {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Delivery delivery = new Delivery();
        delivery.setAmount(90);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse("2017-04-12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        delivery.setExpiration_date(sql);

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedData = null;
        try {
            parsedData = formatData.parse("2015-03-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlData = new java.sql.Date(parsedData.getTime());
        delivery.setDelivery_date(sqlData);

        Employee employee = entityManager.find(Employee.class,2);
        Supplier supplier = entityManager.find(Supplier.class,1);
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class,1);

        delivery.setEmployee(employee);
        delivery.setOrder(purchaseOrder);
        delivery.setSupplier(supplier);

        entityManager.getTransaction().begin();
        entityManager.persist(delivery);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
