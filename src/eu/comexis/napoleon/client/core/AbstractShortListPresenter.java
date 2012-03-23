package eu.comexis.napoleon.client.core;

import static eu.comexis.napoleon.client.core.party.PartyUpdatePresenter.UUID_PARAMETER;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.events.DisplayMessageEvent;
import eu.comexis.napoleon.client.events.HideMessageEvent;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.shared.model.Identifiable;

public abstract class AbstractShortListPresenter<T extends Identifiable, V extends AbstractShortListPresenter.MyView<T>, P extends Proxy<?>>
    extends AbstractPresenter<V, P> implements ShortListUiHandlers<T> {
  
  public interface MyView<T> extends View, HasShortListUiHandlers<T> {
    public void dataIsLoading();

    public void resetFocus();
    
    public void setData(String title,List<T> data);
  }

  private List<T> datas;

  @Inject
  public AbstractShortListPresenter(EventBus eventBus, V view, P proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  public void onButtonNewClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, "new");
    getPlaceManager().revealPlace(myRequest);
  }
  
  @Override
  public void onButtonDeleteClick() {
    // to be implemented
  }

  @Override
  public void onSelect(T data) {
    if (data == null){
      return;
    }
    
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, data.getId());
    getPlaceManager().revealPlace(myRequest);
  }
  
  protected abstract String getDetailsNameTokens();
  
  protected abstract String getUpdateNameTokens();

  @Override
  protected void onBind() {
    super.onBind();

    getView().setPresenter(this);
  }

  @Override
  protected void onReset() {
    GWT.log("onReset");
    super.onReset();
    
    getView().resetFocus();
    getView().dataIsLoading();
    
    getEventBus().fireEvent(new DisplayMessageEvent(Literals.INSTANCE.dataLoading()));

    requestData();
    
    

  }

  protected abstract void requestData();
  
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  protected void setDatas(String title,List<T> datas) {
    this.datas = datas;
    getView().setData(title,datas);

    getEventBus().fireEvent(new HideMessageEvent());
    doReveal();
  }
}
