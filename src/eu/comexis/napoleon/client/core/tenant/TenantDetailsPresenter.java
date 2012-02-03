package eu.comexis.napoleon.client.core.tenant;

import static eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter.UUID_PARAMETER;

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
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotTenant;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.client.core.tenant.TenantDetailUiHandlers.HasTenantDetailUiHandlers;

public class TenantDetailsPresenter extends
		Presenter<TenantDetailsPresenter.MyView, TenantDetailsPresenter.MyProxy> implements TenantDetailUiHandlers{

	public static final String UUID_PARAMETER = "uuid";
	private static final Logger LOG = Logger.getLogger(TenantDetailsPresenter.class.getName());
	
	public interface MyView extends View,HasTenantDetailUiHandlers{
		public void setTenant(Tenant o);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.tenant)
	public interface MyProxy extends ProxyPlace<TenantDetailsPresenter> {
	}
	
	private PlaceManager placeManager;
	private String id;
	private Tenant tenant;

	@Inject
	public TenantDetailsPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}
	@Override
  protected void onBind() {
    super.onBind();

    getView().setTenantDetailUiHandler(this);
  }
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		
		new GetTenantCommand(id).dispatch(new GotTenant() {
			
			@Override
			public void got(Tenant tenant) {
				TenantDetailsPresenter.this.tenant = tenant;
				getView().setTenant(tenant);
			}
		});
		
		
	}
	@Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.updateTenant);
    // add the id of the tenant to load
    myRequest = myRequest.with(UUID_PARAMETER, TenantDetailsPresenter.this.tenant.getId());
    placeManager.revealPlace(myRequest);
  }
	@Override
  public void onButtonBackToDashBoardClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.dashboard);
    placeManager.revealPlace(myRequest);
  }
	/**
	 * Retrieve the id of the tenant to show it
	 */
	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);

		// In the next call, "view" is the default value,
		// returned if "action" is not found on the URL.
		id = placeRequest.getParameter(UUID_PARAMETER, null);

		if (id == null || id.length() == 0){
			if (LogConfiguration.loggingIsEnabled()){
				LOG.severe("invalid id is null or empty");
			}
			placeManager.revealErrorPlace(placeRequest.getNameToken());
		}
	}

}
