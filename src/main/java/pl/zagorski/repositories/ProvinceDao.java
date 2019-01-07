package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Province;

import java.util.List;

public interface ProvinceDao {
    void save(Province province);
    void edit(Province province);
    List<Province> findAll();
    Province findOne(int id);
    List<Province> orderByName();
    Province getProvinceByName(@Param("name") String name);


}
