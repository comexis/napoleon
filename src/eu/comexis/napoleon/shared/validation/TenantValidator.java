package eu.comexis.napoleon.shared.validation;

import java.util.List;

import eu.comexis.napoleon.shared.model.Tenant;

public class TenantValidator extends PartyValidator<Tenant>{

  
  @Override
  public List<ValidationMessage> validate(Tenant tenant) {
    List<ValidationMessage> messages = super.validate(tenant);
    return messages;
  }

}
