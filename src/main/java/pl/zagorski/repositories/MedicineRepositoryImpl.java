package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("MedicineDao")
public class MedicineRepositoryImpl implements MedicineDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Medicine medicine) {
        em.persist(medicine);
        em.flush();
    }

    @Override
    public void edit(Medicine medicine) {
        em.merge(medicine);
        em.flush();
    }

    @Override
    public List<Medicine> findAll() {
        TypedQuery<Medicine> q = em.createQuery("Select c from Medicine c", Medicine.class);
        return q.getResultList();
    }

    @Override
    public Medicine findOne(int id) {
        Medicine medicine = em.find(Medicine.class, id);
        return medicine;
    }

    @Override
    public List<Medicine> orderByName() {
        TypedQuery<Medicine> q = em.createQuery("Select c from Medicine c order by name", Medicine.class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showMedicineById(int id) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.id,m.name,(concat(m.price,' zł')),concat((m.discount*100),'%'),m.portion,p.name,c.name,m.wrapping," +
                "pr.name FROM Medicine m JOIN m.character c JOIN m.prescription p JOIN m.producer pr WHERE m.id = :id ", Object[].class);
        return q.setParameter("id",id).getResultList();
    }

    @Override
    public List<Object[]> showMedicineByName(String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.id,m.name,(concat(m.price,' zł')),concat((m.discount*100),'%'),m.portion,p.name,c.name,m.wrapping," +
                "pr.name FROM Medicine m JOIN m.character c JOIN m.prescription p JOIN m.producer pr WHERE m.name = :name ", Object[].class);
        return q.setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showMedicineByIdAndName(int id, String name) {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.id,m.name,(concat(m.price,' zł')),concat((m.discount*100),'%'),m.portion,p.name,c.name,m.wrapping," +
                "pr.name FROM Medicine m JOIN m.character c JOIN m.prescription p JOIN m.producer pr WHERE m.id = :id AND m.name = :name ", Object[].class);
        return q.setParameter("id",id).setParameter("name",name).getResultList();
    }


    @Override
    public List<Object[]> showAllMedicinesOrderByName() {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.id,m.name,(concat(m.price,' zł')),concat((m.discount*100),'%'),m.portion,p.name,c.name,m.wrapping," +
                "pr.name FROM Medicine m JOIN m.character c JOIN m.prescription p JOIN m.producer pr order by m.name", Object[].class);
        return q.getResultList();
    }

    @Override
    public List<Object[]> showAllMedicines() {
        TypedQuery<Object[]> q = em.createQuery("SELECT m.id,m.name,(concat(m.price,' zł')),concat((m.discount*100),'%'),m.portion,p.name,c.name,m.wrapping," +
                "pr.name FROM Medicine m JOIN m.character c JOIN m.prescription p JOIN m.producer pr order by m.id", Object[].class);
        return q.getResultList();
    }


}


