package eu.comexis.napoleon.client.core.tenant;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllTenant;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public class TenantListPresenter extends
    AbstractListPresenter<SimpleTenant, TenantListPresenter.MyView, TenantListPresenter.MyProxy> {

  @ProxyCodeSplit
  @NameToken(NameTokens.tenantlist)
  public interface MyProxy extends ProxyPlace<TenantListPresenter> {
  }

  public interface MyView extends AbstractListPresenter.MyView<SimpleTenant> {

  }

  @Inject
  public TenantListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.tenant;
  }

  @Override
  protected String getUpdateNameTokens() {
    return NameTokens.updateTenant;
  }

  @Override
  protected void requestData() {
    new GetAllTenantCommand().dispatch(new GotAllTenant() {
      @Override
      public void got(List<SimpleTenant> tenants) {
        getView().setData(tenants);
      }
    });

  }

}
