package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Producer;
import pl.zagorski.repositories.ProducerDao;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerImpl {

    @Autowired
    private ProducerDao producerDao;


    @Override
    @Transactional
    public void save(Producer producer) {
        producerDao.save(producer);
    }

    @Override
    @Transactional
    public void edit(Producer producer) {
        producerDao.edit(producer);
    }

    @Override
    public List<Producer> findAll() {
        return producerDao.findAll();
    }

    @Override
    public Producer findOne(int id) {
        return producerDao.findOne(id);
    }

    @Override
    public List<Producer> orderByName() {
        return producerDao.orderByName();
    }

    @Override
    public Producer getProducerByName(String name) {
        return producerDao.getProducerByName(name);
    }
}
