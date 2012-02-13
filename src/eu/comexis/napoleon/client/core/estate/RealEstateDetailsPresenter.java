package eu.comexis.napoleon.client.core.estate;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateDetailUiHandlers.HasRealEstateDetailUiHandlers;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotRealEstate;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class RealEstateDetailsPresenter extends
    Presenter<RealEstateDetailsPresenter.MyView, RealEstateDetailsPresenter.MyProxy> implements
    RealEstateDetailUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.realEstate)
  public interface MyProxy extends ProxyPlace<RealEstateDetailsPresenter> {
  }
  public interface MyView extends View, HasRealEstateDetailUiHandlers {
    public void setRealEstate(RealEstate e,SimpleOwner o,Condo cdo);
  }

  public static final String UUID_PARAMETER = "uuid";

  private static final Logger LOG = Logger.getLogger(RealEstateDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private RealEstate realEstate;

  @Inject
  public RealEstateDetailsPresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }

  @Override
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstatelist);
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.updateRealEstate);
    // add the id of the realEstate to load
    myRequest = myRequest.with(UUID_PARAMETER, RealEstateDetailsPresenter.this.realEstate.getId());
    placeManager.revealPlace(myRequest);
  }

  /**
   * Retrieve the id of the realEstate to show it
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

  @Override
  protected void onBind() {
    super.onBind();

    getView().setRealEstateDetailUiHandler(this);
  }

  @Override
  protected void onReset() {
    super.onReset();

    new GetRealEstateCommand(id).dispatch(new GotRealEstate() {

      @Override
      public void got(RealEstate realEstate,SimpleOwner owner,Condo cdo) {
        RealEstateDetailsPresenter.this.realEstate = realEstate;
        getView().setRealEstate(realEstate,owner,cdo);
      }
    });

  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

}