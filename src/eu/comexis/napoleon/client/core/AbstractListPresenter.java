package eu.comexis.napoleon.client.core;

import static eu.comexis.napoleon.client.core.party.PartyUpdatePresenter.UUID_PARAMETER;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.shared.model.Identifiable;

public abstract class AbstractListPresenter<T extends Identifiable, V extends AbstractListPresenter.MyView<T>, P extends Proxy<?>>
    extends Presenter<V, P> implements ListUiHandlers<T> {

  public interface MyView<T> extends View, HasListUiHandlers<T> {
    public void dataIsLoading();

    public void setData(List<T> data);
  }

  private PlaceManager placeManager;

  @Inject
  public AbstractListPresenter(EventBus eventBus, V view, P proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);

    this.placeManager = placeManager;
  }

  protected abstract String getDetailsNameTokens();

  protected abstract String getUpdateNameTokens();

  @Override
  protected void onBind() {
    super.onBind();

    getView().setPresenter(this);
  }

  @Override
  public void onButtonBackToDashBoardClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.dashboard);
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonNewClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, "new");
    placeManager.revealPlace(myRequest);
  }

  @Override
  protected void onReset() {
    GWT.log("onReset");
    super.onReset();

    getView().dataIsLoading();

    requestData();

  }

  @Override
  public void onSelect(T data) {
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, data.getId());
    placeManager.revealPlace(myRequest);
  }

  protected abstract void requestData();

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
}
