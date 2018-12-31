package pl.zagorski.TestDataBase;

import pl.zagorski.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WarehouseTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Warehouse warehouse = new Warehouse();
        warehouse.setAmount(90);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse("2015-02-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());

        warehouse.setDelivery_date(sql);
        warehouse.setExpiration_date(sql);

        Employee employee = entityManager.find(Employee.class,1);
        Supplier supplier = entityManager.find(Supplier.class,1);
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class,1);

        List<Warehouse> warehouseEmployee = employee.getWarehouseList();
        List<Warehouse> warehouseSupplier = supplier.getWarehouseList();
        List<Warehouse> warehousePurchase = purchaseOrder.getWarehouseList();

        warehouseEmployee.add(warehouse);
        warehouseSupplier.add(warehouse);
        warehousePurchase.add(warehouse);

        employee.setWarehouseList(warehouseEmployee);
        supplier.setWarehouseList(warehouseSupplier);
        purchaseOrder.setWarehouseList(warehousePurchase);

        entityManager.getTransaction().begin();
        entityManager.persist(warehouse);
        entityManager.merge(employee);
        entityManager.merge(supplier);
        entityManager.merge(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
