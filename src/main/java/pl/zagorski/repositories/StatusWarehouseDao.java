package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Status;
import pl.zagorski.domain.StatusWarehouse;

import java.util.List;

public interface StatusWarehouseDao {
    void save(StatusWarehouse statusWarehouse);
    void edit(StatusWarehouse statusWarehouse);
    List<StatusWarehouse> findAll();
    StatusWarehouse findOne(int id);
    List<StatusWarehouse> orderByName();
    StatusWarehouse getStatusByName(@Param("name") String name);
    StatusWarehouse getInStockStatus();
    StatusWarehouse getOnSaleStatus();
    StatusWarehouse getSoldStatus();


}
