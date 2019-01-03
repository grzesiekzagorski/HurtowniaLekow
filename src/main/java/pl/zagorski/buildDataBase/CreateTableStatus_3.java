package pl.zagorski.buildDataBase;

import pl.zagorski.domain.Prescription;
import pl.zagorski.domain.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTableStatus_3 {
    public static void main(String[]args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Status status = new Status();
        status.setName("złożony");
        Status status1 = new Status();
        status1.setName("dostarczony");

        entityManager.getTransaction().begin();
        entityManager.persist(status);
        entityManager.persist(status1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
