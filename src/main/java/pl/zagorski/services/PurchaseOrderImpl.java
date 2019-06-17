package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.exceptions.ExceptionSample;

import java.util.List;

public interface PurchaseOrderImpl {
    void save(int idMedicine,int idSupplier,int amount,String userLogin) throws ExceptionSample;
    void edit(int idOrderEdit,int idMedicineEdit,int idSupplierEdit,int amountEdit,String userLogin) throws ExceptionSample;
    void delete(int orderDelete) throws ExceptionSample;
    List<PurchaseOrder> findAll();
    PurchaseOrder findOne(int id);
    List<String[]> orderByName();
    List<String[]> convertObjectListToStringList(List<Object[]> objects);
    List<String[]> convertObjectListToStringListFirstVersion(List<Object[]> objects);
    List<String[]> showAllPurchaseOrders();
    List<String[]> showAllPurchaseOrdersThatAreNotDelivered();
    List<String[]> showPurchaseOrderByIdOrName(@Param("id")String id,@Param("name")String name);


}
