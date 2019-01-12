package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderImpl {
    void save(PurchaseOrder purchaseOrder);
    void edit(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> findAll();
    PurchaseOrder findOne(int id);
    List<Object[]> orderByName();
    List<Object[]> showPurchaseOrdersByName(@Param("name") String name);
    List<Object[]> showPurchaseOrderById(@Param("id") int id);
    List<Object[]> showAllPurchaseOrders();


}