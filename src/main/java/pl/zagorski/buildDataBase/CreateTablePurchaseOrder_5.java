package pl.zagorski.buildDataBase;

import pl.zagorski.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTablePurchaseOrder_5 {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setAmount(180);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse("2015-02-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        purchaseOrder.setDate_of_order(sql);

        Employee employee = entityManager.find(Employee.class,2);
        Medicine medicine = entityManager.find(Medicine.class,3);
        Status status = entityManager.find(Status.class,2);
        purchaseOrder.setEmployee(employee);
        purchaseOrder.setMedicine(medicine);
        purchaseOrder.setStatus(status);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
