
package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.expense.GetAllExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.GetAllExpenseResponse;
import eu.comexis.napoleon.shared.command.expense.GetExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.GetExpenseResponse;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseResponse;
public interface ExpenseServiceAsync {
  public ExpenseServiceAsync INSTANCE = GWT.create(ExpenseService.class);
  void execute(GetExpenseCommand command, AsyncCallback<GetExpenseResponse> callback);
  void execute(GetAllExpenseCommand command, AsyncCallback<GetAllExpenseResponse> callback);
  void execute(UpdateExpenseCommand command, AsyncCallback<UpdateExpenseResponse> callback);
}
