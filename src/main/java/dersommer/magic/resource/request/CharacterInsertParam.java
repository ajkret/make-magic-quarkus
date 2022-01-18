package dersommer.magic.resource.request;

import dersommer.magic.validation.ValidHouse;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterInsertParam {

  @NotNull
  private String name;
  @NotNull
  private String role;
  @NotNull
  private String school;
  @NotNull
  @ValidHouse
  private String house;
  @NotNull
  private String patronus;
}
