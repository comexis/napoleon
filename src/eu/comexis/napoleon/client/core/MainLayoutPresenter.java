package eu.comexis.napoleon.client.core;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import eu.comexis.napoleon.client.utils.ApplicationHelper;
import eu.comexis.napoleon.shared.model.AppUser;

public class MainLayoutPresenter extends
		Presenter<MainLayoutPresenter.MyView, MainLayoutPresenter.MyProxy> {

	public interface MyView extends View {
		public void setUserName(String userName);
		public void setLogo(String logo);
		public void setLogoutUrl(String logoutUrl);
		
	}

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<MainLayoutPresenter> {
	}

	@Inject
	public MainLayoutPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
		initView();
	}

	private void initView() {
		MyView view = getView();
		
		AppUser loggedInUser = ApplicationHelper.INSTANCE.getLoggedUser();
		view.setLogo(loggedInUser.getClient().getName());
		view.setUserName(loggedInUser.getFirstName()+ " "+ loggedInUser.getLastName());
		view.setLogoutUrl(ApplicationHelper.INSTANCE.getLogoutUrl());
		
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> MAIN_CONTENT = new Type<RevealContentHandler<?>>();

	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}
}
