package dersommer.magic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Houses {

  private List<House> houses;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @Getter
  @Setter
  public static class House {
    private String id;
    private String name;
    private String headOfHouse;
    private String[] values;
    private String[] colors;
    private String mascot;
    private String school;
    private String houseGhost;
    private String founder;
  }
}
