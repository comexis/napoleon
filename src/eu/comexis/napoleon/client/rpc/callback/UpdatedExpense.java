package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.expense.UpdateExpenseResponse;
import eu.comexis.napoleon.shared.model.Expense;

public abstract class UpdatedExpense extends AbstractCallback<UpdateExpenseResponse> {

  public UpdatedExpense() {
    // TODO Auto-generated constructor stub
  }

  public abstract void got(Expense expense);

  @Override
  public void onSuccess(UpdateExpenseResponse result) {
    got(result.getExpense());
  }
}
