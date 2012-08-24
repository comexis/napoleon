package eu.comexis.napoleon.shared.command.expense;
import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.RealEstate;

public class GetExpenseResponse implements Response{
  private Expense expense;
  private RealEstate estate;
  

  public RealEstate getEstate() {
    return estate;
  }


  public void setEstate(RealEstate estate) {
    this.estate = estate;
  }


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
