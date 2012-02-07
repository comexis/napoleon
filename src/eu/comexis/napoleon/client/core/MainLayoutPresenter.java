package eu.comexis.napoleon.client.core;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

import eu.comexis.napoleon.client.utils.ApplicationHelper;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;

public class MainLayoutPresenter extends
    Presenter<MainLayoutPresenter.MyView, MainLayoutPresenter.MyProxy> {

  @ProxyCodeSplit
  public interface MyProxy extends Proxy<MainLayoutPresenter> {
  }

  public interface MyView extends View {
    public void setLogo(String logo);

    public void setLogoutUrl(String logoutUrl);

    public void setUserName(String userName);

  }

  /**
   * Content slot are used in leaf presenters, inside their {@link #revealInParent} method.
   */
  @ContentSlot
  public static final Type<RevealContentHandler<?>> MAIN_CONTENT =
      new Type<RevealContentHandler<?>>();
  @ContentSlot
  public static final Type<RevealContentHandler<?>> LEFT_CONTENT =
      new Type<RevealContentHandler<?>>();

  @Inject
  public MainLayoutPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
    super(eventBus, view, proxy);
    initView();
  }

  @Override
  protected void revealInParent() {
    RevealRootLayoutContentEvent.fire(this, this);
  }

  private void initView() {
    MyView view = getView();

    ApplicationUser loggedInUser = ApplicationHelper.INSTANCE.getLoggedUser();
    Company loggedCompany = ApplicationHelper.INSTANCE.getLoggedCompany();

    view.setLogo(loggedCompany.getName());
    view.setUserName(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
    view.setLogoutUrl(ApplicationHelper.INSTANCE.getLogoutUrl());

  }
}
