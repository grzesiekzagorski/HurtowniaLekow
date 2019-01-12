package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Producer;

import java.util.List;

public interface ProducerImpl {
    void save(Producer producer);
    void edit(Producer producer);
    List<Producer> findAll();
    Producer findOne(int id);
    List<Producer> orderByName();
    Producer getProducerByName(@Param("name") String name);

}
