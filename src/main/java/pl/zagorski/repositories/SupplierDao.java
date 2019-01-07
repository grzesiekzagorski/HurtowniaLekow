package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Supplier;

import java.util.List;

public interface SupplierDao {
    void save(Supplier supplier);
    void edit(Supplier supplier);
    List<Supplier> findAll();
    Supplier findOne(int id);
    List<Supplier> orderByName();
    List<Supplier> getSuppliersByName(@Param("name") String name);
    List<Object[]> showAllSuppliers();
    List<Object[]> showSupplierByName(@Param("name") String name);
    List<Object[]> showSupplierById(@Param("id") int id);


}
