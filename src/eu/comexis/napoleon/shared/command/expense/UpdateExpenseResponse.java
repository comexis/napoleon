package eu.comexis.napoleon.shared.command.expense;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Expense;

public class UpdateExpenseResponse implements Response{
  private Expense entity;
  public Expense getExpense() {
    return entity;
  }


  public void setExpense(Expense entity) {
    this.entity = entity;
  }
  public UpdateExpenseResponse() {
    // TODO Auto-generated constructor stub
  }

}
