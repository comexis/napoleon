package eu.comexis.napoleon.client.core.tenant;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.party.PartyDetailsPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotTenant;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantResponse;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.Tenant;

public class TenantDetailsPresenter extends
    PartyDetailsPresenter<Tenant, TenantDetailsPresenter.MyView, TenantDetailsPresenter.MyProxy>{

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
      }
    });
  }
  
  @Override
  protected void saveFile(FileDescriptor file) {
    new UpdateTenantCommand(getData()).dispatch(new AsyncCallback<UpdateTenantResponse>() {
      
      @Override
      public void onSuccess(UpdateTenantResponse result) {}
      
      @Override
      public void onFailure(Throwable caught) {
        //TODO improve that
        Window.alert("Impossible de lier le fichier au locataire. Veuillez reessayer.");
        
      }
    });

    
  }
  
  @Override
  protected Menus getMenu() {
    return Menus.TENANT;
  }
  
  @Override
  protected String getTitle() {
    return Literals.INSTANCE.tenantDetailsTitle();
  }

}
