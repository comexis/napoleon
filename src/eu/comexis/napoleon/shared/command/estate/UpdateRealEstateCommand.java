package eu.comexis.napoleon.shared.command.estate;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.RealEstateServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.RealEstate;

public class UpdateRealEstateCommand implements Action<UpdateRealEstateResponse>{

  public UpdateRealEstateCommand() {
    // TODO Auto-generated constructor stub
  }
  public UpdateRealEstateCommand(RealEstate realEstate) {
    this.realEstate = realEstate;
  }
  private RealEstate realEstate;
  
  public RealEstate getRealEstate() {
    return realEstate;
  }
  
  public void setRealEstate(RealEstate realEstate) {
    this.realEstate = realEstate;
  }
  @Override
  public void dispatch(AsyncCallback<UpdateRealEstateResponse> callback) {
    RealEstateServiceAsync.INSTANCE.execute(this, callback);

  }
}
