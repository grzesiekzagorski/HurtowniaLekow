package pl.zagorski.buildDataBase;

import pl.zagorski.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTableSale_11 {
    public static void main(String[]args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Sale sale = new Sale();
        sale.setAmount(90);

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedData = null;
        try {
            parsedData = formatData.parse("2015-03-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlData = new java.sql.Date(parsedData.getTime());
        sale.setSale_date(sqlData);

        Client client = entityManager.find(Client.class,2);
        Employee employee = entityManager.find(Employee.class,1);
        Warehouse warehouse = entityManager.find(Warehouse.class,1);

        sale.setClient(client);
        sale.setEmployee(employee);
        sale.setWarehouse(warehouse);

        entityManager.getTransaction().begin();
        entityManager.persist(sale);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
