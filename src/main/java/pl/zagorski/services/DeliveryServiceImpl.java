package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Delivery;
import pl.zagorski.domain.PurchaseOrder;
import pl.zagorski.repositories.DeliveryDao;
import pl.zagorski.repositories.EmployeeRepositoryImpl;
import pl.zagorski.repositories.PurchaseOrderRepositoryImpl;
import pl.zagorski.repositories.StatusRepositoryImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryImpl {

    private static final String DELIVERED_STATUS = "dostarczony";

    @Autowired
    private DeliveryDao deliveryDao;
    @Autowired
    private EmployeeRepositoryImpl employeeRepository;
    @Autowired
    private PurchaseOrderRepositoryImpl purchaseOrderRepository;
    @Autowired
    private StatusRepositoryImpl statusRepository;



    public List<String[]> convertObjectListToStringList(List<Object[]> objects) {
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
    public void save(String expirationDate, int idOrderAddDelivery, String userLogin) {
        Delivery delivery = new Delivery();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        delivery.setExpiration_date(sql);

        long time = System.currentTimeMillis();
        delivery.setDelivery_date(new Date(time));


        delivery.setEmployee(employeeRepository.getEmployeeByLogin(userLogin).get());

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(idOrderAddDelivery);
        purchaseOrder.setStatus(statusRepository.getStatusByName(DeliveryServiceImpl.DELIVERED_STATUS));
        delivery.setOrder(purchaseOrder);

        purchaseOrderRepository.edit(purchaseOrder);
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
    public List<String[]> showAllDeliveries() {
        List<String[]> strings = convertObjectListToStringList(deliveryDao.showAllDeliveries());
        return strings;
    }

    @Override
    public List<String[]> showAllDeliveriesOrderByMedicineName() {
        List<String[]> strings = convertObjectListToStringList(deliveryDao.showAllDeliveriesOrderByMedicineName());
        return strings;
    }

    @Override
    public List<String[]> showAllDeliveriesOrderByDeliveryAmount() {
        List<String[]> strings = convertObjectListToStringList(deliveryDao.showAllDeliveriesOrderByDeliveryAmount());
        return strings;
    }

    @Override
    public List<String[]> showDeliveriesByMedicineName(String name) {
        List<String[]> strings = convertObjectListToStringList(deliveryDao.showDeliveriesByMedicineName(name));
        return strings;
    }

    @Override
    public List<String[]> showDeliveryById(int id) {
        List<String[]> strings = convertObjectListToStringList(deliveryDao.showDeliveryById(id));
        return strings;
    }
}
