package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.expense.GetExpenseResponse;
import eu.comexis.napoleon.shared.model.Expense;

public abstract class GotExpense extends AbstractCallback<GetExpenseResponse>{

  public abstract void got(Expense expense);
  @Override
  public void onSuccess(GetExpenseResponse result) {
    got(result.getExpense());
  }

}
