package eu.comexis.napoleon.shared.command.estate;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.RealEstateServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetRealEstateCommand implements Action<GetRealEstateResponse> {

	private String id;

	public GetRealEstateCommand() {
	}

	public GetRealEstateCommand(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void dispatch(AsyncCallback<GetRealEstateResponse> callback) {
		RealEstateServiceAsync.INSTANCE.execute(this, callback);

	}

}
