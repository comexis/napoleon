package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.OwnerService;
import eu.comexis.napoleon.server.dao.OwnerDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetOwnerResponse;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerResponse;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.utils.SimpleTextComparator;

/**
 * Implementation of the service on server side.
 * 
 * @author jDramaix
 * 
 */
@SuppressWarnings("serial")
public class OwnerServiceImpl extends RemoteServiceServlet implements OwnerService {

  private static Log LOG = LogFactory.getLog(OwnerServiceImpl.class);

  @Override
  public GetAllOwnerResponse execute(GetAllOwnerCommand command) {

    String companyId = UserManager.INSTANCE.getCompanyId();
    OwnerDao ownerData = new OwnerDao();
    ArrayList<SimpleOwner> owners = ownerData.getListSimpleOwners(companyId);
    
    //sort owners by name
    Collections.sort(owners, new SimpleTextComparator<SimpleOwner>() {
    	@Override
    	public int compare(SimpleOwner o1, SimpleOwner o2) {
    		return compare(o1.getName(), o2.getName());
    	}
	});

    GetAllOwnerResponse response = new GetAllOwnerResponse();
    response.setOwners(owners);

    return response;
  }

  @Override
  public GetOwnerResponse execute(GetOwnerCommand command) {
    String id = command.getId();
    String companyId = UserManager.INSTANCE.getCompanyId();
    OwnerDao dao = new OwnerDao();
    if (id == null || id.length() == 0) {
      LOG.warn("Try to get an owner without passing an id !! Return error 500");
      // will generate an error 500. Do put to many info
      throw new RuntimeException("Ooops something wrong happened");
    }
    Owner o = dao.getById(id, companyId);
    GetOwnerResponse response = new GetOwnerResponse();
    response.setOwner(o);

    return response;
  }

  @Override
  public UpdateOwnerResponse execute(UpdateOwnerCommand command) {
    Owner owner = command.getOwner();
    String companyId = UserManager.INSTANCE.getCompanyId();
    OwnerDao dao = new OwnerDao();
    owner = dao.update(owner,companyId);
    UpdateOwnerResponse response = new UpdateOwnerResponse();
    response.setOwner(owner);
    return response;
  }

}
