package eu.comexis.napoleon.client.core.owner;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerListUiHandlers.HasOwnerListUiHandlers;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllOwner;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerListPresenter extends
		Presenter<OwnerListPresenter.MyView, OwnerListPresenter.MyProxy> implements OwnerListUiHandlers {

	@ProxyCodeSplit
	@NameToken(NameTokens.ownerlist)
	public interface MyProxy extends ProxyPlace<OwnerListPresenter> {
	}

	public interface MyView extends View, HasOwnerListUiHandlers {
		public void setData(List<SimpleOwner> owners);
	}

	@Inject
	public OwnerListPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
		
		
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
		Window.alert("Selected owner : "+selectedOwner);
		
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

}
