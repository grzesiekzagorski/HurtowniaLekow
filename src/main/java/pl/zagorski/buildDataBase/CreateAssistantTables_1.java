package pl.zagorski.buildDataBase;

import pl.zagorski.domain.*;
import pl.zagorski.domain.Character;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CreateAssistantTables_1 {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Adding entries to the table Character

        Character character = new Character();
        character.setName("tabletki powlekane");
        Character character1 = new Character();
        character1.setName("tabletki");
        Character character2 = new Character();
        character2.setName("maść");
        Character character3 = new Character();
        character3.setName("proszek");
        Character character4 = new Character();
        character4.setName("pastylki");
        Character character5 = new Character();
        character5.setName("aerozol");

        //Adding entries to the table Producer

        Producer producer = new Producer();
        producer.setName("Polfa Tarchomin");
        Producer producer1 = new Producer();
        producer1.setName("Teva Pharmaveuticals Polska");
        Producer producer2 = new Producer();
        producer2.setName("GlaxoSmithKline-GB");
        Producer producer3 = new Producer();
        producer3.setName("Medana");
        Producer producer4 = new Producer();
        producer4.setName("Krka");
        Producer producer5 = new Producer();
        producer5.setName("Hexanova");

        //Adding entries to the table Prescription

        Prescription prescription = new Prescription();
        prescription.setName("Tak");
        Prescription prescription1 = new Prescription();
        prescription1.setName("Nie");

        //Adding entries to the table Medicine

        Medicine medicine = new Medicine();
        medicine.setName("Davercin");
        medicine.setPrice(21.63);
        medicine.setDiscount(0);
        medicine.setWrapping("16 tabl.");
        medicine.setPortion("250mg");
        medicine.setProducer(producer2);
        medicine.setPrescription(prescription);
        medicine.setCharacter(character4);

        //Adding entries to the table Action

        Action action1 = new Action();
        action1.setName("baza leków");
        Action action2 = new Action();
        action2.setName("baza producentów");
        Action action3 = new Action();
        action3.setName("zamówienia");
        Action action4 = new Action();
        action4.setName("stan magazynowy");
        Action action5 = new Action();
        action5.setName("dostawy");
        Action action6 = new Action();
        action6.setName("sprzedaże");

        //Adding entries to the table Position

        Position position1 = new Position();
        position1.setName("dział obsługi klienta");
        Position position2 = new Position();
        position2.setName("magazynier");
        Position position3 = new Position();
        position3.setName("kierownik");

        List<Position> bazaLekow = new ArrayList<>();
        List<Position> bazaProducentow = new ArrayList<>();
        List<Position> zamowienia = new ArrayList<>();
        List<Position> stanMagazynowy = new ArrayList<>();
        List<Position> dostawy = new ArrayList<>();
        List<Position> sprzedaze = new ArrayList<>();

        bazaLekow.add(position1);
        bazaProducentow.add(position1);
        zamowienia.add(position1);
        stanMagazynowy.add(position1);
        sprzedaze.add(position1);

        bazaLekow.add(position3);
        bazaProducentow.add(position3);
        zamowienia.add(position3);
        stanMagazynowy.add(position3);
        dostawy.add(position3);
        sprzedaze.add(position3);

        stanMagazynowy.add(position2);

        action1.setPositions(bazaLekow);
        action2.setPositions(bazaProducentow);
        action3.setPositions(zamowienia);
        action4.setPositions(stanMagazynowy);
        action5.setPositions(dostawy);
        action6.setPositions(sprzedaze);



        entityManager.getTransaction().begin();
        entityManager.persist(character);
        entityManager.persist(character1);
        entityManager.persist(character2);
        entityManager.persist(character3);
        entityManager.persist(character4);
        entityManager.persist(character5);

        entityManager.persist(producer);
        entityManager.persist(producer1);
        entityManager.persist(producer2);
        entityManager.persist(producer3);
        entityManager.persist(producer4);
        entityManager.persist(producer5);

        entityManager.persist(prescription);
        entityManager.persist(prescription1);

        entityManager.persist(medicine);

        entityManager.persist(position1);
        entityManager.persist(position2);
        entityManager.persist(position3);

        entityManager.persist(action1);
        entityManager.persist(action2);
        entityManager.persist(action3);
        entityManager.persist(action4);
        entityManager.persist(action5);
        entityManager.persist(action6);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
