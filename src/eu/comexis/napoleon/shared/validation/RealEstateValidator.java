package eu.comexis.napoleon.shared.validation;

import static eu.comexis.napoleon.client.resources.ValidationMessages.VALIDATION_MESSAGES;

import java.util.ArrayList;
import java.util.List;

import eu.comexis.napoleon.shared.model.RealEstate;

public class RealEstateValidator extends AbstractValidator<RealEstate> {

  @Override
  public List<ValidationMessage> validate(RealEstate estate) {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    
    validateReference(estate, messages);
    validateOwner(estate, messages);
    validateAddress(estate, messages);
    
    return messages;
  }

  private void validateReference(RealEstate estate, List<ValidationMessage> messages) {
   
    if (isEmpty(estate.getReference())){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("Référence"), "reference"));
    }
    
  }
  
  private void validateOwner(RealEstate estate, List<ValidationMessage> messages) {
    
    if (estate.getOwner()==null){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("propriétaire"), "owner"));
    }
    
  }
  
  private void validateAddress(RealEstate estate, List<ValidationMessage> messages) {
    
    if (estate.getStreet()==null || estate.getStreet().isEmpty()){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("adresse"), "street"));
    }
    if (estate.getNumber()==null || estate.getNumber().isEmpty()){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("n°"), "number"));
    }
    if (estate.getPostalCode()==null || estate.getPostalCode().isEmpty()){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("code postal"), "postalCode"));
    }
    if (estate.getCity()==null || estate.getCity().isEmpty()){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("localité"), "city"));
    }
    if (estate.getCountry()==null || estate.getCountry().isEmpty()){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("pays"), "country"));
    }
    
  }

}
