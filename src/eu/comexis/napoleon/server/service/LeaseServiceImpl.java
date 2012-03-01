package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.LeaseService;
import eu.comexis.napoleon.server.dao.LeaseDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.association.GetAllAssocResponse;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseResponse;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

public class LeaseServiceImpl extends RemoteServiceServlet implements LeaseService{

  public LeaseServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetLeaseResponse execute(GetLeaseCommand command) {
    LeaseDao dao = new LeaseDao();
    Lease lease = dao.getById(command.getId(), command.getRealEstateId());
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
      leaseList = dao.getAllLeaseForEstate(command.getRealEstateId(),companyId);
    }else{
      leaseList = dao.getAllLease(companyId);
    }
    GetAllLeaseResponse response = new GetAllLeaseResponse();
    response.setLeaseList(leaseList);
    return response;
  }

  @Override
  public UpdateLeaseResponse execute(UpdateLeaseCommand command) {
    LeaseDao dao = new LeaseDao();
    Lease lease = dao.update(command.getLease());
    UpdateLeaseResponse response = new UpdateLeaseResponse();
    response.setLease(lease);
    return response;
  }

}
