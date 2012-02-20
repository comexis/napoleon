package eu.comexis.napoleon.client.core.tenant;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.owner.OwnerDetailUiHandlers;
import eu.comexis.napoleon.client.core.party.PartyDetailsPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotTenant;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.model.Tenant;

public class TenantDetailsPresenter extends
    PartyDetailsPresenter<Tenant, TenantDetailsPresenter.MyView, TenantDetailsPresenter.MyProxy>
    implements OwnerDetailUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.tenant)
  public interface MyProxy extends ProxyPlace<TenantDetailsPresenter> {
  }
  
  public interface MyView extends PartyDetailsPresenter.MyView<Tenant>{
    
  }

  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(TenantDetailsPresenter.class.getName());

  @Inject
  public TenantDetailsPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  protected String getListNameTokens() {
    return NameTokens.tenantlist;
  }

  @Override
  protected String getUpdateDetailsNameTokens() {
    return NameTokens.updateTenant;
  }

  @Override
  protected void requestData(String id) {
    new GetTenantCommand(id).dispatch(new GotTenant() {

      @Override
      public void got(Tenant tenant) {
        setData(tenant);
        getView().setData(tenant);
      }
    });

  }

}
