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
    
    public void hideActiveFilter();
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
      return;
    }
  
    //filter don't change since the last call
    if (newFilterValue.equals(filterValue)){
      return;
    }
    
    if (filterValue == null || !newFilterValue.startsWith(filterValue)){
      filteredDatas = datas;
    }
    
    filterValue = newFilterValue;
    
    List<T> newDatas = new ArrayList<T>();
    
    for (T data : filteredDatas){
      if (!filter.filter(data, newFilterValue) && testActif(showOnlyActive, data)){
    		  newDatas.add(data);
      }
    }
    
    getView().setData(newDatas);
    filteredDatas = newDatas;
    
  }
  
  public void filterActive(boolean showOnlyActive){
    
    List<T> dataList = filteredDatas == null ? this.datas : filteredDatas;
    
    if (dataList == null){
      return;
    }
    
    if (!showOnlyActive){
      //reset the list
      getView().setData(dataList);
      return;
    }
    
    //filter
    List<T> newDatas = new ArrayList<T>();
    
    for (T data : dataList){
      if (testActif(showOnlyActive, data)){
          newDatas.add(data);
      }
    }
    
    getView().setData(newDatas);
    
  }


  private boolean testActif(boolean showOnlyActive, T data) {   
    //don't test on the active status if we don't manage it in the list
    if (!manageActive() || !(data instanceof EnablableEntity)){
      return true;
    }
    
    EnablableEntity entity = (EnablableEntity) data;
  
    return showOnlyActive ? EntityStatus.isActif(entity.getEntityStatus()) : true;
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
    if (manageActive()){
      this.filterActive(checked); 
    }
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
  
  protected abstract boolean manageActive();

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
    
    if (!manageActive()){
      getView().hideActiveFilter();
    }
    
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
    
    //show only active TODO: we should only retrieve active entity from the server...
    filterActive(true);
   
    doReveal();
  }
}
