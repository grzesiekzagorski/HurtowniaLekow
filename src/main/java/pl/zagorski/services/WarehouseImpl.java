package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Warehouse;

import java.util.List;

public interface WarehouseImpl {
    void save(Warehouse warehouse);
    void edit(Warehouse warehouse);
    List<Warehouse> findAll();
    Warehouse findOne(int id);
    List<Object[]> showAllWarehouses();
    List<Object[]> showAllWarehousesOrderByMedicineName();
    List<Object[]> showAllWarehousesOrderByWarehouseAmount();
    List<Object[]> showWarehousesByMedicineName(@Param("name") String name);
    List<Object[]> showWarehouseById(@Param("id") int id);

}
