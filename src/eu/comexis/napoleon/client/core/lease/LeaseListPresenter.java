package eu.comexis.napoleon.client.core.lease;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotAllLease;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

public class LeaseListPresenter extends
    AbstractListPresenter<SimpleLease, LeaseListPresenter.MyView, LeaseListPresenter.MyProxy> {
  private String estateId;
  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";
  private static final Logger LOG = Logger.getLogger(LeaseListPresenter.class.getName());
  
  private static class leaseListFilter implements ListFilter<SimpleLease>{
  
    @Override
    public boolean filter(SimpleLease lease, String filter) {
      return !lease.getRealEstateRef().toLowerCase().startsWith(filter.toLowerCase());
    }
    
  }
  @ProxyCodeSplit
  @NameToken(NameTokens.leaselist)
  public interface MyProxy extends ProxyPlace<LeaseListPresenter> {
  }

  public interface MyView extends AbstractListPresenter.MyView<SimpleLease> {
  }

  @Inject
  public LeaseListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.lease;
  }

  @Override
  protected String getUpdateNameTokens() {
    return NameTokens.updateLease;
  }
  @Override
  public void onButtonNewClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, "new");
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, estateId);
    super.getPlaceManager().revealPlace(myRequest);
  }
  
  @Override
  public void onButtonBackToDashBoardClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
    myRequest = myRequest.with(UUID_PARAMETER,estateId);
    getPlaceManager().revealPlace(myRequest);
  }
  
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);
    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    estateId = placeRequest.getParameter(UUID_PARAMETER, null);
    
    if (estateId == null || estateId.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      super.getPlaceManager().revealErrorPlace(placeRequest.getNameToken());
    }
  }
  protected void requestData() {
    new GetAllLeaseCommand(estateId).dispatch(new GotAllLease() {
      @Override
      public void got(List<SimpleLease> leases) {
        setDatas(leases);
        doReveal();
      }
    });
  }

  @Override
  protected ListFilter<SimpleLease> createFilter() {
    return new leaseListFilter();
  }
  @Override
  public void onSelect(SimpleLease data) {
    if (data == null){
      return;
    }
    
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, data.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, data.getRealEstateId());
    super.getPlaceManager().revealPlace(myRequest);
  }
  

  @Override
  protected Menus getMenu() {
    return Menus.REAL_ESTATE;
  }

  @Override
  protected String getTitle() {
    return Literals.INSTANCE.leaseListTitle();
  }

  @Override
  protected boolean manageActive() {
    return false;
  }
  
}
