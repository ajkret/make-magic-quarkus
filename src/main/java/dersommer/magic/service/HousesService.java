package dersommer.magic.service;

import dersommer.magic.client.potter.HousesClient;
import dersommer.magic.dto.Houses;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class HousesService {
  String apiKey;
  HousesClient housesClient;

  @Inject
  public HousesService(@RestClient HousesClient housesClient,
                       @ConfigProperty(name="app.magic.potter-houses-client-api-key") String apiKey) {
    this.apiKey = apiKey;
    this.housesClient = housesClient;
  }

  public Optional<Houses> retrieveHouses() {
    return housesClient.queryHouses(apiKey);
  }

}

