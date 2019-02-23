package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Warehouse;
import pl.zagorski.exceptions.ExceptionSample;

import java.util.List;

public interface WarehouseDao {
    void save(Warehouse warehouse);
    void edit(Warehouse warehouse);
    void delete(Warehouse warehouse)throws ExceptionSample;
    List<Warehouse> findAll();
    Warehouse findOne(@Param("id") int id);
    Warehouse findOneByPurchaseOrderId(@Param("id")int id);
    List<Object[]> showAllWarehouses();
    List<Object[]> showAllWarehousesOrderByMedicineName();
    List<Object[]> showAllWarehousesOrderByWarehouseAmount();
    List<Object[]> showWarehousesByMedicineName(@Param("name") String name);
    List<Object[]> showWarehouseById(@Param("id") int id);
    List<Object[]> showWarehouseByIdAndMedicineName(@Param("id") int id,@Param("name") String name);
    List<Object[]> showWarehouseWhereStatusEqualsInStock();
    List<Object[]> showWarehouseWhereStatusEqualsInStockOrOnSold();

}
