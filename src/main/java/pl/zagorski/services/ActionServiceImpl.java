package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Action;
import pl.zagorski.repositories.ActionDao;


import java.util.List;

@Service
public class ActionServiceImpl implements ActionImpl {

    @Autowired
    private ActionDao actionDao;


    @Override
    @Transactional
    public void save(Action action) {
        actionDao.save(action);
    }

    @Override
    @Transactional
    public void edit(Action action) {
        actionDao.edit(action);
    }

    @Override
    public List<Action> findAll() {
        return actionDao.findAll();
    }

    @Override
    public Action findOne(int id) {
        return actionDao.findOne(id);
    }

    @Override
    public List<Action> orderByName() {
        return actionDao.orderByName();
    }

    @Override
    public Action getActionByName(String name) {
        return actionDao.getActionByName(name);
    }

    @Override
    public List<String> showPositionsOfThisAction(String name) {
        return actionDao.showPositionsOfThisAction(name);
    }
}
