package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateResponse;


public interface RealEstateServiceAsync {

	public RealEstateServiceAsync INSTANCE = GWT.create(RealEstateService.class);
	
	void execute(GetAllRealEstateCommand command,
			AsyncCallback<GetAllRealEstateResponse> callback);

	void execute(GetRealEstateCommand command,
			AsyncCallback<GetRealEstateResponse> callback);
	void execute(UpdateRealEstateCommand command,
      AsyncCallback<UpdateRealEstateResponse> callback);

}
