package eu.comexis.napoleon.shared.validation;

import static eu.comexis.napoleon.client.resources.ValidationMessages.VALIDATION_MESSAGES;

import java.util.ArrayList;
import java.util.List;

import eu.comexis.napoleon.shared.model.Lease;

public class LeaseValidator extends AbstractValidator<Lease> {

  @Override
  public List<ValidationMessage> validate(Lease lease) {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();

    validateRealEstate(lease, messages);
    validateTenant(lease, messages);
    validateStartDate(lease, messages);
    validateEndDate(lease, messages);
    validateRent(lease, messages);
    validateBookeepingRef(lease, messages);
    validateSecurityDeposit(lease, messages);
    validateFee(lease, messages);
    validateAcademicYear(lease, messages);

    return messages;
  }

  private void validateRealEstate(Lease lease, List<ValidationMessage> messages) {

    if (lease.getRealEstate() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("référence"),
          "reference"));
    }

  }

  private void validateRent(Lease lease, List<ValidationMessage> messages) {

    if (lease.getRent() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("loyer"),
          "rent"));
    }

  }

  private void validateBookeepingRef(Lease lease, List<ValidationMessage> messages) {

    if (lease.getBookkeepingReference() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("référence comptable"),
          "bookkeepingRef"));
    }

  }

  private void validateSecurityDeposit(Lease lease, List<ValidationMessage> messages) {

    if (lease.getRealEstate() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("garantie locative"),
          "deposit"));
    }

  }
  
  private void validateFee(Lease lease, List<ValidationMessage> messages) {

    if (lease.getRealEstate() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("honoraires"),
          "fee"));
    }

  }

  private void validateAcademicYear(Lease lease, List<ValidationMessage> messages) {

    if (lease.getAcademicYear() == null || lease.getAcademicYear().isEmpty()) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("année académique"),
          "academicYear"));
    }

  }

  private void validateTenant(Lease lease, List<ValidationMessage> messages) {

    if (lease.getTenant() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("locataire"),
          "tenantName"));
    }

  }

  private void validateStartDate(Lease lease, List<ValidationMessage> messages) {

    if (lease.getStartDate() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("date d'entrée"),
          "startDate"));
    }

  }

  private void validateEndDate(Lease lease, List<ValidationMessage> messages) {

    if (lease.getEndDate() == null) {
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("date de sortie"),
          "endDate"));
    } else {
      if (lease.getStartDate() != null && lease.getEndDate().before(lease.getStartDate())) {
        messages.add(new ValidationMessage(VALIDATION_MESSAGES.endDateInvalid(), "endDate"));
      }
    }

  }

}
