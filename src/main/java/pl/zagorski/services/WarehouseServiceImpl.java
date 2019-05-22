package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Employee;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.domain.WarehouseRest;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.repositories.StatusWarehouseDao;
import pl.zagorski.repositories.WarehouseDao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Service
public class WarehouseServiceImpl implements WarehouseImpl {

    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private StatusWarehouseDao statusWarehouseDao;
    @Autowired
    private SaleServiceImpl saleService;


    @Override
    @Transactional
    public void save(int amount, Date delivery_date, Date expiration_date, Employee employee, PurchaseOrder purchase_order) {
        Warehouse warehouse = new Warehouse();
        warehouse.setAmount(amount);
        warehouse.setDelivery_date(delivery_date);
        warehouse.setExpiration_date(expiration_date);
        warehouse.setEmployee(employee);
        warehouse.setOrder(purchase_order);
        warehouse.setStatus(statusWarehouseDao.getInStockStatus());
        warehouseDao.save(warehouse);
    }

    @Override
    @Transactional
    public void edit(Warehouse warehouse) {
        warehouseDao.edit(warehouse);
    }

    @Override
    @Transactional
    public void delete(int idPurchaseOrder) throws ExceptionSample {
        Warehouse warehouse = warehouseDao.findOneByPurchaseOrderId(idPurchaseOrder);
        warehouseDao.delete(warehouse);
    }

    @Override
    @Transactional
    public void sell(int amount, int idClient, String userLogin, int idWarehouse) throws ExceptionSample {
        final String statusInStock = "na magazynie";

        if(warehouseDao.findOne(idWarehouse) == null){
            throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
        }else{
            Warehouse warehouse = warehouseDao.findOne(idWarehouse);
            if(warehouse.getAmount() >= amount  && amount > 0) {
                int amountTmp = warehouse.getAmount() - amount;
                warehouse.setAmount(amountTmp);
                if (warehouse.getStatus().getName().equals(statusInStock)) {
                    warehouse.setStatus(statusWarehouseDao.getOnSaleStatus());
                }
                if (warehouse.getAmount() == 0) {
                    warehouse.setStatus(statusWarehouseDao.getSoldStatus());
                }
                warehouseDao.edit(warehouse);
                saleService.save(amount,idClient,userLogin,warehouse);
            }else{
                throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
            }
        }
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
    public List<String[]> showAllWarehousesOrderByIdOrMedicineName(String id, String name) {
        List<String[]> result;
        if (id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(warehouseDao.showWarehouseWhereStatusEqualsInStockOrOnSold());
        } else if (!id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(warehouseDao.showWarehouseById(Integer.parseInt(id)));
        } else if (id.isEmpty() && !name.isEmpty()) {
            result = convertObjectListToStringList(warehouseDao.showWarehousesByMedicineName(name));
        } else{
            result = convertObjectListToStringList(warehouseDao.showWarehouseByIdAndMedicineName(Integer.parseInt(id), name));
        }
        return result;
    }

    @Override
    public List<Object[]> showAllWarehousesOrderByWarehouseAmount() {
        return warehouseDao.showAllWarehousesOrderByWarehouseAmount();
    }

    @Override
    public List<String[]> showWarehouseWhereStatusEqualsInStockOrOnSold() {
        return convertObjectListToStringList(warehouseDao.showWarehouseWhereStatusEqualsInStockOrOnSold());
    }

    @Override
    public List<String[]> showWarehouseWhereStatusEqualsInStock() {
        return convertObjectListToStringList(warehouseDao.showWarehouseWhereStatusEqualsInStock());
    }

    public List<String[]> convertObjectListToStringList(List<Object[]> objects) {
        List<String[]> strings = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Object[] tab = objects.get(i);
            String[] tabString = new String[tab.length -1];
            for (int j = 0; j < tab.length - 1; j++) {
                tabString[j] = tab[j].toString();
                if(j == tab.length - 2){
                    tabString[j] = tab[j].toString()+" "+tab[j+1].toString();
                }
            }
            strings.add(tabString);
        }
        return strings;
    }

    public List<String[]> convertObjectListToStringListFirstVersion(List<Object[]> objects) {
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
    public List<WarehouseRest> showWarehouseRestObjects() {
        List<WarehouseRest> warehouseRestList = new ArrayList<>();
        if(convertObjectListToStringListFirstVersion(warehouseDao.showWarehouseWhereStatusEqualsInStockOrOnSoldRest()).size() > 0) {
            for (String[] strings : convertObjectListToStringListFirstVersion(warehouseDao.showWarehouseWhereStatusEqualsInStockOrOnSoldRest())) {
                WarehouseRest warehouseRest = new WarehouseRest();
                warehouseRest.setId(Integer.parseInt(strings[0]));
                warehouseRest.setDelivery_number(Integer.parseInt(strings[1]));
                warehouseRest.setNameOfMedicine(strings[2]);
                warehouseRest.setAmount(Integer.parseInt(strings[3]));
                warehouseRestList.add(warehouseRest);
            }
        }
        return warehouseRestList;
    }

}
