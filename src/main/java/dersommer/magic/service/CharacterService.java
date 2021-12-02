package dersommer.magic.service;

import dersommer.magic.entity.Character;
import dersommer.magic.repository.CharactersRepository;
import dersommer.magic.resource.request.CharacterParam;
import javax.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CharacterService {
  CharactersRepository repository;

  public boolean saveCharacter(CharacterParam request) {
    Character character = new Character();
    character.house = request.getHouse();
    character.patronus = request.getPatronus();
    character.role = request.getRole();
    character.school = request.getSchool();
    character.name = request.getName();

    repository.persist(character);

    log.info("[Make Magic] - Character saved {}", character.id);

    return true;
  }


}
