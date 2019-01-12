package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.repositories.WarehouseDao;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseImpl{

    @Autowired
    private WarehouseDao warehouseDao;


    @Override
    @Transactional
    public void save(Warehouse warehouse) {
        warehouseDao.save(warehouse);
    }

    @Override
    @Transactional
    public void edit(Warehouse warehouse) {
        warehouseDao.edit(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseDao.findAll();
    }

    @Override
    public Warehouse findOne(int id) {
        return warehouseDao.findOne(id);
    }

    @Override
    public List<Object[]> showAllWarehouses() {
        return warehouseDao.showAllWarehouses();
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByMedicineName() {
        return warehouseDao.showAllWarehousesOrderByMedicineName();
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByWarehouseAmount() {
        return warehouseDao.showAllWarehousesOrderByWarehouseAmount();
    }

    @Override
    public List<Object[]> showWarehousesByMedicineName(String name) {
        return warehouseDao.showWarehousesByMedicineName(name);
    }

    @Override
    public List<Object[]> showWarehouseById(int id) {
        return warehouseDao.showWarehouseById(id);
    }
}
