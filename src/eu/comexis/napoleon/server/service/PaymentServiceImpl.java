package eu.comexis.napoleon.server.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.PaymentService;
import eu.comexis.napoleon.server.dao.LeaseDao;
import eu.comexis.napoleon.server.dao.PaymentDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.payment.GetAllPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.GetAllPaymentResponse;
import eu.comexis.napoleon.shared.command.payment.GetPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.GetPaymentResponse;
import eu.comexis.napoleon.shared.command.payment.GetPaymentsBoardCommand;
import eu.comexis.napoleon.shared.command.payment.GetPaymentsBoardResponse;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentCommand;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentResponse;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.model.PaymentTenant;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

@SuppressWarnings("serial")
public class PaymentServiceImpl extends RemoteServiceServlet implements PaymentService{
  public static Log LOG = LogFactory.getLog(PaymentServiceImpl.class);
  public PaymentServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public <T extends Payment> GetPaymentResponse<T> execute(GetPaymentCommand<T> command) {
    PaymentDao<T> ptDao = new PaymentDao<T>();
    String errorMsg = "";
    if (command.getType().equals(PaymentTenant.class.toString())){
      ptDao.setClazz((Class<T>)PaymentTenant.class);
    }else if (command.getType().equals(PaymentOwner.class.toString())){
      ptDao.setClazz((Class<T>)PaymentOwner.class);
    }
    String companyId = UserManager.INSTANCE.getCompanyId();
    T pt = null;
    if (command.getPaymentId().equals("next")){
      try{
        if (command.getType().equals(PaymentTenant.class.toString())){
          pt = (T)ptDao.getNextPaymentTenantForLease(command.getLeaseId(), command.getEstateId(), companyId);
        }else if(command.getType().equals(PaymentOwner.class.toString())){
          pt = (T)ptDao.getNextPaymentOwnerForLease(command.getLeaseId(), command.getEstateId(), companyId);
        }
      }catch(Exception e){
        errorMsg = e.getMessage();
      }
    }else{
      pt = ptDao.getById(command.getPaymentId(),command.getLeaseId(),command.getEstateId(), companyId);
    }
    GetPaymentResponse<T> response = new GetPaymentResponse<T>();
    response.setPayment(pt);
    response.setErrorMsg(errorMsg);
    return response;
  }
  
  @Override
  public <T extends Payment> GetAllPaymentResponse<T> execute(GetAllPaymentCommand<T> command) {
    LOG.info("Execute Payment service " + command.getType());
    GetAllPaymentResponse<T> response = new GetAllPaymentResponse<T>();
    String companyId = UserManager.INSTANCE.getCompanyId();
    PaymentDao<T> ptDao = new PaymentDao<T>();
    LeaseDao lDao = new LeaseDao();
    Lease l = lDao.getById(command.getLeaseId(),command.getEstateId(), companyId);
    if (command.getType().equals(PaymentTenant.class.toString())){
      ptDao.setClazz((Class<T>)PaymentTenant.class);
    }else if (command.getType().equals(PaymentOwner.class.toString())){
      ptDao.setClazz((Class<T>)PaymentOwner.class);
    }
    response.setTitle(l.getRealEstate().getReference() + " " + l.getAcademicYear() + " " + l.getTenant().getName());
    List<T> pts = ptDao.getPaymentsForLease(command.getLeaseId(),command.getEstateId(), companyId);
    
    response.setPayment(pts);
    return response;
  }
  
  @Override
  public GetPaymentsBoardResponse execute(GetPaymentsBoardCommand command) {
    GetPaymentsBoardResponse response = new GetPaymentsBoardResponse();
    String companyId = UserManager.INSTANCE.getCompanyId();
    PaymentDao<Payment> ptDao = new PaymentDao<Payment>();
    LeaseDao lDao = new LeaseDao();
    Lease l = lDao.getById(command.getLeaseId(),command.getEstateId(), companyId);
    List<PaymentListItem> pts = ptDao.getPaymentDashboardForLease(command.getLeaseId(),command.getEstateId(), companyId);
    response.setTitle(l.getRealEstate().getReference() + " " + l.getAcademicYear() + " " + l.getTenant().getName());
    response.setListPayments(pts);
    return response;
  }

  @Override
  public <T extends Payment> UpdatePaymentResponse<T> execute(UpdatePaymentCommand<T> command) {
    // TODO Auto-generated method stub
    String errorMsg = "";
    PaymentDao<T> ptDao = new PaymentDao<T>();
    T pt = command.getPayment();
    if (command.getType().equals(PaymentTenant.class.toString())){
      ptDao.setClazz((Class<T>)PaymentTenant.class);
    }else if (command.getType().equals(PaymentOwner.class.toString())){
      ptDao.setClazz((Class<T>)PaymentOwner.class);
    }
    String companyId = UserManager.INSTANCE.getCompanyId();
    try{
      pt = ptDao.update(pt,companyId);
    }catch(Exception e){
      errorMsg=e.getMessage();
    }
    UpdatePaymentResponse<T> response = new UpdatePaymentResponse<T>();
    response.setPayment(pt);
    response.setErrorMsg(errorMsg);
    return response;
  }

}
