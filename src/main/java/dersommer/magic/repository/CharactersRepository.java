package dersommer.magic.repository;

import dersommer.magic.entity.Character;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CharactersRepository implements PanacheMongoRepository<Character> {
  public Character findByName(String name) {
    return find("name", name).firstResult();
  }
}
