package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Position;
import pl.zagorski.repositories.PositionDao;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionImpl{

    @Autowired
    private PositionDao positionDao;


    @Override
    @Transactional
    public void save(Position position) {
        positionDao.save(position);
    }

    @Override
    @Transactional
    public void edit(Position position) {
        positionDao.edit(position);
    }

    @Override
    public List<Position> findAll() {
        return positionDao.findAll();
    }

    @Override
    public Position findOne(int id) {
        return positionDao.findOne(id);
    }

    @Override
    public List<Position> orderByName() {
        return positionDao.orderByName();
    }

    @Override
    public Position getPositionByName(String name) {
        return positionDao.getPositionByName(name);
    }

    @Override
    public List<String> showActionsOfThisPosition(String name) {
        return positionDao.showActionsOfThisPosition(name);
    }
}
