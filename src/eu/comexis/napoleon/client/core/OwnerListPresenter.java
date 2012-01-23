package eu.comexis.napoleon.client.core;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import eu.comexis.napoleon.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;

public class OwnerListPresenter extends
		Presenter<OwnerListPresenter.MyView, OwnerListPresenter.MyProxy> {

	public interface MyView extends View {
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
	}
}
