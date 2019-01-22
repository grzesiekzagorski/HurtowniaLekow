package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Character;
import pl.zagorski.repositories.CharacterDao;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterImpl {

    @Autowired
    private CharacterDao characterDao;


    @Override
    @Transactional
    public void save(Character character) {
        characterDao.save(character);
    }

    @Override
    @Transactional
    public void edit(Character character) {
        characterDao.edit(character);
    }

    @Override
    public List<Character> findAll() {
        return characterDao.findAll();
    }

    @Override
    public Character findOne(int id) {
        return characterDao.findOne(id);
    }

    @Override
    public List<Character> orderByName() {
        return characterDao.orderByName();
    }

    @Override
    public Character getCharacterByName(String name){
        return characterDao.getCharacterByName(name);
    }
}
