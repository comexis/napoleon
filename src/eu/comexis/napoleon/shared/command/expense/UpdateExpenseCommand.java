package eu.comexis.napoleon.shared.command.expense;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.ExpenseServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Expense;

public class UpdateExpenseCommand implements Action<UpdateExpenseResponse>{
  private Expense entity;
  public Expense getExpense() {
    return entity;
  }


  public void setExpense(Expense entity) {
    this.entity = entity;
  }

  public UpdateExpenseCommand() {
    // TODO Auto-generated constructor stub
  }
  public UpdateExpenseCommand(Expense entity) {
    this.entity = entity;
  }

  @Override
  public void dispatch(AsyncCallback<UpdateExpenseResponse> callback) {
    ExpenseServiceAsync.INSTANCE.execute(this, callback);
    
  }

}
