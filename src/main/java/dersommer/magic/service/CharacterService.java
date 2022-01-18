package dersommer.magic.service;

import dersommer.magic.entity.Character;
import dersommer.magic.repository.CharactersRepository;
import dersommer.magic.repository.request.CharactersFilter;
import dersommer.magic.resource.request.CharacterInsertParam;
import dersommer.magic.resource.request.CharacterUpdateParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CharacterService {
  CharactersRepository repository;

  public boolean saveCharacter(CharacterInsertParam request) {
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

  public boolean updateCharacter(final CharacterUpdateParam request) {
    Optional<Character> actual = repository.findByName(request.getName());
    if (actual.isPresent()) {
      Character character = actual.get();
      if (request.getNewName() != null) {character.setName(request.getNewName());}
      if (request.getHouse() != null) {character.setHouse(request.getHouse());}
      if (request.getRole() != null) {character.setRole(request.getRole());}
      if (request.getSchool() != null) {character.setRole(request.getSchool());}
      if (request.getPatronus() != null) {character.setRole(request.getPatronus());}

      repository.update(character);

      log.info("[Make Magic] - Character updated {}", character.id);
      return true;
    }
    return false;
  }

  public List<Character> retrieveCharacters(CharactersFilter filter) {
    final int idx[] = {0};
    String query = Stream.of(new KeyValue("house", filter.house()), new KeyValue("name", filter.name()))
                         .filter(entry -> entry.value != null)
                         .map(entry -> String.format("%s=?%d", entry.key, ++idx[0]))
                         .collect(Collectors.joining(" and "));

    List<String> param = Stream.of(new KeyValue("house", filter.house()), new KeyValue("name", filter.name()))
                               .filter(entry -> entry.value != null)
                               .map(KeyValue::value)
                               .collect(Collectors.toList());

    if (query.isEmpty()) {
      return repository.streamAll().toList();
    }

    // Mongo does not work with named parameters (like name=:name and passing a map, which would be more graceful)
    return repository.list(query, param.toArray()).stream().toList();
  }

  record KeyValue(String key, String value) {}

}
