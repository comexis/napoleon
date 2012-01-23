package eu.comexis.napoleon.client.core;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainLayoutView extends ViewImpl implements
		MainLayoutPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MainLayoutView> {
	}

	@UiField
	Element userNameElement;

	@UiField
	Element logoElement;

	@UiField
	AnchorElement signOutLink;

	@Inject
	public MainLayoutView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setUserName(String userName) {
		userNameElement.setInnerText(userName);
	}

	@Override
	public void setLogo(String logo) {
		logoElement.setInnerText(logo);

	}

	@Override
	public void setLogoutUrl(String logoutUrl) {
		signOutLink.setHref(logoutUrl);

	}
}
