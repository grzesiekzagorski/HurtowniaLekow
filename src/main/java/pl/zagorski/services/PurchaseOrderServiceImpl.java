package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.repositories.PurchaseOrderDao;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderImpl{

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;


    @Override
    @Transactional
    public void save(PurchaseOrder purchaseOrder) {
        purchaseOrderDao.save(purchaseOrder);
    }

    @Override
    @Transactional
    public void edit(PurchaseOrder purchaseOrder) {
        purchaseOrderDao.edit(purchaseOrder);
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
    public List<Object[]> orderByName() {
        return purchaseOrderDao.orderByName();
    }

    @Override
    public List<Object[]> showPurchaseOrdersByName(String name) {
        return purchaseOrderDao.showPurchaseOrdersByName(name);
    }

    @Override
    public List<Object[]> showPurchaseOrderById(int id) {
        return purchaseOrderDao.showPurchaseOrderById(id);
    }

    @Override
    public List<Object[]> showAllPurchaseOrders() {
        return purchaseOrderDao.showAllPurchaseOrders();
    }
}
