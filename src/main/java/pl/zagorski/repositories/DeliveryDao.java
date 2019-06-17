package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Delivery;
import pl.zagorski.exceptions.ExceptionSample;

import java.util.List;

public interface DeliveryDao {
    void save(Delivery delivery);
    void edit(Delivery delivery);
    void delete(Delivery delivery);
    List<Delivery> findAll();
    Delivery findOne(int id);
    List<Object[]> showAllDeliveries();
    List<Object[]> showAllDeliveriesOrderByMedicineName();
    List<Object[]> showAllDeliveriesOrderByDeliveryAmount();
    List<Object[]> showDeliveriesByMedicineName(@Param("name") String name);
    List<Object[]> showDeliveryById(@Param("id") int id);
    List<Object[]> showDeliveryByIdAndMedicineName(@Param("id") int id,@Param("name") String name);
    List<Object[]> showDeliveryWhereItIsNotForSale();
}
