package eu.comexis.napoleon.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.CondoService;
import eu.comexis.napoleon.server.dao.CondoDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.condo.GetAllCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetAllCondoResponse;
import eu.comexis.napoleon.shared.command.condo.GetCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetCondoResponse;
import eu.comexis.napoleon.shared.model.Condo;

public class CondoServiceImpl extends RemoteServiceServlet implements CondoService{

  @Override
  public GetAllCondoResponse execute(GetAllCondoCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CondoDao dao = new CondoDao();
    List<String> lst = dao.getNames(companyId);
    GetAllCondoResponse response = new GetAllCondoResponse();
    response.setCondoNames(lst);
    return response;
  }

  @Override
  public GetCondoResponse execute(GetCondoCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CondoDao dao = new CondoDao();
    Condo cdo = dao.getById(command.getName(), companyId);
    GetCondoResponse response = new GetCondoResponse();
    response.setCdo(cdo);
    return response;
  }

}
