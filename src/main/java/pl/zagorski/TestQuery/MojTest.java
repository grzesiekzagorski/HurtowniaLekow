package pl.zagorski.TestQuery;

import pl.zagorski.domain.Character;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.Prescription;
import pl.zagorski.domain.Producer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MojTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Character character = new Character();
//        character.setName("tabletki");
//
//        Producer producer = new Producer();
//        producer.setName("Polfa Tarchomin");
//
//        Prescription prescription = new Prescription();
//        prescription.setName("Tak");



        Medicine medicine = new Medicine();
        medicine.setDiscount(0.25);
        medicine.setName("Acidolac Baby");
        medicine.setPrice(39.90);
        medicine.setWrapping("8 tabl.");
        medicine.setPortion("0.4g");
        Character character = entityManager.find(Character.class,1);
        Prescription prescription = entityManager.find(Prescription.class,1);
        Producer producer = entityManager.find(Producer.class,1);
        medicine.setCharacter(character);
        medicine.setPrescription(prescription);
        medicine.setProducer(producer);

        entityManager.getTransaction().begin();
        entityManager.persist(character);
        entityManager.persist(prescription);
        entityManager.persist(producer);
        entityManager.persist(medicine);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}
