package eu.comexis.napoleon.client.core.estate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
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
import eu.comexis.napoleon.client.core.estate.RealEstateUpdateUiHandlers.HasRealEstateUpdateUiHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllAssoc;
import eu.comexis.napoleon.client.rpc.callback.GotAllCondo;
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotAllOwner;
import eu.comexis.napoleon.client.rpc.callback.GotAssoc;
import eu.comexis.napoleon.client.rpc.callback.GotCondo;
import eu.comexis.napoleon.client.rpc.callback.GotCountry;
import eu.comexis.napoleon.client.rpc.callback.GotRealEstate;
import eu.comexis.napoleon.client.rpc.callback.UpdatedRealEstate;
import eu.comexis.napoleon.shared.command.association.GetAllAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAssocCommand;
import eu.comexis.napoleon.shared.command.condo.GetAllCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetCondoCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetCountryCommand;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.validation.RealEstateValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class RealEstateUpdatePresenter extends
    Presenter<RealEstateUpdatePresenter.MyView, RealEstateUpdatePresenter.MyProxy> implements
    RealEstateUpdateUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.updateRealEstate)
  public interface MyProxy extends ProxyPlace<RealEstateUpdatePresenter> {
  }
  public interface MyView extends View, HasRealEstateUpdateUiHandler {
    public void displayError(String error);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void fillCityList(List<String> cities);

    public void fillCountryList(List<Country> countries);

    public void fillOwnerList(List<SimpleOwner> owners);

    public void fillCondoList(List<String> condoNames);

    public void fillCondo(Condo cdo);

    public void fillAssoc(Association assoc);

    public void fillAssocList(List<String> assocNames);

    public void fillPostalCodeList(List<String> postCdes);

    public void fillSquareList(List<String> squares);

    public String getSelectedCountry();

    public void setRealEstate(RealEstate e);

    public RealEstate updateRealEstate(RealEstate o);
  }

  public static final String UUID_PARAMETER = "uuid";

  private static final Logger LOG = Logger.getLogger(RealEstateDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private RealEstate realEstate;
  private Condo cdo;
  private Country country;
  private RealEstateValidator validator;

  @Inject
  public RealEstateUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = new RealEstateValidator();
  }

  @Override
  public void onButtonCancelClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
    // add the id of the realEstate to load
    GWT.log("cancel click on " + realEstate.getId());
    myRequest = myRequest.with(UUID_PARAMETER, realEstate.getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonSaveClick() {
    getView().updateRealEstate(realEstate);

    List<ValidationMessage> validationMessages = validator.validate(realEstate);

    if (validationMessages.isEmpty()) {
      saveRealEstate();
    } else {
      getView().displayValidationMessage(validationMessages);
    }

  }

  @Override
  public void onCitySelect(String selectedCity) {
    if (RealEstateUpdatePresenter.this.country != null) {
      for (City c : RealEstateUpdatePresenter.this.country.getCities()) {
        if (c.getName().equals(selectedCity) && c.getSquareList() != null) {
          getView().fillSquareList(c.getSquareList());
          break;
        } else {
          getView().fillSquareList(new ArrayList<String>());
        }
      }
    }
  }

  @Override
  public void onCountrySelect(String selectedCountry) {

    if (selectedCountry == null || selectedCountry.length() == 0) {
      return;
    }

    // get all the already encoded cities for the given country
    // -> get a full country object from the server
    // This object contains the list of cities of the given country
    GetCountryCommand cmd = new GetCountryCommand();
    cmd.setName(selectedCountry);
    cmd.dispatch(new GotCountry() {
      @Override
      public void got(Country cnty) {
        RealEstateUpdatePresenter.this.country = cnty;
        if (cnty != null) {
          List<String> lst = cnty.getListPostalCode();
          getView().fillPostalCodeList(lst);
          if (lst != null && !lst.isEmpty()) {
            onPostalCodeSelect(lst.get(0));
          }
        }
      }
    });

  }

  @Override
  public void onPostalCodeSelect(String selectedPostalCode) {

    if (selectedPostalCode == null || selectedPostalCode.length() == 0) {
      return;
    }
    List<String> lstCities = new ArrayList<String>();
    if (RealEstateUpdatePresenter.this.country != null) {
      for (City c : RealEstateUpdatePresenter.this.country
          .getListCitiesByPostalCode(selectedPostalCode)) {
        lstCities.add(c.getName());
      }
    }
    getView().fillCityList(lstCities);

  }

  @Override
  public void onCondoSelect(String selectedCondo) {

    if (selectedCondo == null || selectedCondo.length() == 0) {
      return;
    }
    GetCondoCommand cmd = new GetCondoCommand();
    cmd.setName(selectedCondo);
    cmd.dispatch(new GotCondo() {
      @Override
      public void got(Condo cdo) {
        getView().fillCondo(cdo);
      }
    });
  }

  @Override
  public void onAssocSelect(String selectedAssoc) {

    if (selectedAssoc == null || selectedAssoc.length() == 0) {
      return;
    }
    GetAssocCommand cmd = new GetAssocCommand();
    cmd.setName(selectedAssoc);
    cmd.dispatch(new GotAssoc() {
      @Override
      public void got(Association assoc) {
        getView().fillAssoc(assoc);
      }
    });
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

  public void saveRealEstate() {
    // Save it
    UpdateRealEstateCommand cmd = new UpdateRealEstateCommand();
    cmd.setRealEstate(realEstate);
    cmd.dispatch(new UpdatedRealEstate() {
      @Override
      public void got(RealEstate realEstate) {
        if (realEstate != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
          myRequest = myRequest.with(UUID_PARAMETER, realEstate.getId());
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The realEstate cannot be save");
        }
      }
    });
    // On success display the realEstate detail screen
    // On failure display the realEstate update screen with the reason why it cannot be saved

  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().setRealEstateUpdateUiHandler(this);
    init();
  }

  @Override
  protected void onReset() {
    super.onReset();
    if (id != null && !"new".equals(id)) { // call the server to get the requested owner
      new GetRealEstateCommand(id).dispatch(new GotRealEstate() {
        @Override
        public void got(RealEstate realEstate) {
          RealEstateUpdatePresenter.this.realEstate = realEstate;
          getView().setRealEstate(realEstate);
        }
      });
    } else {
      realEstate = new RealEstate();
      getView().setRealEstate(realEstate);
    }
  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  private void init() {
    new GetAllCountriesCommand().dispatch(new GotAllCountries() {
      @Override
      public void got(List<Country> countries) {
        getView().fillCountryList(countries);

      }
    });
    new GetAllOwnerCommand().dispatch(new GotAllOwner() {
      @Override
      public void got(List<SimpleOwner> owners) {
        getView().fillOwnerList(owners);
      }
    });
    new GetAllAssocCommand().dispatch(new GotAllAssoc() {
      @Override
      public void got(List<String> assocNames) {
        getView().fillAssocList(assocNames);
      }
    });
    new GetAllCondoCommand().dispatch(new GotAllCondo() {
      @Override
      public void got(List<String> condoNames) {
        getView().fillCondoList(condoNames);
      }
    });
  }
}