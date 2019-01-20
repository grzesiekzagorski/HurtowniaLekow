package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Medicine;
import pl.zagorski.repositories.CharacterDao;
import pl.zagorski.repositories.MedicineDao;
import pl.zagorski.repositories.PrescriptionDao;
import pl.zagorski.repositories.ProducerDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineImpl {

    @Autowired
    private MedicineDao medicineDao;
    @Autowired
    private PrescriptionDao prescriptionDao;
    @Autowired
    private CharacterDao characterDao;
    @Autowired
    private ProducerDao producerDao;

    public List<String[]> convertObjectListToStringList(List<Object[]> objects) {
        List<String[]> strings = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Object[] tab = objects.get(i);
            String[] tabString = new String[tab.length];
            for (int j = 0; j < tab.length; j++) {
                tabString[j] = tab[j].toString();
            }
            strings.add(tabString);
        }
        return strings;
    }


    @Override
    @Transactional
    public void save(int idPrescription, int idCharacter, int idProducer, String name, double price, double discount,
                     String portion, String wrapping) {
        Medicine medicine = new Medicine();
        medicine.setPrescription(prescriptionDao.findOne(idPrescription));
        medicine.setCharacter(characterDao.findOne(idCharacter));
        medicine.setProducer(producerDao.findOne(idProducer));
        medicine.setName(name);
        medicine.setPrice(price);
        medicine.setDiscount(discount);
        medicine.setPortion(portion);
        medicine.setWrapping(wrapping);
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
    public List<String[]> showMedicineByIdOrName(String id, String name) {
        List<String[]> result = new ArrayList<>();
        if (id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(medicineDao.showAllMedicines());
        } else if (!id.isEmpty()) {
            result = convertObjectListToStringList(medicineDao.showMedicineById(Integer.parseInt(id)));
        } else if (!name.isEmpty()) {
            result = convertObjectListToStringList(medicineDao.showMedicineByName(name));
        } else {
            result = convertObjectListToStringList(medicineDao.showMedicineByIdAndName(Integer.parseInt(id), name));
        }
        return result;
    }

    @Override
    public List<String[]> showAllMedicinesOrderByName() {
        List<String[]> result = convertObjectListToStringList(medicineDao.showAllMedicinesOrderByName());
        return result;
    }

    @Override
    public List<String[]> showAllMedicines() {
        List<String[]> result = convertObjectListToStringList(medicineDao.showAllMedicines());
        return result;
    }
}
