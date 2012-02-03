package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.TenantService;
import eu.comexis.napoleon.server.dao.TenantDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantResponse;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

/**
 * Implementation of the service on server side.
 * 
 * @author jDramaix
 * 
 */
@SuppressWarnings("serial")
public class TenantServiceImpl extends RemoteServiceServlet implements
		TenantService {

	@Override
	public GetAllTenantResponse execute(GetAllTenantCommand command) {
	  String companyId = UserManager.INSTANCE.getCompanyId();
		TenantDao tenantData = new TenantDao(companyId);
		ArrayList<SimpleTenant> tenants = tenantData.getListSimpleTenants();

		GetAllTenantResponse response = new GetAllTenantResponse();
		response.setTenants(tenants);

		return response;
	}

	@Override
	public GetTenantResponse execute(GetTenantCommand command) {
		String id = command.getId();
		String companyId = UserManager.INSTANCE.getCompanyId();
    TenantDao dao = new TenantDao(companyId);
    Tenant o;
		if (id == null || id.length() == 0){
			//TODO add logging
			
			//will generate an error 500. Do put to many info
			//throw new RuntimeException("Ooops something wrong happened");
		  o = dao.create();
		}else{
		  o = dao.getById(id);
		}
		GetTenantResponse response = new GetTenantResponse();
		response.setTenant(o);
		
		return response;
	}
	@Override
  public UpdateTenantResponse execute(UpdateTenantCommand command) {
    Tenant tenant = command.getTenant();
    String companyId = UserManager.INSTANCE.getCompanyId();
    TenantDao dao = new TenantDao(companyId);
    tenant = dao.update(tenant);
    UpdateTenantResponse response = new UpdateTenantResponse();
    response.setTenant(tenant);
    return response;
  }


}
