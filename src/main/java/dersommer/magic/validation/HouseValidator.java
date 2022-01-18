package dersommer.magic.validation;

import dersommer.magic.service.HousesService;
import java.util.Optional;
import javax.enterprise.inject.spi.CDI;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HouseValidator implements ConstraintValidator<ValidHouse, String> {

  HousesService housesService;
  boolean nullable;

  @Override
  public void initialize(ValidHouse annotation) {
    nullable = annotation.nullable();

    Optional<HousesService> service = CDI.current().select(HousesService.class).stream().findFirst();
    if(service.isPresent()) {
      housesService = service.get();
    }
  }

  @Override
  public boolean isValid(String houseId, ConstraintValidatorContext ctx) {
    if(nullable==true && houseId==null)
      return true;

    if(housesService.retrieveHousesAsMap().containsKey(houseId))
      return true;
    return false;
  }
}