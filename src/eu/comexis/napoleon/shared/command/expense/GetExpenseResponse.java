package eu.comexis.napoleon.shared.command.expense;
import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Expense;

public class GetExpenseResponse implements Response{
  private Expense expense;
  

  public Expense getExpense() {
    return expense;
  }


  public void setExpense(Expense expense) {
    this.expense = expense;
  }


  public GetExpenseResponse() {
    // TODO Auto-generated constructor stub
  }

}
