package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.LeaseService;
import eu.comexis.napoleon.server.dao.LeaseDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.server.utils.NapoleonDaoException;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseResponse;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

public class LeaseServiceImpl extends RemoteServiceServlet implements LeaseService{
  public static Log LOG = LogFactory.getLog(LeaseServiceImpl.class);
  public LeaseServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetLeaseResponse execute(GetLeaseCommand command) {
    LeaseDao dao = new LeaseDao();
    String companyId = UserManager.INSTANCE.getCompanyId();
    Lease lease = dao.getById(command.getId(), command.getRealEstateId(), companyId);
    GetLeaseResponse response = new GetLeaseResponse();
    response.setLease(lease);
    return response;
  }

  @Override
  public GetAllLeaseResponse execute(GetAllLeaseCommand command) {
    LeaseDao dao = new LeaseDao();
    String companyId = UserManager.INSTANCE.getCompanyId();
    List<SimpleLease> leaseList = new ArrayList<SimpleLease>();
    if (command.getRealEstateId()!=null && !command.getRealEstateId().isEmpty()){
      leaseList = dao.getAllLease(command.getRealEstateId(),companyId);
    }else{
      leaseList = dao.getAllLease(companyId);
    }
    GetAllLeaseResponse response = new GetAllLeaseResponse();
    response.setLeaseList(leaseList);
    return response;
  }

  @Override
  public UpdateLeaseResponse execute(UpdateLeaseCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    UpdateLeaseResponse response = new UpdateLeaseResponse();
    try{
      LeaseDao dao = new LeaseDao();
      Lease lease = dao.update(command.getLease(),companyId);
      response.setLease(lease);
    }catch(NapoleonDaoException e){
      LOG.info("Dao Error: " + e.getMessage());
      response.setErrorMsg(e.getMessage());
    }catch(Exception e){
      LOG.error("Error: cannot update payment " + e);
      response.setErrorMsg("Oups, une erreur s'est produite");
    }
    return response;
  }

}
