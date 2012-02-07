package eu.comexis.napoleon.client.core.tenant;

import static eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter.UUID_PARAMETER;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
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
import eu.comexis.napoleon.client.core.tenant.TenantListUiHandlers.HasTenantListUiHandlers;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllTenant;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public class TenantListPresenter extends
    Presenter<TenantListPresenter.MyView, TenantListPresenter.MyProxy> implements
    TenantListUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.tenantlist)
  public interface MyProxy extends ProxyPlace<TenantListPresenter> {
  }

  public interface MyView extends View, HasTenantListUiHandlers {
    public void setData(List<SimpleTenant> tenants);
  }

  private PlaceManager placeManager;

  @Inject
  public TenantListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);

    this.placeManager = placeManager;

  }

  @Override
  public void onSelect(SimpleTenant selectedTenant) {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.tenant);
    // add the id of the tenant to load
    myRequest = myRequest.with(UUID_PARAMETER, selectedTenant.getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  protected void onBind() {
    super.onBind();

    getView().setTenantListUiHandler(this);
  }

  @Override
  protected void onReset() {
    super.onReset();

    new GetAllTenantCommand().dispatch(new GotAllTenant() {
      @Override
      public void got(List<SimpleTenant> tenants) {
        getView().setData(tenants);

      }
    });
  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

}
