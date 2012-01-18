package eu.comexis.napoleon.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainLayoutView extends ViewImpl implements
		MainLayoutPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MainLayoutView> {
	}

	@Inject
	public MainLayoutView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
