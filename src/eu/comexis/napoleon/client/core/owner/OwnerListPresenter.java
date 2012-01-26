package eu.comexis.napoleon.client.core.owner;

import static eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter.UUID_PARAMETER;

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
import eu.comexis.napoleon.client.core.owner.OwnerListUiHandlers.HasOwnerListUiHandlers;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllOwner;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerListPresenter extends
		Presenter<OwnerListPresenter.MyView, OwnerListPresenter.MyProxy>
		implements OwnerListUiHandlers {

	@ProxyCodeSplit
	@NameToken(NameTokens.ownerlist)
	public interface MyProxy extends ProxyPlace<OwnerListPresenter> {
	}

	public interface MyView extends View, HasOwnerListUiHandlers {
		public void setData(List<SimpleOwner> owners);
	}
	
	private PlaceManager placeManager;

	@Inject
	public OwnerListPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		
		this.placeManager = placeManager;

	}

	@Override
	protected void onBind() {
		super.onBind();

		getView().setOwnerListUiHandler(this);
	}

	@Override
	protected void onReset() {
		super.onReset();

		new GetAllOwnerCommand().dispatch(new GotAllOwner() {
			@Override
			public void got(List<SimpleOwner> owners) {
				getView().setData(owners);

			}
		});
	}

	@Override
	public void onSelect(SimpleOwner selectedOwner) {
		PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
		// add the id of the owner to load
		myRequest = myRequest.with(UUID_PARAMETER, selectedOwner.getId());
		placeManager.revealPlace(myRequest);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

}