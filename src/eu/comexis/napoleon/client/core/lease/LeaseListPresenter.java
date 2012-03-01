package eu.comexis.napoleon.client.core.lease;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllLease;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

public class LeaseListPresenter extends
    AbstractListPresenter<SimpleLease, LeaseListPresenter.MyView, LeaseListPresenter.MyProxy> {
  
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
}
