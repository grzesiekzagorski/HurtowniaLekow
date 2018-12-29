package pl.zagorski.TestDataBase;

import pl.zagorski.domain.Employee;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PurchaseOrderTest {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setAmount(180);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = null;
        try {
            parsed = format.parse("20110210");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        purchaseOrder.setDate_of_order(sql);

        Status status = entityManager.find(Status.class, 1);
        Employee employee = entityManager.find(Employee.class, 1);
        Medicine medicine = entityManager.find(Medicine.class, 1);

        List<PurchaseOrder> orderStatus = status.getOrders();
        List<PurchaseOrder> orderEmployee = employee.getOrders();
        List<PurchaseOrder> orderMedicine = medicine.getOrders();

        orderStatus.add(purchaseOrder);
        orderEmployee.add(purchaseOrder);
        orderMedicine.add(purchaseOrder);

        status.setOrders(orderStatus);
        employee.setOrders(orderEmployee);
        medicine.setOrders(orderMedicine);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.merge(status);
        entityManager.merge(employee);
        entityManager.merge(medicine);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }
}
