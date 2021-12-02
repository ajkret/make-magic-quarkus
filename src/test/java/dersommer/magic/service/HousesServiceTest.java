package dersommer.magic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dersommer.magic.client.potter.HousesClient;
import dersommer.magic.dto.Houses;
import dersommer.magic.repository.CharactersRepository;
import dersommer.magic.test.LoadFile;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class HousesServiceTest implements LoadFile {

  private HousesService fixture;
  private HousesClient housesClient;
  private CharactersRepository repository;

  @BeforeEach
  public void init() {
    housesClient = Mockito.mock(HousesClient.class);
    repository = Mockito.mock(CharactersRepository.class);

    Mockito.when(housesClient.queryHouses(Mockito.anyString())).then(invocation -> {
      return Optional.of(new ObjectMapper().readValue(loadFile("/data/houses.json"), Houses.class));
    });

    fixture = new HousesService(housesClient, repository, "");
  }

  @Test
  public void shouldRetrieveHouses() {
    Optional<Houses> result = fixture.retrieveHouses();
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.isPresent());

    Assertions.assertEquals(14, result.get().getHouses().size());

  }
}
