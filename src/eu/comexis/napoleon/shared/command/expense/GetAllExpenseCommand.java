package eu.comexis.napoleon.shared.command.expense;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.ExpenseServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllExpenseCommand implements Action<GetAllExpenseResponse> {
  private String realEstateId;

  public GetAllExpenseCommand() {
    // TODO Auto-generated constructor stub
  }
  public GetAllExpenseCommand(String realEstateId) {
    this.realEstateId = realEstateId;
  }

  @Override
  public void dispatch(AsyncCallback<GetAllExpenseResponse> callback) {
    ExpenseServiceAsync.INSTANCE.execute(this, callback);

  }

  public String getRealEstateId() {
    return realEstateId;
  }

  public void setRealEstateId(String realEstateId) {
    this.realEstateId = realEstateId;
  }

}
