package pl.zagorski.TestDataBase;

import pl.zagorski.domain.Character;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.Prescription;
import pl.zagorski.domain.Producer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MedicineTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Medicine medicine = new Medicine();
        medicine.setName("Davercin");
        medicine.setPrice(21.63);
        medicine.setDiscount(0);
        medicine.setWrapping("16 tabl.");
        medicine.setPortion("250mg");

        Character character = entityManager.find(Character.class, 1);
        Prescription prescription = entityManager.find(Prescription.class, 1);
        Producer producer = entityManager.find(Producer.class, 1);

        List<Medicine> medicineCharacter = character.getMedicines();
        medicineCharacter.add(medicine);

        List<Medicine> medicinePrescription = prescription.getMedicines();
        medicinePrescription.add(medicine);

        List<Medicine> medicineProducer = producer.getMedicines();
        medicineProducer.add(medicine);

        character.setMedicines(medicineCharacter);
        prescription.setMedicines(medicinePrescription);
        producer.setMedicines(medicineProducer);

        entityManager.getTransaction().begin();
        entityManager.persist(medicine);
        entityManager.merge(character);
        entityManager.merge(prescription);
        entityManager.merge(producer);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
