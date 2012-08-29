package eu.comexis.napoleon.shared.command.expense;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Expense;

public class GetAllExpenseResponse implements Response {
  private List<Expense> expenses;
  private String title;

  public GetAllExpenseResponse() {
    // TODO Auto-generated constructor stub
  }

  public List<Expense> getListExpense() {
    return expenses;
  }

  public String getTitle() {
    return title;
  }

  public void setListExpense(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
