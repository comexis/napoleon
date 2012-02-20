package eu.comexis.napoleon.client.core.tenant;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.party.PartyUpdatePresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotTenant;
import eu.comexis.napoleon.client.rpc.callback.UpdatedTenant;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.validation.PartyValidator;
import eu.comexis.napoleon.shared.validation.TenantValidator;

public class TenantUpdatePresenter extends
    PartyUpdatePresenter<Tenant, TenantUpdatePresenter.MyView, TenantUpdatePresenter.MyProxy>
    implements TenantUpdateUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.updateTenant)
  public interface MyProxy extends ProxyPlace<TenantUpdatePresenter> {
  }

  public interface MyView extends PartyUpdatePresenter.MyView<Tenant> {
  }

  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(TenantDetailsPresenter.class.getName());

  @Inject
  public TenantUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  protected PartyValidator<Tenant> createValidator() {
    return new TenantValidator();
  }

  @Override
  protected Tenant createNewDataModel() {
    return new Tenant();
  }

  @Override
  protected void requestData(String id) {
    new GetTenantCommand(id).dispatch(new GotTenant() {
      @Override
      public void got(Tenant tenant) {
        setDataObjectModel(tenant);
        getView().setData(tenant);
      }
    });

  }

  @Override
  protected void save() {
    new UpdateTenantCommand(getDataObjectModel()).dispatch(new UpdatedTenant() {
      @Override
      public void updated(Tenant tenant) {
        if (tenant != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.tenant);
          myRequest = myRequest.with(UUID_PARAMETER, tenant.getId());
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The tenant cannot be save");
        }
      }
    });

  }
  
  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.tenant;
  }
  
  @Override
  protected String getListNameTokens() {
    return NameTokens.tenantlist;
  }
}
