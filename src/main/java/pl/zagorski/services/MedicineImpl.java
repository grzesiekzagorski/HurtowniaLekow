package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Medicine;

import java.util.List;

public interface MedicineImpl {
    void save(int idPrescription, int idCharacter, int idProducer, String name, double price, double discount,
              String portion, String wrapping);
    void edit(Medicine medicine);
    List<Medicine> findAll();
    Medicine findOne(int id);
    List<Medicine> orderByName();
    List<String[]> showMedicineByIdOrName(@Param("id")String id ,@Param("name")String name);
    List<String[]> convertObjectListToStringList(List<Object[]> objects);
    List<String[]> showAllMedicinesOrderByName();
    List<String[]> showAllMedicines();

}
