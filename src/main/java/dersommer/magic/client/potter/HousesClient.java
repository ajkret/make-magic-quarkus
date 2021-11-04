package dersommer.magic.client.potter;

import dersommer.magic.dto.Houses;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// Follow this https://quarkus.io/guides/rest-client
@Path("/potterApi")
@RegisterRestClient(configKey="potter-houses-api")
public interface HousesClient {

  @GET
  @Path("/houses")
  public Optional<Houses> queryHouses(@HeaderParam("apiKey")String apiKey);

}
