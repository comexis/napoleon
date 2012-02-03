package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

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
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

/**
 * Implementation of the service on server side.
 * 
 * @author jDramaix
 * 
 */
@SuppressWarnings("serial")
public class OwnerServiceImpl extends RemoteServiceServlet implements
		OwnerService {

	private static Log LOG = LogFactory.getLog(OwnerServiceImpl.class);

	@Override
	public GetAllOwnerResponse execute(GetAllOwnerCommand command) {

		String companyId = UserManager.INSTANCE.getCompanyId();
		OwnerDao ownerData = new OwnerDao(companyId);
		ArrayList<SimpleOwner> owners = ownerData.getListSimpleOwners();

		GetAllOwnerResponse response = new GetAllOwnerResponse();
		response.setOwners(owners);

		return response;
	}

	@Override
	public GetOwnerResponse execute(GetOwnerCommand command) {
		String id = command.getId();

		if (id == null || id.length() == 0) {
			LOG.warn("Try to get an owner without passing an id !! Return error 500");
			// will generate an error 500. Do put to many info
			throw new RuntimeException("Ooops something wrong happened");
		}
		String companyId = UserManager.INSTANCE.getCompanyId();
		OwnerDao dao = new OwnerDao(companyId);
		Owner o = dao.getById(id);

		GetOwnerResponse response = new GetOwnerResponse();
		response.setOwner(o);

		return response;
	}

}
