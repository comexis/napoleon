package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.RealEstateService;
import eu.comexis.napoleon.server.dao.RealEstateDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateResponse;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

/**
 * Implementation of the service on server side.
 * 
 * @author jDramaix
 * 
 */
@SuppressWarnings("serial")
public class RealEstateServiceImpl extends RemoteServiceServlet implements
		RealEstateService {

	@Override
	public GetAllRealEstateResponse execute(GetAllRealEstateCommand command) {
	  String companyId = UserManager.INSTANCE.getCompanyId();
		RealEstateDao realEstateData = new RealEstateDao(companyId);
		ArrayList<SimpleRealEstate> realEstates = realEstateData.getListSimpleRealEstates();

		GetAllRealEstateResponse response = new GetAllRealEstateResponse();
		response.setRealEstates(realEstates);

		return response;
	}

	@Override
	public GetRealEstateResponse execute(GetRealEstateCommand command) {
		String id = command.getId();
		String companyId = UserManager.INSTANCE.getCompanyId();
    RealEstateDao dao = new RealEstateDao(companyId);
    RealEstate o;
		if (id == null || id.length() == 0){
			//TODO add logging
			
			//will generate an error 500. Do put to many info
			//throw new RuntimeException("Ooops something wrong happened");
		  o = dao.create();
		}else{
		  o = dao.getById(id);
		}
		GetRealEstateResponse response = new GetRealEstateResponse();
		response.setRealEstate(o);
		
		return response;
	}
	@Override
  public UpdateRealEstateResponse execute(UpdateRealEstateCommand command) {
    RealEstate realEstate = command.getRealEstate();
    String companyId = UserManager.INSTANCE.getCompanyId();
    RealEstateDao dao = new RealEstateDao(companyId);
    realEstate = dao.update(realEstate);
    UpdateRealEstateResponse response = new UpdateRealEstateResponse();
    response.setRealEstate(realEstate);
    return response;
  }


}
