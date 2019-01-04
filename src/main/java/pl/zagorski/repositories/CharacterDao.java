package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Character;

import java.util.List;

public interface CharacterDao {
    void save(Character character);
    void edit(Character character);
    List<Character> findAll();
    Character findOne(int id);
    List<Character> orderByName();
    Character getCharacterByName(@Param("name") String name);


}
