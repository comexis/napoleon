
package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.expense.GetAllExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.GetAllExpenseResponse;
import eu.comexis.napoleon.shared.command.expense.GetExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.GetExpenseResponse;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseResponse;
@RemoteServiceRelativePath("expense")
public interface ExpenseService extends RemoteService{
  public GetAllExpenseResponse execute(GetAllExpenseCommand command);
  public GetExpenseResponse execute(GetExpenseCommand command);
  public UpdateExpenseResponse execute(UpdateExpenseCommand command);
}
