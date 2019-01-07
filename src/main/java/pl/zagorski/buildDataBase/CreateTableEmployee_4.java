package pl.zagorski.buildDataBase;

import pl.zagorski.domain.Employee;
import pl.zagorski.domain.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTableEmployee_4 {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Position position = entityManager.find(Position.class,2);
        Position position1 = entityManager.find(Position.class,3);

        Employee employee = new Employee();
        employee.setName("Jan");
        employee.setSurname("Kowalski");
        employee.setLogin("jan.kowalski");
        employee.setPassword("kowalski.j");
        employee.setPosition(position);

        Employee employee1 = new Employee();
        employee1.setName("Nadia");
        employee1.setSurname("Waleriewa");
        employee1.setLogin("nadia.waleriewa");
        employee1.setPassword("waleriewa.n");
        employee1.setPosition(position1);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(employee1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}
