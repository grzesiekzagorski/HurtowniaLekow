package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Position;

import java.util.List;

public interface PositionImpl {
    void save(Position position);
    void edit(Position position);
    List<Position> findAll();
    Position findOne(int id);
    List<Position> orderByName();
    Position getPositionByName(@Param("name") String name);
}
