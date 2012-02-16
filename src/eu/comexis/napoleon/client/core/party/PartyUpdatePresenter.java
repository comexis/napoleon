package eu.comexis.napoleon.client.core.party;

import java.util.ArrayList;
import java.util.List;
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
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotCountry;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetCountryCommand;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Party;
import eu.comexis.napoleon.shared.validation.PartyValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public abstract class PartyUpdatePresenter<T extends Party, V extends PartyUpdatePresenter.MyView<T>, P extends Proxy<?>> extends
    Presenter<V, P> implements
    PartyUpdateUiHandlers {

  
  public interface MyView<T extends Party> extends View, PartyUpdateUiHandlers.HasPresenter {
    public void displayError(String error);

    public void fillCityList(List<String> cities);
    
    public void fillPostalCodeList(List<String> postCdes);

    public void fillCountryList(List<Country> countries);

    public String getSelectedCountry();

    public void setData(T o);

    public void updateData(T o);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void reset();
  }

  public static final String UUID_PARAMETER = "uuid";

  private static final Logger LOG = Logger.getLogger(PartyUpdatePresenter.class.getName());

  protected PlaceManager placeManager;
  private String id;
  private T party;
  private Country selectedCountry;
  private PartyValidator<T> validator;
 

  @Inject
  public PartyUpdatePresenter(final EventBus eventBus, final V view, final P proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = createValidator();

  }

  protected abstract PartyValidator<T> createValidator();

  @Override
  public void onButtonCancelClick() {
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, party.getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonSaveClick() {
    getView().updateData(party);
    
    List<ValidationMessage> validationMessages = validator.validate(party);
    
    if (validationMessages.isEmpty()){
      save();
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
        PartyUpdatePresenter.this.selectedCountry = cnty;
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
    if (selectedCountry!=null){
      for(City c: selectedCountry.getListCitiesByPostalCode(selectedPostalCode)){
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
    getView().setPresenter(this);
    init();
  }

  @Override
  protected void onReset() {
    super.onReset();
    if (id != "new") { // call the server to get the requested owner
      requestData(id);
    } else {
      party = createNewDataModel();
      getView().setData(party);
    }

  }
  
  protected abstract  T createNewDataModel();

  protected abstract void requestData(String id);
  
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
  
  protected T getDataObjectModel(){
    return party;
  }
  
  protected void setDataObjectModel(T t){
    party = t;
  }
  
  protected abstract void save();
  
  protected abstract String getDetailsNameTokens();

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
}
