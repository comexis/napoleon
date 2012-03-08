package eu.comexis.napoleon.client.core.lease;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.lease.LeaseDetailUiHandlers.HasLeaseDetailUiHandlers;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotLease;
import eu.comexis.napoleon.client.rpc.callback.GotRealEstate;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class LeaseDetailsPresenter extends
    Presenter<LeaseDetailsPresenter.MyView, LeaseDetailsPresenter.MyProxy> implements
    LeaseDetailUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.lease)
  public interface MyProxy extends ProxyPlace<LeaseDetailsPresenter> {
  }
  public interface MyView extends View, HasLeaseDetailUiHandlers {
    public void setLease(Lease l);
  }

  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";

  private static final Logger LOG = Logger.getLogger(LeaseDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private String realEstateId;
  private Lease lease;

  @Inject
  public LeaseDetailsPresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }

  @Override
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.leaselist);
    placeManager.revealPlace(myRequest);
  }
  
  @Override
  public void onButtonPaymentTenantClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentTenantlist);
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }
  
  @Override
  public void onButtonPaymentOwnerClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentOwnerlist);
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.updateLease);
    // add the id of the realEstate to load
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }

  /**
   * Retrieve the id of the lease to show it
   */
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);

    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);
    realEstateId = placeRequest.getParameter(ESTATE_UUID_PARAMETER, null);

    if (id == null || id.length() == 0 || realEstateId == null || realEstateId.length()== 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id: null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }
  }

  @Override
  protected void onBind() {
    super.onBind();

    getView().setLeaseDetailUiHandler(this);
  }

  @Override
  protected void onReset() {
    super.onReset();

    new GetLeaseCommand(id, realEstateId).dispatch(new GotLease() {

      @Override
      public void got(Lease lease) {
        LeaseDetailsPresenter.this.lease = lease;
        getView().setLease(lease);
      }
    });

  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  @Override
  public void onButtonPaymentClick() {
    // TODO Auto-generated method stub
    
  }

}
