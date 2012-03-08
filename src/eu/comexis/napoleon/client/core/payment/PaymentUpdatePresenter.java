package eu.comexis.napoleon.client.core.payment;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.HasPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.rpc.callback.GotLease;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.validation.PaymentValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public abstract class PaymentUpdatePresenter<T extends Payment, V extends PaymentUpdatePresenter.MyView<T>, P extends Proxy<?>>
    extends Presenter<V, P> implements PaymentUpdateUiHandlers {

  public interface MyView<T extends Payment> extends View, HasPresenter<PaymentUpdateUiHandlers> {
    
    public void displayError(String error);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void reset();

    public void setData(T o);
    
    public void setLease(Lease l);

    public void updateData(T o);
    
  }

  public static final String UUID_PARAMETER = "uuid";
  public static final String LEASE_UUID_PARAMETER = "lease_uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";

  private static final Logger LOG = Logger.getLogger(PaymentUpdatePresenter.class.getName());

  protected PlaceManager placeManager;
  protected String id;
  protected String leaseId;
  protected String estateId;
  protected T payment;
  protected Lease lease;
  private PaymentValidator<T> validator;

  @Inject
  public PaymentUpdatePresenter(final EventBus eventBus, final V view, final P proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = createValidator();

  }

  @Override
  public void onButtonCancelClick() {
    goToList();
  }

  @Override
  public void onButtonSaveClick() {
    getView().updateData(payment);

    List<ValidationMessage> validationMessages = validator.validate(payment);

    if (validationMessages.isEmpty()) {
      save();
    } else {
      getView().displayValidationMessage(validationMessages);
    }

  }

  protected abstract PaymentValidator<T> createValidator();

  protected abstract String getDetailsNameTokens();

  protected abstract String getListNameTokens();

  protected abstract void save();

  private void goToDetails() {
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, this.id);
    myRequest = myRequest.with(LEASE_UUID_PARAMETER, this.leaseId);
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, this.estateId);
    placeManager.revealPlace(myRequest);
  }

  private void goToList() {
    PlaceRequest myRequest = new PlaceRequest(getListNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, this.leaseId);
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, this.estateId);
    placeManager.revealPlace(myRequest);

  }
  /**
   * Retrieve the id of the owner to show it
   */
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);
    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);
    leaseId = placeRequest.getParameter(LEASE_UUID_PARAMETER, null);
    this.estateId = placeRequest.getParameter(ESTATE_UUID_PARAMETER, null);
    
    if (id == null || id.length() == 0 
        || leaseId == null || leaseId.length()==0 
        || estateId == null || estateId.length() ==0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }
  }
  
  protected abstract  T createNewDataModel();
  protected T getDataObjectModel(){
    return payment;
  }
  @Override
  protected void onBind() {
    super.onBind();
    getView().setPresenter(this);
    init();
  }
  
  @Override
  protected void onHide() {
    super.onHide();
    getView().reset();
  }

  @Override
  protected void onReset() {
    super.onReset();
    if (id != null && !"new".equals(id)) { // call the server to get the requested owner
      requestLease();
      requestData(id);
    } else {
      requestLease();
      payment = createNewDataModel();
      payment.setLeaseId(leaseId);
      payment.setEstateId(estateId);
      getView().setData(payment);
    }

  }
  protected void requestLease() {
    new GetLeaseCommand(this.leaseId,this.estateId).dispatch(new GotLease() {
      @Override
      public void got(Lease l) {
        setLease(l);
        getView().setLease(l);
      }
    });
  }
  protected abstract void requestData(String id);
  
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
  
  protected void setDataObjectModel(T t){
    payment = t;
    id = t.getId();
  }
  protected void setLease(Lease l){
    lease = l;
    leaseId = l.getId();
    estateId = l.getRealEstate().getId();
  }
  
  private void init() {
    //
  }
}
