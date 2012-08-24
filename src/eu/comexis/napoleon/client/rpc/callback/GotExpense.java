package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.expense.GetExpenseResponse;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.RealEstate;

public abstract class GotExpense extends AbstractCallback<GetExpenseResponse>{

  public abstract void got(Expense expense,RealEstate estate);
  @Override
  public void onSuccess(GetExpenseResponse result) {
    got(result.getExpense(),result.getEstate());
  }
}
