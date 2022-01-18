package dersommer.magic.validation;

import dersommer.magic.service.HousesService;
import java.util.Optional;
import javax.enterprise.inject.spi.CDI;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HouseValidator implements ConstraintValidator<ValidHouse, String> {

  HousesService housesService;

  @Override
  public void initialize(ValidHouse annotation) {
    Optional<HousesService> service = CDI.current().select(HousesService.class).stream().findFirst();
    if(service.isPresent()) {
      housesService = service.get();
    }
  }

  @Override
  public boolean isValid(String houseId, ConstraintValidatorContext cxt) {
    if(housesService.retrieveHousesAsMap().containsKey(houseId))
      return true;
    return false;
  }
}