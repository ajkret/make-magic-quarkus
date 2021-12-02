package dersommer.magic.service;

import dersommer.magic.client.potter.HousesClient;
import dersommer.magic.dto.Houses;
import dersommer.magic.repository.CharactersRepository;
import io.quarkus.cache.CacheResult;
import java.time.LocalDateTime;
import java.util.Optional;
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


}

