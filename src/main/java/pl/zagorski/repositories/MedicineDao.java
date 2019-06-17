package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Medicine;
import pl.zagorski.exceptions.ExceptionSample;

import java.util.List;

public interface MedicineDao {
    void save(Medicine medicine);
    void edit(Medicine medicine);
    void delete(Medicine medicine) throws ExceptionSample;
    List<Medicine> findAll();
    Medicine findOne(int id);
    List<Medicine> orderByName();
    List<Object[]> showMedicineById(@Param("id") int id);
    List<Object[]> showMedicineByName(@Param("name") String name);
    List<Object[]> showMedicineByIdAndName(@Param("id") int id,@Param("name") String name);
    List<Object[]> showAllMedicinesOrderByName();
    List<Object[]> showAllMedicines();
    List<Object[]> showMedicinesThatAreNotOrdered();

}
