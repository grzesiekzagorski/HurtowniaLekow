package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderImpl {
    void save(int idMedicine,int idSupplier,int amount,String userLogin);
    void edit(int idOrderEdit,int idMedicineEdit,int idSupplierEdit,int amountEdit,String userLogin);
    void delete(int orderDelete);
    List<PurchaseOrder> findAll();
    PurchaseOrder findOne(int id);
    List<String[]> orderByName();
    List<String[]> convertObjectListToStringList(List<Object[]> objects);
    List<String[]> showAllPurchaseOrders();
    List<String[]> showAllPurchaseOrdersThatAreNotDelivered();
    List<String[]> showPurchaseOrderByIdOrName(@Param("id")String id,@Param("name")String name);


}
