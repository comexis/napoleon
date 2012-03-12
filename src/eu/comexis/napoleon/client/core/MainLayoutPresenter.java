package eu.comexis.napoleon.client.core;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

import eu.comexis.napoleon.client.events.SelectedMenuEvent;
import eu.comexis.napoleon.client.events.SelectedMenuEvent.SelectedMenuHandler;
import eu.comexis.napoleon.client.events.SetTitleEvent;
import eu.comexis.napoleon.client.events.SetTitleEvent.SetTitleHandler;
import eu.comexis.napoleon.client.utils.ApplicationHelper;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;

public class MainLayoutPresenter extends
    Presenter<MainLayoutPresenter.MyView, MainLayoutPresenter.MyProxy> implements SelectedMenuHandler, SetTitleHandler {

  @ProxyCodeSplit
  public interface MyProxy extends Proxy<MainLayoutPresenter> {
  }
  
  public enum Menus {
    //order is important here !! We use the ordinal() method in
    // onSelectedMenu()
    OWNER, TENANT, REAL_ESTATE, LEASE, PAYMENT;
  }

  public interface MyView extends View {
    public void setLogo(String logo);

    public void setLogoutUrl(String logoutUrl);

    public void setUserName(String userName);
    
    public void selectLeftMenu(int id);
    
    public void hideLeftMenu();

    public void setTitle(String title);

  }
  
  private HandlerRegistration selectedMenuEventHandlerRegistration;
  private HandlerRegistration setTitleHandlerRegistration;

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
  protected void onBind() {
    super.onBind();
    selectedMenuEventHandlerRegistration = getEventBus().addHandler(SelectedMenuEvent.getType(), this);
    setTitleHandlerRegistration = getEventBus().addHandler(SetTitleEvent.getType(), this);
  }
  
  @Override
  protected void onUnbind() {
    super.onUnbind();
    
    if (selectedMenuEventHandlerRegistration != null){
      selectedMenuEventHandlerRegistration.removeHandler();
      selectedMenuEventHandlerRegistration = null;
    }
    
    if (setTitleHandlerRegistration != null){
      setTitleHandlerRegistration.removeHandler();
      setTitleHandlerRegistration = null;
    }
  }

  @Override
  protected void revealInParent() {
    RevealRootLayoutContentEvent.fire(this, this);
  }

  private void initView() {
    MyView view = getView();

    ApplicationUser loggedInUser = ApplicationHelper.INSTANCE.getLoggedUser();
    Company loggedCompany = ApplicationHelper.INSTANCE.getLoggedCompany();

    view.setLogo(loggedCompany.getLogo());
    view.setUserName(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
    view.setLogoutUrl(ApplicationHelper.INSTANCE.getLogoutUrl());

  }

  @Override
  public void onSelectedMenu(SelectedMenuEvent event) {
    if (event.getMenu() != null){
      getView().selectLeftMenu(event.getMenu().ordinal());
    }else{
      getView().hideLeftMenu();
    }
    
  }

  @Override
  public void onSetTitle(SetTitleEvent event) {
    String title = event.getTitle();
    getView().setTitle(title);    
  }
}
