package dersommer.magic.resource.request;

import dersommer.magic.validation.ValidHouse;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterUpdateParam {

  @NotNull
  private String name;
  private String newName;
  private String role;
  private String school;
  @ValidHouse(nullable=true)
  private String house;
  private String patronus;
}
