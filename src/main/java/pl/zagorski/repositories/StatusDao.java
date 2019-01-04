package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Status;

import java.util.List;

public interface StatusDao {
    void save(Status status);
    void edit(Status status);
    List<Status> findAll();
    Status findOne(int id);
    List<Status> orderByName();
    Status getStatusByName(@Param("name") String name);


}
