package eu.comexis.napoleon.client.core.lease;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.AbstractPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotAllRealEstate;
import eu.comexis.napoleon.client.rpc.callback.GotAllSuggest;
import eu.comexis.napoleon.client.rpc.callback.GotAllTenant;
import eu.comexis.napoleon.client.rpc.callback.GotLease;
import eu.comexis.napoleon.client.rpc.callback.UpdatedLease;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseCommand;
import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.model.FeeUnit;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;
import eu.comexis.napoleon.shared.validation.LeaseValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;
import eu.comexis.napoleon.client.core.lease.LeaseUpdateUiHandlers.HasLeaseUpdateUiHandler;
public class LeaseUpdatePresenter extends
    AbstractPresenter<LeaseUpdatePresenter.MyView, LeaseUpdatePresenter.MyProxy> implements
    LeaseUpdateUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.updateLease)
  public interface MyProxy extends ProxyPlace<LeaseUpdatePresenter> {
  }
  public interface MyView extends View, HasLeaseUpdateUiHandler {
    public void displayError(String error);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void reset();

    public void setLease(Lease l);
    
    public void setFee(Float fee);

    public Lease updateLease(Lease l);
    
    public void fillEstateList(List<SimpleRealEstate> estates);
    
    public void fillTenantList(List<SimpleTenant> tenants);
    
    public void fillAcademicYearList(List<String> years);
  }

  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";

  private static final Logger LOG = Logger.getLogger(LeaseDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private String realEstateId;
  private Lease lease;
  private LeaseValidator validator;

  @Inject
  public LeaseUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = new LeaseValidator();
  }
  private void goToDetails() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.lease);
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }

  private void goToList() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.leaselist);
    placeManager.revealPlace(myRequest);

  }
  @Override
  public void onButtonCancelClick() {
    if (lease == null || lease.getId() == null || lease.getId().length() == 0){
      goToList();
    }else {
      goToDetails();
    }
  }
  @Override
  public void onRentChanged(Float rent) {
    Float feeOwner = 0f;
    Float fee = 0f;
    if (lease.getUnit().equals(FeeUnit.RENT_PERCENTAGE)){
      fee=rent * lease.getFeeFromOwner()/100;
    }else{
      fee=lease.getFeeFromOwner();
    }
    getView().setFee(fee);
  }
  @Override
  public void onButtonSaveClick() {
    getView().updateLease(lease);

    List<ValidationMessage> validationMessages = validator.validate(lease);
    
    if (validationMessages.isEmpty()) {
      saveLease();
    } else {
      getView().displayValidationMessage(validationMessages);
    }
    
  }

  /**
   * Retrieve the id of the realEstate to show it
   */
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);
    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);
    realEstateId = placeRequest.getParameter(ESTATE_UUID_PARAMETER, null);

    if (id == null || id.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }

  }

  public void saveLease() {
    // Save it
    UpdateLeaseCommand cmd = new UpdateLeaseCommand();
    cmd.setLease(lease);
    cmd.dispatch(new UpdatedLease() {
      @Override
      public void got(Lease lease) {
        if (lease != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.lease);
          myRequest = myRequest.with(UUID_PARAMETER, lease.getId());
          myRequest = myRequest.with(ESTATE_UUID_PARAMETER, lease.getRealEstate().getId());
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The lease cannot be save");
        }
      }
    });

  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().setLeaseUpdateUiHandler(this);
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
    if (id != null) { // call the server to get the requested owner
      new GetLeaseCommand(id,realEstateId).dispatch(new GotLease() {
        @Override
        public void got(Lease lease) {
          LeaseUpdatePresenter.this.lease = lease;
          getView().setLease(lease);
        }
      });
    }
  }
  
  
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  private void init() {
    new GetAllTenantCommand().dispatch(new GotAllTenant() {
      @Override
      public void got(List<SimpleTenant> tenants) {
        getView().fillTenantList(tenants);
      }
    });
    new GetAllRealEstateCommand().dispatch(new GotAllRealEstate() {
      @Override
      public void got(List<SimpleRealEstate> estates) {
        getView().fillEstateList(estates);
      }
    });
    new GetAllSuggestCommand("AcademicYear").dispatch(new GotAllSuggest() {
      @Override
      public void got(List<String> suggests) {
        getView().fillAcademicYearList(suggests);
      }
    });
  }
  
  @Override
  protected Menus getMenu() {
    return Menus.LEASE;
  }
  
  @Override
  protected String getTitle() {
    if ("new".equals(id)) {
      return Literals.INSTANCE.leaseNewTitle();
    }
    return Literals.INSTANCE.leaseUpdateTitle();
  }
}