package eu.comexis.napoleon.client.core;

import java.util.List;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import eu.comexis.napoleon.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerListPresenter extends
		Presenter<OwnerListPresenter.MyView, OwnerListPresenter.MyProxy> {

	public interface MyView extends View {
		public void setData(List<SimpleOwner> owners);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.ownerlist)
	public interface MyProxy extends ProxyPlace<OwnerListPresenter> {
	}

	@Inject
	public OwnerListPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		//TODO move that
		new GetAllOwnerCommand().dispatch(new AsyncCallback<GetAllOwnerResponse>() {
			
			@Override
			public void onSuccess(GetAllOwnerResponse result) {
				getView().setData(result.getOwners());
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("error during GetAllOwnerCommand "+caught);
				
			}
		});
	}
}
