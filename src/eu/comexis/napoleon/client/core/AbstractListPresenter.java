package eu.comexis.napoleon.client.core;

import static eu.comexis.napoleon.client.core.party.PartyUpdatePresenter.UUID_PARAMETER;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.shared.model.EnablableEntity;
import eu.comexis.napoleon.shared.model.EntityStatus;
import eu.comexis.napoleon.shared.model.Identifiable;

public abstract class AbstractListPresenter<T extends Identifiable, V extends AbstractListPresenter.MyView<T>, P extends Proxy<?>>
    extends AbstractPresenter<V, P> implements ListUiHandlers<T> {

  public interface ListFilter<T>{
    public boolean filter(T data, String filter);
  }
  
  public interface MyView<T> extends View, HasListUiHandlers<T> {
    public void dataIsLoading();

    public void resetFocus();
    
    public void setData(List<T> data);
  }

  private List<T> datas;
  private List<T> filteredDatas;
  private String filterValue;
  private ListFilter<T> filter;

  @Inject
  public AbstractListPresenter(EventBus eventBus, V view, P proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
    filter = createFilter();
  }

  @Override
  public void filter(String newFilterValue, boolean showOnlyActive) {
    
    //reset
    if (newFilterValue == null || newFilterValue.length() == 0){
      getView().setData(datas);
      filteredDatas = datas;
      filterValue = null;
      //return;
    }
    
    //filter don't change since the last call
    if (newFilterValue.equals(filterValue)){
      //return;
    }
    
    if (filterValue == null || !newFilterValue.startsWith(filterValue)){
      filteredDatas = datas;
    }
    
    filterValue = newFilterValue;
    
    List<T> newDatas = new ArrayList<T>();
    
    for (T data : filteredDatas){
      if (!filter.filter(data, newFilterValue)){
    	  if(!(showOnlyActive && data instanceof EnablableEntity && !EntityStatus.ACTIVE.equals((((EnablableEntity)data)).getEntityStatus()))){
    		  newDatas.add(data);
    	  }
        
      }
    }
    
    getView().setData(newDatas);
    filteredDatas = newDatas;
    
  }


  @Override
  public void onButtonBackToDashBoardClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.dashboard);
    getPlaceManager().revealPlace(myRequest);
  }

  @Override
  public void onButtonNewClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, "new");
    getPlaceManager().revealPlace(myRequest);
  }
  
  @Override
  public void onShowOnlyActiveClicked(boolean checked, String filterString) {
    this.filter(filterString, checked); 
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

  protected abstract ListFilter<T> createFilter();

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
    
    //getEventBus().fireEvent(new DisplayMessageEvent(Literals.INSTANCE.dataLoading()));

    requestData();
    
    

  }

  protected abstract void requestData();
 
  
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  protected void setDatas(List<T> datas) {
    this.datas = datas;
    getView().setData(datas);

    
    doReveal();
  }
}
