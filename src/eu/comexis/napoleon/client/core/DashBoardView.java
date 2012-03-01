package eu.comexis.napoleon.client.core;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.Napoleon;
import eu.comexis.napoleon.client.events.DisplayMessageEvent;
import eu.comexis.napoleon.client.events.HideMessageEvent;

public class DashBoardView extends ViewImpl implements DashBoardPresenter.MyView {

  public interface Binder extends UiBinder<Widget, DashBoardView> {
  }

  private final Widget widget;

  @Inject
  public DashBoardView(final Binder binder) {
    widget = binder.createAndBindUi(this);
  }

  @Override
  public Widget asWidget() {
    return widget;
  } 
}
