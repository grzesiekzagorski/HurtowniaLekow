package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Employee;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.domain.WarehouseRest;
import pl.zagorski.exceptions.ExceptionSample;

import java.sql.Date;
import java.util.List;

public interface WarehouseImpl {
    void save(int amount, Date delivery_date, Date expiration_date, Employee employee, PurchaseOrder purchase_order);
    void edit(Warehouse warehouse);
    void delete(int deleteWarehouse) throws ExceptionSample;
    void sell(int amount,int idClient,String userLogin,int idWarehouse) throws ExceptionSample;
    List<Warehouse> findAll();
    Warehouse findOne(int id);
    List<Object[]> showAllWarehouses();
    List<String[]> showAllWarehousesOrderByIdOrMedicineName(@Param("id")String id,@Param("name")String name);
    List<Object[]> showAllWarehousesOrderByWarehouseAmount();
    List<String[]> convertObjectListToStringList(List<Object[]> objects);
    List<String[]> showWarehouseWhereStatusEqualsInStockOrOnSold();
    List<String[]> showWarehouseWhereStatusEqualsInStock();
    List<String[]> convertObjectListToStringListFirstVersion(List<Object[]> objects);
    List<WarehouseRest> showWarehouseRestObjects();

}
