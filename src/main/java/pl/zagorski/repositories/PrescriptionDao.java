package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Prescription;
import pl.zagorski.domain.Producer;

import java.util.List;

public interface PrescriptionDao {
    void save(Prescription prescription);
    void edit(Prescription prescription);
    List<Prescription> findAll();
    Prescription findOne(int id);
    List<Prescription> orderByName();
    Prescription getPrescriptionByName(@Param("name") String name);


}
