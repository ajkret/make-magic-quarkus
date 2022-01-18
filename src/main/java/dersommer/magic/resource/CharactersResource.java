package dersommer.magic.resource;

import dersommer.magic.repository.request.CharactersFilter;
import dersommer.magic.resource.request.CharacterInsertParam;
import dersommer.magic.resource.request.CharacterUpdateParam;
import dersommer.magic.service.CharacterService;
import dersommer.magic.validation.ValidHouse;
import java.util.Optional;
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

@Path("/api/characters")
@AllArgsConstructor
public class CharactersResource {

  CharacterService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response queryHouses(@QueryParam("name") String name,  @QueryParam("house") @ValidHouse(nullable = true)String house) {

    return Optional.ofNullable(service.retrieveCharacters(new CharactersFilter(name, house)))
                   .map(result -> Response.ok().entity(result).build())
                   .orElse(Response.status(Response.Status.BAD_GATEWAY).build());
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response insert(@Valid CharacterInsertParam request) {

    return service.saveCharacter(request) ? Response.ok().entity("Character saved").build() : Response.notModified().build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("update") // Instead of PUT
  public Response update(@Valid CharacterUpdateParam request) {

    return service.updateCharacter(request) ? Response.ok().entity("Character saved").build() : Response.notModified().build();
  }


}
