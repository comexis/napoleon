package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.AssocService;
import eu.comexis.napoleon.server.dao.HomeownerAssocDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.association.GetAllAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAllAssocResponse;
import eu.comexis.napoleon.shared.command.association.GetAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAssocResponse;
import eu.comexis.napoleon.shared.model.Association;

public class AssocServiceImpl extends RemoteServiceServlet implements AssocService{

  public AssocServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetAllAssocResponse execute(GetAllAssocCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    HomeownerAssocDao dao = new HomeownerAssocDao();
    List<String> lst = dao.getNames(companyId);
    GetAllAssocResponse response = new GetAllAssocResponse();
    response.setHomeownerAssociationNames(lst);
    return response;
  }

  @Override
  public GetAssocResponse execute(GetAssocCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    HomeownerAssocDao dao = new HomeownerAssocDao();
    Association assoc = dao.getById(command.getName(), companyId);
    GetAssocResponse response = new GetAssocResponse();
    response.setHomeownerAssociation(assoc);
    return response;
  }
}
