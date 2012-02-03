package eu.comexis.napoleon.client.core.estate;

import static eu.comexis.napoleon.client.core.estate.RealEstateDetailsPresenter.UUID_PARAMETER;

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
import eu.comexis.napoleon.client.core.estate.RealEstateListUiHandlers.HasRealEstateListUiHandlers;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllRealEstate;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateListPresenter extends
		Presenter<RealEstateListPresenter.MyView, RealEstateListPresenter.MyProxy>
		implements RealEstateListUiHandlers {

	@ProxyCodeSplit
	@NameToken(NameTokens.realEstatelist)
	public interface MyProxy extends ProxyPlace<RealEstateListPresenter> {
	}

	public interface MyView extends View, HasRealEstateListUiHandlers {
		public void setData(List<SimpleRealEstate> realEstates);
	}
	
	private PlaceManager placeManager;

	@Inject
	public RealEstateListPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		
		this.placeManager = placeManager;

	}

	@Override
	protected void onBind() {
		super.onBind();

		getView().setRealEstateListUiHandler(this);
	}

	@Override
	protected void onReset() {
		super.onReset();

		new GetAllRealEstateCommand().dispatch(new GotAllRealEstate() {
			@Override
			public void got(List<SimpleRealEstate> realEstates) {
				getView().setData(realEstates);

			}
		});
	}

	@Override
	public void onSelect(SimpleRealEstate selectedRealEstate) {
		PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
		// add the id of the realEstate to load
		myRequest = myRequest.with(UUID_PARAMETER, selectedRealEstate.getId());
		placeManager.revealPlace(myRequest);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

}
