package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Medicine;
import pl.zagorski.repositories.MedicineDao;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineImpl {

    @Autowired
    private MedicineDao medicineDao;


    @Override
    @Transactional
    public void save(Medicine medicine) {
        medicineDao.save(medicine);
    }

    @Override
    @Transactional
    public void edit(Medicine medicine) {
        medicineDao.edit(medicine);
    }

    @Override
    public List<Medicine> findAll() {
        return medicineDao.findAll();
    }

    @Override
    public Medicine findOne(int id) {
        return medicineDao.findOne(id);
    }

    @Override
    public List<Medicine> orderByName() {
        return medicineDao.orderByName();
    }

    @Override
    public Medicine getMedicineByName(String name) {
        return medicineDao.getMedicineByName(name);
    }

    @Override
    public List<Object[]> showAllMedicinesOrderByName() {
        return medicineDao.showAllMedicinesOrderByName();
    }

    @Override
    public List<Object[]> showAllMedicinesOrderById() {
        return medicineDao.showAllMedicinesOrderById();
    }

    @Override
    public List<Object[]> showAllMedicines() {
        return medicineDao.showAllMedicines();
    }
}
