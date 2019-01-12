package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Supplier;
import pl.zagorski.repositories.SupplierDao;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierImpl{

    @Autowired
    private SupplierDao supplierDao;


    @Override
    @Transactional
    public void save(Supplier supplier) {
        supplierDao.save(supplier);
    }

    @Override
    @Transactional
    public void edit(Supplier supplier) {
        supplierDao.edit(supplier);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }

    @Override
    public Supplier findOne(int id) {
        return supplierDao.findOne(id);
    }

    @Override
    public List<Supplier> orderByName() {
        return supplierDao.orderByName();
    }

    @Override
    public List<Supplier> getSuppliersByName(String name) {
        return supplierDao.getSuppliersByName(name);
    }

    @Override
    public List<Object[]> showAllSuppliers() {
        return supplierDao.showAllSuppliers();
    }

    @Override
    public List<Object[]> showSupplierByName(String name) {
        return supplierDao.showSupplierByName(name);
    }

    @Override
    public List<Object[]> showSupplierById(int id) {
        return supplierDao.showSupplierById(id);
    }
}
