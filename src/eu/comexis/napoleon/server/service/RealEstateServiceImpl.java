package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.RealEstateService;
import eu.comexis.napoleon.server.dao.CondoDao;
import eu.comexis.napoleon.server.dao.NapoleonDao;
import eu.comexis.napoleon.server.dao.RealEstateDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateResponse;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the service on server side.
 * 
 * @author jDramaix
 * 
 */
@SuppressWarnings("serial")
public class RealEstateServiceImpl extends RemoteServiceServlet implements RealEstateService {
  public static Log LOG = LogFactory.getLog(RealEstateServiceImpl.class);
  @Override
  public GetAllRealEstateResponse execute(GetAllRealEstateCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    RealEstateDao realEstateData = new RealEstateDao();
    ArrayList<SimpleRealEstate> realEstates = realEstateData.getListSimpleRealEstates(companyId);

    GetAllRealEstateResponse response = new GetAllRealEstateResponse();
    response.setRealEstates(realEstates);

    return response;
  }

  @Override
  public GetRealEstateResponse execute(GetRealEstateCommand command) {
    String id = command.getId();
    String companyId = UserManager.INSTANCE.getCompanyId();
    RealEstateDao dao = new RealEstateDao();
    RealEstate e;
    if (id == null || id.length() == 0) {
      // TODO add logging

      // will generate an error 500. Do not put too many info
      throw new RuntimeException("Ooops something wrong happened");
    } else {
      e = dao.getById(id, companyId);
    }
    GetRealEstateResponse response = new GetRealEstateResponse();
    response.setRealEstate(e);
    return response;
  }

  @Override
  public UpdateRealEstateResponse execute(UpdateRealEstateCommand command) {
    RealEstate realEstate = command.getRealEstate();
    RealEstateDao dao = new RealEstateDao();
    String companyId = UserManager.INSTANCE.getCompanyId();
    realEstate = dao.update(realEstate,companyId);
    UpdateRealEstateResponse response = new UpdateRealEstateResponse();
    response.setRealEstate(realEstate);
    return response;
  }

}
