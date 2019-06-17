package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Delivery;
import pl.zagorski.domain.Sale;
import pl.zagorski.domain.StatusWarehouse;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.repositories.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleImpl {

    @Autowired
    private SaleDao saleDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private StatusWarehouseDao statusWarehouseDao;

    @Override
    @Transactional
    public void save(int amount, int idClient, String userLogin, Warehouse warehouse) throws ExceptionSample {
        if(clientDao.findOne(idClient) != null){
            Sale sale = new Sale();
            sale.setAmount(amount);
            sale.setClient(clientDao.findOne(idClient));
            sale.setEmployee(employeeDao.getEmployeeByLogin(userLogin).get());
            long time = System.currentTimeMillis();
            sale.setSale_date(new Date(time));
            sale.setWarehouse(warehouse);
            saleDao.save(sale);
        }else{
            throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
        }

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

    @Override
    public List<String[]> showSaleByIdOrMedicineName(String  id, String name) {
        List<String[]> result;
        if (id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(saleDao.showAllSales());
        } else if (!id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(saleDao.showSaleById(Integer.parseInt(id)));
        } else if (id.isEmpty() && !name.isEmpty()) {
            result = convertObjectListToStringList(saleDao.showSalesByMedicineName(name));
        } else{
            result = convertObjectListToStringList(saleDao.showSaleByIdAndMedicineName(Integer.parseInt(id), name));
        }
        return result;
    }


    @Override
    @Transactional
    public void edit(Sale sale) {
        saleDao.edit(sale);
    }

    @Override
    @Transactional
    public void delete(int idSale) throws ExceptionSample {
        Sale sale = saleDao.findOne(idSale);
        if(sale != null){
            Warehouse warehouse = sale.getWarehouse();
            warehouse.setAmount(warehouse.getAmount() + sale.getAmount());
            warehouse.setStatus(statusWarehouseDao.getOnSaleStatus());
            for (Delivery delivery : warehouse.getOrder().getOrdersDelivery()) {
                if(delivery.getOrder().getId() == warehouse.getOrder().getId()){
                    if(delivery.getOrder().getAmount() == warehouse.getAmount()){
                        warehouse.setStatus(statusWarehouseDao.getInStockStatus());
                        break;
                    }
                }
            }
            warehouseDao.edit(warehouse);
            saleDao.delete(sale);
        }else{
            throw new ExceptionSample("Dostarczone dane nie mogą zostać uznane za prawidłowe.");
        }
    }

    @Override
    public List<Sale> findAll() {
        return saleDao.findAll();
    }

    @Override
    public Sale findOne(int id) {
        return saleDao.findOne(id);
    }

    @Override
    public List<String[]> showAllSales() {
        return convertObjectListToStringList(saleDao.showAllSales());
    }

    @Override
    public List<String[]> showAllSalesOrderByMedicineName() {
        return convertObjectListToStringList(saleDao.showAllSalesOrderByMedicineName());
    }

    @Override
    public List<String[]> showAllSalesOrderBySaleAmount() {
        return convertObjectListToStringList(saleDao.showAllSalesOrderBySaleAmount());
    }

    @Override
    public List<String[]> showSalesByMedicineName(String name) {
        return convertObjectListToStringList(saleDao.showSalesByMedicineName(name));
    }

    @Override
    public List<String[]> showSaleById(int id) {
        return convertObjectListToStringList(saleDao.showSaleById(id));
    }
}
