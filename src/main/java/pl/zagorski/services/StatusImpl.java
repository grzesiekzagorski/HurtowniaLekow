package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Status;

import java.util.List;

public interface StatusImpl {
    void save(Status status);
    void edit(Status status);
    List<Status> findAll();
    Status findOne(int id);
    List<Status> orderByName();
    Status getStatusByName(@Param("name") String name);


}
