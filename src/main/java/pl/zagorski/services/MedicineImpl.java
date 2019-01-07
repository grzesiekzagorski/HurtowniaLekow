package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Medicine;

import java.util.List;

public interface MedicineImpl {
    void save(Medicine medicine);
    void edit(Medicine medicine);
    List<Medicine> findAll();
    Medicine findOne(int id);
    List<Medicine> orderByName();
    Medicine getMedicineByName(@Param("name") String name);
    List<Object[]> showAllMedicinesOrderByName();
    List<Object[]> showAllMedicinesOrderById();


}
