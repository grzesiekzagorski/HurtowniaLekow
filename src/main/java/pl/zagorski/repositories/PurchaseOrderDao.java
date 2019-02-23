package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.exceptions.ExceptionSample;

import java.util.List;

public interface PurchaseOrderDao {
    void save(PurchaseOrder purchaseOrder);
    void edit(PurchaseOrder purchaseOrder) throws ExceptionSample;
    void delete(PurchaseOrder purchaseOrder)throws ExceptionSample;
    List<PurchaseOrder> findAll();
    PurchaseOrder findOne(int id);
    List<Object[]> orderByName();
    List<Object[]> showPurchaseOrdersByName(@Param("name") String name);
    List<Object[]> showPurchaseOrderById(@Param("id") int id);
    List<Object[]> showAllPurchaseOrders();
    List<Object[]> showAllPurchaseOrdersThatAreNotDelivered();
    List<Object[]> showPurchaseOrderByIdAndName(@Param("id")int id,@Param("name")String name);


}
