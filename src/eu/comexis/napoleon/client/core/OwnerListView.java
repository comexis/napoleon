package eu.comexis.napoleon.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class OwnerListView extends ViewImpl implements
		OwnerListPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, OwnerListView> {
	}

	@Inject
	public OwnerListView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
