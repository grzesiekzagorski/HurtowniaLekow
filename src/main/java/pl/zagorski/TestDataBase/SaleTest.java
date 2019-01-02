package pl.zagorski.TestDataBase;

import pl.zagorski.domain.Client;
import pl.zagorski.domain.Employee;
import pl.zagorski.domain.Sale;
import pl.zagorski.domain.Warehouse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaleTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Sale sale = new Sale();
        sale.setAmount(90);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse("2017-09-24");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        sale.setSale_date(sql);

        Warehouse warehouse = entityManager.find(Warehouse.class,1);
        Employee employee = entityManager.find(Employee.class,1);
        Client client = entityManager.find(Client.class,1);

        List<Sale> saleWarehouse = warehouse.getSales();
        List<Sale> saleEmployee = employee.getSales();
        List<Sale> saleClient = client.getSales();

        saleWarehouse.add(sale);
        saleEmployee.add(sale);
        saleClient.add(sale);

        warehouse.setSales(saleWarehouse);
        employee.setSales(saleEmployee);
        client.setSales(saleClient);

        entityManager.getTransaction().begin();
        entityManager.persist(sale);
        entityManager.merge(warehouse);
        entityManager.merge(employee);
        entityManager.merge(client);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }

}
