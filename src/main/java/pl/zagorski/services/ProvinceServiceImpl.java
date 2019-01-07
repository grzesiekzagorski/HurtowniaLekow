package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Province;
import pl.zagorski.repositories.ProvinceDao;

import java.util.List;

@Service
public class ProvinceServiceImpl  implements ProvinceImpl{

    @Autowired
    ProvinceDao provinceDao;


    @Override
    @Transactional
    public void save(Province province) {
        provinceDao.save(province);
    }

    @Override
    @Transactional
    public void edit(Province province) {
        provinceDao.edit(province);
    }

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public Province findOne(int id) {
        return provinceDao.findOne(id);
    }

    @Override
    public List<Province> orderByName() {
        return provinceDao.orderByName();
    }

    @Override
    public Province getProvinceByName(String name) {
        return provinceDao.getProvinceByName(name);
    }
}
