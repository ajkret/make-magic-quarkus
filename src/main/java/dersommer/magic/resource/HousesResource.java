package dersommer.magic.resource;

import dersommer.magic.resource.request.CharacterInsertParam;
import dersommer.magic.resource.request.CharacterUpdateParam;
import dersommer.magic.service.CharacterService;
import dersommer.magic.service.HousesService;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/api/houses")
@AllArgsConstructor
public class HousesResource {

  HousesService service;
  CharacterService characterService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response queryHouses() {
    return service.retrieveHouses().map(result -> Response.ok().entity(result).build()).orElse(Response.status(Response.Status.BAD_GATEWAY).build());
  }
}