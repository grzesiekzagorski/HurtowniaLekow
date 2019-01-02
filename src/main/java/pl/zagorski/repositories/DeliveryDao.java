package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Action;
import pl.zagorski.domain.Delivery;

import java.util.List;

public interface DeliveryDao {
    void save(Delivery delivery);
    void edit(Delivery delivery);
    List<Delivery> findAll();
    Delivery findOne(int id);
    List<Delivery> orderByName();
    Delivery getDeliveryByName(@Param("name") String name);
//    List<Action> getActionByPosition(@Param("name")String name);


}
