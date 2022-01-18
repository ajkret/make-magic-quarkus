package dersommer.magic.repository;

import dersommer.magic.entity.Character;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CharactersRepository implements PanacheMongoRepository<Character> {
  public Optional<Character> findByName(String name) {
    return Optional.ofNullable(find("name", name).firstResult());
  }

}
