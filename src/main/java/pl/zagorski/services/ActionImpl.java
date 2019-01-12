package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Action;

import java.util.List;

public interface ActionImpl {
    void save(Action action);
    void edit(Action action);
    List<Action> findAll();
    Action findOne(int id);
    List<Action> orderByName();
    Action getActionByName(@Param("name") String name);
    List<String> showPositionsOfThisAction(@Param("name") String name);


}