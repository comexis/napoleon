package eu.comexis.napoleon.client.core.party;

import static eu.comexis.napoleon.client.core.party.PartyUpdatePresenter.UUID_PARAMETER;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.shared.model.Party;

public abstract class PartyDetailsPresenter<T extends Party, V extends PartyDetailsPresenter.MyView<T>, P extends Proxy<?>>
    extends Presenter<V, P> implements PartyDetailsUiHandlers {

  public interface MyView<T extends Party> extends View, PartyDetailsUiHandlers.HasPresenter {
    public void setData(T p);
  }

  private static final Logger LOG = Logger.getLogger(PartyDetailsPresenter.class.getName());

  private String id;
  private T party;
  private PlaceManager placeManager;

  @Inject
  public PartyDetailsPresenter(final EventBus eventBus, final V view, final P proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }

  protected T getData() {
    return party;
  }

  protected abstract String getListNameTokens();

  public PlaceManager getPlaceManager() {
    return placeManager;
  }

  protected abstract String getUpdateDetailsNameTokens();

  @Override
  protected void onBind() {
    super.onBind();
    getView().setPresenter(this);
  }

  @Override
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(getListNameTokens());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, party.getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  protected void onReset() {
    super.onReset();
    requestData(id);
  }

  /**
   * Retrieve the id of the owner to show it
   */
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);

    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);

    if (id == null || id.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }
  }

  protected abstract void requestData(String id);

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  protected void setData(T party) {
    this.party = party;
  }
}
