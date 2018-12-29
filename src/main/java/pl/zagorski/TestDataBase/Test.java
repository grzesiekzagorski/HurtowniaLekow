package pl.zagorski.TestDataBase;

import pl.zagorski.domain.*;
import pl.zagorski.domain.Character;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Character character = new Character();
        character.setName("tabletki");

        Prescription prescription = new Prescription();
        prescription.setName("tak");

        Producer producer = new Producer();
        producer.setName("Polfa Tarchomin");

        ////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////////////
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Date parsed = null;
//        try {
//            parsed = format.parse("20110210");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//        Zamowienie order = new Zamowienie();
//        order.setAmount(180);
//        order.setDate_of_order(sql);

//////////////////////////////////////////////////////////////////////////////////////////

        Status status = new Status();
        status.setName("złożony");

        entityManager.getTransaction().begin();
        entityManager.persist(character);
        entityManager.persist(prescription);
        entityManager.persist(producer);

        entityManager.persist(position1);
        entityManager.persist(position2);
        entityManager.persist(position3);

        entityManager.persist(action1);
        entityManager.persist(action2);
        entityManager.persist(action3);
        entityManager.persist(action4);
        entityManager.persist(action5);
        entityManager.persist(action6);

        entityManager.persist(status);


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
