package eu.comexis.napoleon.shared.validation;

import java.util.ArrayList;
import java.util.List;
import static eu.comexis.napoleon.client.resources.ValidationMessages.VALIDATION_MESSAGES;

import eu.comexis.napoleon.shared.model.Expense;

public class ExpenseValidator extends AbstractValidator<Expense>{

  @Override
  public List<ValidationMessage> validate(Expense expense) {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    validateReference(expense, messages);
    return messages;
  }
  private void validateReference(Expense expense, List<ValidationMessage> messages) {
    if (isEmpty(expense.getReference())){
      messages.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("références"), "reference"));
    }
  }
}
