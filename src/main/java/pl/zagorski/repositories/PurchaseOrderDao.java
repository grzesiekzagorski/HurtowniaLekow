package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderDao {
    void save(PurchaseOrder purchaseOrder);
    void edit(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> findAll();
    PurchaseOrder findOne(int id);
    List<Object[]> orderByName();
    List<Object[]> showPurchaseOrdersByName(@Param("name") String name);
    List<Object[]> showAllPurchaseOrders();


}