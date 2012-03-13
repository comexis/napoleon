package eu.comexis.napoleon.client.core;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.events.SelectedMenuEvent;
import eu.comexis.napoleon.client.events.SetTitleEvent;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;

public class DashBoardPresenter extends
    Presenter<DashBoardPresenter.MyView, DashBoardPresenter.MyProxy> {

  @ProxyCodeSplit
  @NameToken(NameTokens.dashboard)
  public interface MyProxy extends ProxyPlace<DashBoardPresenter> {
  }

  public interface MyView extends View {
  }

  @Inject
  public DashBoardPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
    super(eventBus, view, proxy);
  }

  @Override
  protected void onBind() {
    super.onBind();
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    SelectedMenuEvent.fire(getEventBus(), null);
    SetTitleEvent.fire(getEventBus(), Literals.INSTANCE.dashboardTitle());
  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
}
