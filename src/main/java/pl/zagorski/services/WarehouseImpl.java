package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Employee;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.domain.Warehouse;

import java.sql.Date;
import java.util.List;

public interface WarehouseImpl {
    void save(int amount, Date delivery_date, Date expiration_date, Employee employee, PurchaseOrder purchase_order);
    void edit(Warehouse warehouse);
    void delete(int deleteWarehouse);
    List<Warehouse> findAll();
    Warehouse findOne(int id);
    List<Object[]> showAllWarehouses();
    List<String[]> showAllWarehousesOrderByIdOrMedicineName(@Param("id")String id,@Param("name")String name);
    List<Object[]> showAllWarehousesOrderByWarehouseAmount();
    List<String[]> convertObjectListToStringList(List<Object[]> objects);
    List<String[]> showWarehouseWhereStatusEqualsInStockOrOnSold();
    List<String[]> showWarehouseWhereStatusEqualsInStock();

}
