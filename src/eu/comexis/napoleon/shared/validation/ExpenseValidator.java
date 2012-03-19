package eu.comexis.napoleon.shared.validation;

import java.util.ArrayList;
import java.util.List;

import eu.comexis.napoleon.shared.model.Expense;

public class ExpenseValidator extends AbstractValidator<Expense>{

  public ExpenseValidator() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public List<ValidationMessage> validate(Expense toValidate) {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    return messages;
  }

}
