package eu.comexis.napoleon.shared.command.estate;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.RealEstateServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllRealEstateCommand implements Action<GetAllRealEstateResponse> {

  @Override
  public void dispatch(AsyncCallback<GetAllRealEstateResponse> callback) {
    RealEstateServiceAsync.INSTANCE.execute(this, callback);
  }

}
