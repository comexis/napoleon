package eu.comexis.napoleon.shared.command.expense;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Expense;

public class GetAllExpenseResponse implements Response{
  private List<Expense> expenses;
  
  public List<Expense> getListExpense() {
    return expenses;
  }

  public void setListExpense(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public GetAllExpenseResponse() {
    // TODO Auto-generated constructor stub
  }

}
