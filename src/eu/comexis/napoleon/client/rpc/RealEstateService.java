package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateResponse;

@RemoteServiceRelativePath("realEstate")
public interface RealEstateService extends RemoteService {
	
	public GetAllRealEstateResponse execute(GetAllRealEstateCommand command);
	
	public GetRealEstateResponse execute(GetRealEstateCommand command);
	
	public UpdateRealEstateResponse execute(UpdateRealEstateCommand command);

}
