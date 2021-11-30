package dersommer.magic.resource.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterParam {

  @NotNull
  private String name;
  @NotNull
  private String role;
  @NotNull
  private String school;
  @NotNull
  private String house;
  @NotNull
  private String patronus;
}
