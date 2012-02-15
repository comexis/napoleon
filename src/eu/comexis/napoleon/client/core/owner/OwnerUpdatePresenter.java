package eu.comexis.napoleon.client.core.owner;

import java.util.ArrayList;
import java.util.List;
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
import eu.comexis.napoleon.client.core.owner.OwnerUpdateUiHandlers.HasOwnerUpdateUiHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotCountry;
import eu.comexis.napoleon.client.rpc.callback.GotOwner;
import eu.comexis.napoleon.client.rpc.callback.UpdatedOwner;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetCountryCommand;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerCommand;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.validation.OwnerValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class OwnerUpdatePresenter extends
    Presenter<OwnerUpdatePresenter.MyView, OwnerUpdatePresenter.MyProxy> implements
    OwnerUpdateUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.updateOwner)
  public interface MyProxy extends ProxyPlace<OwnerUpdatePresenter> {
  }
  public interface MyView extends View, HasOwnerUpdateUiHandler {
    public void displayError(String error);

    public void fillCityList(List<String> cities);
    
    public void fillPostalCodeList(List<String> postCdes);

    public void fillCountryList(List<Country> countries);

    public String getSelectedCountry();

    public void setOwner(Owner o);

    public Owner updateOwner(Owner o);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void reset();
  }

  public static final String UUID_PARAMETER = "uuid";

  private static final Logger LOG = Logger.getLogger(OwnerDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private Owner owner;
  private Country country;
  private OwnerValidator validator;
 

  @Inject
  public OwnerUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = new OwnerValidator();

  }

  @Override
  public void onButtonCancelClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, owner.getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonSaveClick() {
    getView().updateOwner(owner);
    
    List<ValidationMessage> validationMessages = validator.validate(owner);
    
    if (validationMessages.isEmpty()){
      saveOwner();
    }else{
      getView().displayValidationMessage(validationMessages);
    }
  
  }

  @Override
  public void onCountrySelect(String selectedCountry) {

    if (selectedCountry == null || selectedCountry.length() == 0){
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
        OwnerUpdatePresenter.this.country = cnty;
        if (cnty!=null){
          List<String> lst = cnty.getListPostalCode();
          getView().fillPostalCodeList(lst);
          if (lst!=null && !lst.isEmpty()){
            onPostalCodeSelect(lst.get(0));
          }
        }
      }
    });

  }
  
  @Override
  public void onPostalCodeSelect(String selectedPostalCode) {

    if (selectedPostalCode == null || selectedPostalCode.length() == 0){
      return;
    }
    List<String> lstCities = new ArrayList<String>();
    if (OwnerUpdatePresenter.this.country!=null){
      for(City c: OwnerUpdatePresenter.this.country.getListCitiesByPostalCode(selectedPostalCode)){
        lstCities.add(c.getName());
      }
    }
    getView().fillCityList(lstCities);

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
    if (id != "new") {
      if (id == null || id.length() == 0) {
        if (LogConfiguration.loggingIsEnabled()) {
          LOG.severe("invalid id is null or empty");
        }
        placeManager.revealErrorPlace(placeRequest.getNameToken());
      }
    }
  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().setOwnerUpdateUiHandler(this);
    init();
  }

  @Override
  protected void onReset() {
    super.onReset();
    if (id != "new") { // call the server to get the requested owner
      new GetOwnerCommand(id).dispatch(new GotOwner() {
        @Override
        public void got(Owner owner) {
          OwnerUpdatePresenter.this.owner = owner;
          getView().setOwner(owner);
        }
      });
    } else {
      Owner owner = new Owner();
      OwnerUpdatePresenter.this.owner = owner;
      getView().setOwner(owner);
    }

  }
  
  @Override
  protected void onHide() {
    super.onHide();
    getView().reset();
  }

  private void init() {
    new GetAllCountriesCommand().dispatch(new GotAllCountries() {
      @Override
      public void got(List<Country> countries) {
        getView().fillCountryList(countries);
        
      }
    });
  }
  
  private void saveOwner(){
    new UpdateOwnerCommand(owner).dispatch(new UpdatedOwner() {
      @Override
      public void got(Owner owner) {
        if (owner != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
          myRequest = myRequest.with(UUID_PARAMETER, owner.getId());
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The owner cannot be save");
        }
      }
    });
  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
}
