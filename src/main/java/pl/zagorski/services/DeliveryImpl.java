package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Delivery;

import java.util.List;

public interface DeliveryImpl {
    void save(Delivery delivery);
    void edit(Delivery delivery);
    List<Delivery> findAll();
    Delivery findOne(int id);
    List<Object[]> showAllDeliveries();
    List<Object[]> showAllDeliveriesOrderByMedicineName();
    List<Object[]> showAllDeliveriesOrderByDeliveryAmount();
    List<Object[]> showDeliveriesByMedicineName(@Param("name") String name);
    List<Object[]> showDeliveryById(@Param("id") int id);

}
