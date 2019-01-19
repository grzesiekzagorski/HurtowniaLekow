package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Medicine;
import pl.zagorski.repositories.MedicineDao;

import java.util.ArrayList;
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
    public List<String[]> showAllMedicines() {
        List<Object[]> objects = medicineDao.showAllMedicines();
        List<String[]> strings = new ArrayList<>();

        for(int i=0;i<objects.size();i++){
            Object[] tab = objects.get(i);
            String[] tabString = new String[tab.length];
            for(int j=0;j<tab.length;j++){
                tabString[j] = tab[j].toString();
            }
            strings.add(tabString);
        }
        return strings;
    }
}
