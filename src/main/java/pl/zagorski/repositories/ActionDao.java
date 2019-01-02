package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Action;
import pl.zagorski.domain.Client;

import java.util.List;

public interface ActionDao {
    void save(Action action);
    void edit(Action action);
    List<Action> findAll();
    Action findOne(int id);
    List<Action> orderByName();
    Action getActionByName(@Param("name") String name);
//    List<Action> getActionByPosition(@Param("name")String name);


}
