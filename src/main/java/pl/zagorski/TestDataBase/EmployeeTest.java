package pl.zagorski.TestDataBase;

import pl.zagorski.domain.Employee;
import pl.zagorski.domain.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = new Employee();
        employee.setName("Adam");
        employee.setSurname("Grosicki");
        employee.setLogin("adam.grosicki");
        employee.setPassword("grosiciki123");

        Employee employee2 = new Employee();
        employee2.setName("Lukasz");
        employee2.setSurname("Kadziewicz");
        employee2.setLogin("lukasz.kadziewicz");
        employee2.setPassword("kadziewicz123");

        Position position = entityManager.find(Position.class, 1);
        Position position2 = entityManager.find(Position.class,2);

        List<Employee> employees = position.getEmployees();
        employees.add(employee);

        List<Employee> employees2 = position2.getEmployees();
        employees2.add(employee2);

        position.setEmployees(employees);
        position2.setEmployees(employees2);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.merge(position);
        entityManager.merge(position2);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
