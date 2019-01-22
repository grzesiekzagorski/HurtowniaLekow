package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Delivery;
import pl.zagorski.repositories.DeliveryDao;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryImpl {

    @Autowired
    private DeliveryDao deliveryDao;

    @Override
    @Transactional
    public void save(Delivery delivery) {
        deliveryDao.save(delivery);
    }

    @Override
    @Transactional
    public void edit(Delivery delivery) {
        deliveryDao.edit(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryDao.findAll();
    }

    @Override
    public Delivery findOne(int id) {
        return deliveryDao.findOne(id);
    }

    @Override
    public List<Object[]> showAllDeliveries() {
        return deliveryDao.showAllDeliveries();
    }

    @Override
    public List<Object[]> showAllDeliveriesOrderByMedicineName() {
        return deliveryDao.showAllDeliveriesOrderByMedicineName();
    }

    @Override
    public List<Object[]> showAllDeliveriesOrderByDeliveryAmount() {
        return deliveryDao.showAllDeliveriesOrderByDeliveryAmount();
    }

    @Override
    public List<Object[]> showDeliveriesByMedicineName(String name) {
        return deliveryDao.showDeliveriesByMedicineName(name);
    }

    @Override
    public List<Object[]> showDeliveryById(int id) {
        return deliveryDao.showDeliveryById(id);
    }
}
