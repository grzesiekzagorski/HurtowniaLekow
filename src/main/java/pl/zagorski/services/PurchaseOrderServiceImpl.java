package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.repositories.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderImpl{

    private static final String COMPOSITE_STATUS = "złożony";

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;
    @Autowired
    private StatusRepositoryImpl statusRepository;
    @Autowired
    private MedicineRepositoryImpl medicineRepository;
    @Autowired
    private EmployeeRepositoryImpl employeeRepository;
    @Autowired
    private SupplierRepositoryImpl supplierRepository;

    public List<String[]> convertObjectListToStringList(List<Object[]> objects) {
        List<String[]> strings = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Object[] tab = objects.get(i);
            String[] tabString = new String[tab.length -1];
            for (int j = 0; j < tab.length - 1; j++) {
                tabString[j] = tab[j].toString();
                if(j == tab.length - 2){
                    tabString[j] = tab[j].toString()+" "+tab[j+1].toString();
                }
            }
            strings.add(tabString);
        }
        return strings;
    }
    public List<String[]> convertObjectListToStringListFirstVersion(List<Object[]> objects) {
        List<String[]> strings = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Object[] tab = objects.get(i);
            String[] tabString = new String[tab.length];
            for (int j = 0; j < tab.length; j++) {
                tabString[j] = tab[j].toString();
            }
            strings.add(tabString);
        }
        return strings;
    }


    @Override
    @Transactional
    public void save(int idMedicine,int idSupplier,int amount,String userLogin) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setAmount(amount);
        purchaseOrder.setStatus(statusRepository.getStatusByName(COMPOSITE_STATUS));
        purchaseOrder.setMedicine(medicineRepository.findOne(idMedicine));
        purchaseOrder.setSupplier(supplierRepository.findOne(idSupplier));
        purchaseOrder.setEmployee(employeeRepository.getEmployeeByLogin(userLogin).get());
        long time = System.currentTimeMillis();
        purchaseOrder.setDate_of_order(new Date(time));
        purchaseOrderDao.save(purchaseOrder);
    }

    @Override
    @Transactional
    public void edit(int idOrderEdit,int idMedicineEdit,int idSupplierEdit,int amountEdit,String userLogin) {
        PurchaseOrder purchaseOrder = purchaseOrderDao.findOne(idOrderEdit);
        long time = System.currentTimeMillis();
        purchaseOrder.setDate_of_order(new Date(time));
        purchaseOrder.setMedicine(medicineRepository.findOne(idMedicineEdit));
        purchaseOrder.setSupplier(supplierRepository.findOne(idSupplierEdit));
        purchaseOrder.setEmployee(employeeRepository.getEmployeeByLogin(userLogin).get());
        purchaseOrder.setAmount(amountEdit);
        purchaseOrderDao.edit(purchaseOrder);
    }

    @Override
    @Transactional
    public void delete(int orderDelete) {
        purchaseOrderDao.delete(purchaseOrderDao.findOne(orderDelete));
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderDao.findAll();
    }

    @Override
    public PurchaseOrder findOne(int id) {
        return purchaseOrderDao.findOne(id);
    }

    @Override
    public List<String[]> orderByName() {
        List<String[]> strings = convertObjectListToStringList(purchaseOrderDao.orderByName());
        return strings;
    }

    @Override
    public List<String[]> showAllPurchaseOrders() {
        List<String[]> strings = convertObjectListToStringList(purchaseOrderDao.showAllPurchaseOrders());
        return strings;
    }

    @Override
    public List<String[]> showAllPurchaseOrdersThatAreNotDelivered() {
        List<String[]> strings = convertObjectListToStringListFirstVersion(purchaseOrderDao.showAllPurchaseOrdersThatAreNotDelivered());
        return strings;
    }

    @Override
    public List<String[]> showPurchaseOrderByIdOrName(String id, String name) {
        List<String[]> result;
        if (id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(purchaseOrderDao.showAllPurchaseOrders());
        } else if (!id.isEmpty() && name.isEmpty()) {
            result = convertObjectListToStringList(purchaseOrderDao.showPurchaseOrderById(Integer.parseInt(id)));
        } else if (id.isEmpty() && !name.isEmpty()) {
            result = convertObjectListToStringList(purchaseOrderDao.showPurchaseOrdersByName(name));
        } else{
            result = convertObjectListToStringList(purchaseOrderDao.showPurchaseOrderByIdAndName(Integer.parseInt(id), name));
        }
        return result;
    }


}
