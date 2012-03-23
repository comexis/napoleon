package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.expense.GetAllExpenseResponse;
import eu.comexis.napoleon.shared.model.Expense;

public abstract class GotAllExpense extends AbstractCallback<GetAllExpenseResponse>{

  public abstract void got(String title,List<Expense> leaseList);

  @Override
  public void onSuccess(GetAllExpenseResponse result) {
    got(result.getTitle(),result.getListExpense());
  }

}
