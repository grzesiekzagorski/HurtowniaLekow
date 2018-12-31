package pl.zagorski.TestDataBase;

import pl.zagorski.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DeliveryTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Delivery delivery= new Delivery();
        delivery.setAmount(90);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse("2015-02-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());

        delivery.setDelivery_date(sql);
        delivery.setExpiration_date(sql);

        Employee employee = entityManager.find(Employee.class,1);
        Supplier supplier = entityManager.find(Supplier.class,1);
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class,1);

        List<Delivery> deliveryEmployee = employee.getDeliveryList();
        List<Delivery> deliverySupplier = supplier.getDeliveryList();
        List<Delivery> deliveryPurchase = purchaseOrder.getDeliveryList();

        deliveryEmployee.add(delivery);
        deliverySupplier.add(delivery);
        deliveryPurchase.add(delivery);

        employee.setDeliveryList(deliveryEmployee);
        supplier.setDeliveryList(deliverySupplier);
        purchaseOrder.setDeliveryList(deliveryPurchase);

        entityManager.getTransaction().begin();
        entityManager.persist(delivery);
        entityManager.merge(employee);
        entityManager.merge(supplier);
        entityManager.merge(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
