package pl.zagorski.buildDataBase;

import pl.zagorski.domain.Character;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.Prescription;
import pl.zagorski.domain.Producer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTableMedicine_2 {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Producer producer = entityManager.find(Producer.class,4);
        Character character = entityManager.find(Character.class,3);
        Prescription prescription = entityManager.find(Prescription.class,1);

        Medicine medicine = new Medicine();
        medicine.setName("Finlepsin 200");
        medicine.setPrice(13.76);
        medicine.setDiscount(0.48);
        medicine.setWrapping("50 tabl.");
        medicine.setPortion("200mg");
        medicine.setProducer(producer);
        medicine.setPrescription(prescription);
        medicine.setCharacter(character);

        Producer producer1 = entityManager.find(Producer.class,2);
        Character character1 = entityManager.find(Character.class,5);
        Prescription prescription1 = entityManager.find(Prescription.class,2);

        Medicine medicine1 = new Medicine();
        medicine1.setName("Bactroban");
        medicine1.setPrice(27.89);
        medicine1.setDiscount(0);
        medicine1.setWrapping("15g");
        medicine1.setPortion("20mg/g");
        medicine1.setProducer(producer1);
        medicine1.setPrescription(prescription1);
        medicine1.setCharacter(character1);

        entityManager.getTransaction().begin();
        entityManager.persist(medicine);
        entityManager.persist(medicine1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
