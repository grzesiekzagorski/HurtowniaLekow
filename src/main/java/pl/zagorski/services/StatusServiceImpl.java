package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Status;
import pl.zagorski.repositories.StatusDao;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusImpl{

    @Autowired
    private StatusDao statusDao;

    @Override
    @Transactional
    public void save(Status status) {
        statusDao.save(status);
    }

    @Override
    @Transactional
    public void edit(Status status) {
        statusDao.edit(status);
    }

    @Override
    public List<Status> findAll() {
        return statusDao.findAll();
    }

    @Override
    public Status findOne(int id) {
        return statusDao.findOne(id);
    }

    @Override
    public List<Status> orderByName() {
        return statusDao.orderByName();
    }

    @Override
    public Status getStatusByName(String name) {
        return statusDao.getStatusByName(name);
    }
}
