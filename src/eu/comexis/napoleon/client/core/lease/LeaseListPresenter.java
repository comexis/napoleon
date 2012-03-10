package eu.comexis.napoleon.client.core.lease;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.events.SelectedMenuEvent;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllLease;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

public class LeaseListPresenter extends
    AbstractListPresenter<SimpleLease, LeaseListPresenter.MyView, LeaseListPresenter.MyProxy> {
  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";
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


  protected void requestData() {
    new GetAllLeaseCommand().dispatch(new GotAllLease() {
      @Override
      public void got(List<SimpleLease> leases) {
        setDatas(leases);
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
  protected void onReveal() {
    super.onReveal();
    SelectedMenuEvent.fire(getEventBus(), Menus.LEASE);
  }
}
