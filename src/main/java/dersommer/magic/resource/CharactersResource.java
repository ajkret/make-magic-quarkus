package dersommer.magic.resource;

import dersommer.magic.dto.Houses;
import dersommer.magic.resource.request.CharacterParam;
import dersommer.magic.service.HousesService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/characters")
public class CharactersResource {

  HousesService service;

  public CharactersResource(HousesService service) {

    this.service = service;
  }


  @GET
  @Path("/houses")
  @Produces(MediaType.APPLICATION_JSON)
  public Response queryHouses() {
    return service.retrieveHouses().map(result -> Response.ok().entity(result).build()).orElse(Response.status(Response.Status.BAD_GATEWAY).build());
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response insert(CharacterParam request) {

    
  }

}
