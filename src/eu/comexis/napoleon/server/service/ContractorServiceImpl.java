
package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.ContractorService;
import eu.comexis.napoleon.server.dao.ContractorDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.contractor.GetAllContractorCommand;
import eu.comexis.napoleon.shared.command.contractor.GetAllContractorResponse;
import eu.comexis.napoleon.shared.model.Contractor;

public class ContractorServiceImpl extends RemoteServiceServlet implements ContractorService{

  public ContractorServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetAllContractorResponse execute(GetAllContractorCommand command) {
    ContractorDao dao = new ContractorDao();
    String companyId = UserManager.INSTANCE.getCompanyId();
    List<Contractor> contractorList = new ArrayList<Contractor>();
    contractorList = dao.listAll(companyId);
    GetAllContractorResponse response = new GetAllContractorResponse();
    response.setListContractor(contractorList);
    return response;
  }

}
