package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Delivery;

import java.util.List;

public interface DeliveryImpl {
    void save(String expirationDate,int idOrderAddDelivery,String username);
    void edit(Delivery delivery);
    List<Delivery> findAll();
    Delivery findOne(int id);
    List<String[]> showAllDeliveries();
    List<String[]> showAllDeliveriesOrderByMedicineName();
    List<String[]> showAllDeliveriesOrderByDeliveryAmount();
    List<String[]> showDeliveriesByMedicineName(@Param("name") String name);
    List<String[]> showDeliveryById(@Param("id") int id);
    List<String[]> convertObjectListToStringList(List<Object[]> objects);

}
