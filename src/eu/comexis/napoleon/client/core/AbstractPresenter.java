package eu.comexis.napoleon.client.core;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.query.client.GQuery;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.events.DisplayMessageEvent;
import eu.comexis.napoleon.client.events.HideMessageEvent;
import eu.comexis.napoleon.client.events.SelectedMenuEvent;
import eu.comexis.napoleon.client.events.SetTitleEvent;

public abstract class AbstractPresenter<V extends View, P extends Proxy<?>> extends Presenter<V, P>  {

  private PlaceManager placeManager;
  

  public AbstractPresenter(boolean autoBind, EventBus eventBus, V view, P proxy) {
    super(autoBind, eventBus, view, proxy);
    // TODO Auto-generated constructor stub
  }
  
  public AbstractPresenter(EventBus eventBus, V view, P proxy) {
    super(eventBus, view, proxy);
  }
  
  @Inject
  public AbstractPresenter(EventBus eventBus, V view, P proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }
  
  protected PlaceManager getPlaceManager() {
    return placeManager;
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    GQuery.$(getView().asWidget()).fadeOut(0);
    SelectedMenuEvent.fire(getEventBus(), getMenu());
    SetTitleEvent.fire(getEventBus(), getTitle());
    DisplayMessageEvent.fire(getEventBus(), "Chargement des données. Veuillez attendre...");
    
   
  }
  
  @Override
  protected void onHide() {
    super.onHide();
    GQuery.$(getView().asWidget()).fadeOut(500);
  }
  
  protected void doReveal(){
    GQuery.$(getView().asWidget()).fadeIn(500);
    getEventBus().fireEvent(new HideMessageEvent());
  }
  
  
  protected abstract Menus getMenu();
  
  protected abstract String getTitle();
  

}
