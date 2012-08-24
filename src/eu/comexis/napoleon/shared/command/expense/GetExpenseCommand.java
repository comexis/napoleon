package eu.comexis.napoleon.shared.command.expense;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.ExpenseServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetExpenseCommand implements Action<GetExpenseResponse>{
  private String id;
  private String realEstateId;
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRealEstateId() {
    return realEstateId;
  }

  public void setRealEstateId(String realEstateId) {
    this.realEstateId = realEstateId;
  }

  public GetExpenseCommand() {
    // TODO Auto-generated constructor stub
  }
  public GetExpenseCommand(String id,String realEstateId) {
    this.id = id;
    this.realEstateId = realEstateId;
  }

  @Override
  public void dispatch(AsyncCallback<GetExpenseResponse> callback) {
    ExpenseServiceAsync.INSTANCE.execute(this, callback);
    
  }

}
