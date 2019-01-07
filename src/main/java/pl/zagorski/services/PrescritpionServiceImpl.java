package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Prescription;
import pl.zagorski.repositories.PrescriptionDao;

import java.util.List;

@Service
public class PrescritpionServiceImpl implements PrescriptionImpl {

    @Autowired
    PrescriptionDao prescriptionDao;


    @Override
    @Transactional
    public void save(Prescription prescription) {
        prescriptionDao.save(prescription);
    }

    @Override
    @Transactional
    public void edit(Prescription prescription) {
        prescriptionDao.edit(prescription);
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionDao.findAll();
    }

    @Override
    public Prescription findOne(int id) {
        return prescriptionDao.findOne(id);
    }

    @Override
    public List<Prescription> orderByName() {
        return prescriptionDao.orderByName();
    }

    @Override
    public Prescription getPrescriptionByName(String name) {
        return prescriptionDao.getPrescriptionByName(name);
    }
}
