package dersommer.magic.service;

import dersommer.magic.client.potter.HousesClient;
import dersommer.magic.dto.Houses;
import dersommer.magic.repository.CharactersRepository;
import io.quarkus.cache.CacheResult;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class HousesService {
  String apiKey;
  HousesClient housesClient;
  LocalDateTime validThru = LocalDateTime.MIN;

  @Inject
  public HousesService(@RestClient HousesClient housesClient,
                       CharactersRepository repository,
                       @ConfigProperty(name = "app.magic.potter-houses-client-api-key") String apiKey) {
    this.apiKey = apiKey;
    this.housesClient = housesClient;
  }

  @CacheResult(cacheName = "houses-cache")
  public Optional<Houses> retrieveHouses() {
    return housesClient.queryHouses(apiKey);
  }

  @CacheResult(cacheName = "houses-cache-map")
  public Map<String, Houses.House> retrieveHousesAsMap() {
    return housesClient.queryHouses(apiKey)
                       .stream()
                       .flatMap(houses -> houses.getHouses().stream())
                       .collect(Collectors.toMap(Houses.House::getId, house -> house, (house1, house2) -> house1));
  }
}

