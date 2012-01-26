package eu.comexis.napoleon.client.core.owner;

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
import eu.comexis.napoleon.client.rpc.callback.GotOwner;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.model.Owner;

public class OwnerDetailsPresenter extends
		Presenter<OwnerDetailsPresenter.MyView, OwnerDetailsPresenter.MyProxy> {

	public static final String UUID_PARAMETER = "uuid";
	private static final Logger LOG = Logger.getLogger(OwnerDetailsPresenter.class.getName());
	
	public interface MyView extends View {
		public void setOwner(Owner o);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.owner)
	public interface MyProxy extends ProxyPlace<OwnerDetailsPresenter> {
	}
	
	private PlaceManager placeManager;
	private String id;
	private Owner owner;

	@Inject
	public OwnerDetailsPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		
		new GetOwnerCommand(id).dispatch(new GotOwner() {
			
			@Override
			public void got(Owner owner) {
				OwnerDetailsPresenter.this.owner = owner;
				getView().setOwner(owner);
			}
		});
		
		
	}
	
	/**
	 * Retrieve the id of the owner to show it
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
