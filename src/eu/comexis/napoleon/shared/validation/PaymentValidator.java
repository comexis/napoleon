package eu.comexis.napoleon.shared.validation;

import static eu.comexis.napoleon.client.resources.ValidationMessages.VALIDATION_MESSAGES;

import java.util.ArrayList;
import java.util.List;

import eu.comexis.napoleon.shared.model.Payment;

public class PaymentValidator<T extends Payment> extends AbstractValidator<T>  {

  public PaymentValidator() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public List<ValidationMessage> validate(T payment) {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    
    validateAmount(payment, messages);
    validateDate(payment, messages);
    validateStartDate(payment, messages);
    validateEndDate(payment, messages);
    
    return messages;
  }
  private void validateAmount(T payment, List<ValidationMessage> messages) {
    
    if (payment.getAmount()==null){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("montant"), "amount"));
    }
    
  }
  private void validateStartDate(T payment, List<ValidationMessage> messages) {
    
    if (payment.getPeriodStartDate()==null){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("date de debut de période"), "fromDate"));
    }
    
  }
  private void validateDate(T payment, List<ValidationMessage> messages) {
    
    if (payment.getPaymentDate()==null){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("date de paiement"), "date"));
    }
    
  }
  
  private void validateEndDate(T payment, List<ValidationMessage> messages) {
    
    if (payment.getPeriodEndDate()==null){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("date de fin de période"), "toDate"));
    }else{
      if (payment.getPeriodStartDate()!=null && payment.getPeriodEndDate().before(payment.getPeriodStartDate())){
        messages.add(new ValidationMessage(VALIDATION_MESSAGES.endDateInvalid(), "toDate"));
      }
    }
    
  }

}
