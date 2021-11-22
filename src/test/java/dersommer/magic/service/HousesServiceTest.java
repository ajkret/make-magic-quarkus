package dersommer.magic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dersommer.magic.client.potter.HousesClient;
import dersommer.magic.dto.Houses;
import dersommer.magic.test.LoadFile;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HousesServiceTest implements LoadFile {

  private HousesService fixture;
  private HousesClient housesClient;

  @BeforeEach
  public void init() {
    housesClient = Mockito.mock(HousesClient.class);

    Mockito.when(housesClient.queryHouses(Mockito.anyString())).then(invocation -> {
      return Optional.of(new ObjectMapper().readValue(loadFile("/data/houses.json"), Houses.class));
    });

    fixture = new HousesService(housesClient, "");
  }

  @Test
  public void shouldRetrieveHouses() {
    Optional<Houses> result = fixture.retrieveHouses();
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.isPresent());

    Assertions.assertEquals(14, result.get().getHouses().size());

  }
}
