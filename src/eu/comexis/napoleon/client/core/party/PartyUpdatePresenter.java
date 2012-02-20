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
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.MatrimonialRegime;
import eu.comexis.napoleon.shared.model.Party;
import eu.comexis.napoleon.shared.validation.PartyValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public abstract class PartyUpdatePresenter<T extends Party, V extends PartyUpdatePresenter.MyView<T>, P extends Proxy<?>> extends
    Presenter<V, P> implements
    PartyUpdateUiHandlers {

  
  public interface MyView<T extends Party> extends View, PartyUpdateUiHandlers.HasPresenter {
    public void displayError(String error);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);
    
    public void fillCityList(List<String> cities);

    public void fillCountryList(List<Country> countries);

    public void fillPostalCodeList(List<String> postCdes);

    public String getSelectedCountry();

    public void reset();

    public void setData(T o);

    public void updateData(T o);
    
    public void setMatrimonialRegime(MatrimonialRegime matrimonialRegime);
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

  @Override
  public void onButtonCancelClick() {
    if (party == null || party.getId() == null || party.getId().length() == 0){
      goToList();
    }else {
      goToDetails();
    }
   
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
    
    if (id == null || id.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }
  }

  protected abstract  T createNewDataModel();
  
  protected abstract PartyValidator<T> createValidator();

  protected T getDataObjectModel(){
    return party;
  }

  protected abstract String getDetailsNameTokens();

  protected abstract String getListNameTokens();
  
  @Override
  protected void onBind() {
    super.onBind();
    getView().setPresenter(this);
    init();
  }

  @Override
  protected void onHide() {
    super.onHide();
    getView().reset();
  }
  
  @Override
  protected void onReset() {
    super.onReset();
    if (id != null && !"new".equals(id)) { // call the server to get the requested owner
      requestData(id);
    } else {
      party = createNewDataModel();
      getView().setData(party);
    }

  }

  protected abstract void requestData(String id);
  
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
  
  protected abstract void save();
  
  protected void setDataObjectModel(T t){
    party = t;
  }
  
  private void goToDetails() {
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, party.getId());
    placeManager.revealPlace(myRequest);
  }
  private void goToList() {
    PlaceRequest myRequest = new PlaceRequest(getListNameTokens());
    placeManager.revealPlace(myRequest);
    
  }

  private void init() {
    new GetAllCountriesCommand().dispatch(new GotAllCountries() {
      @Override
      public void got(List<Country> countries) {
        getView().fillCountryList(countries);
        
      }
    });
  }
  
  @Override
  public void onMaritalStatusSelected(MaritalStatus maritalStatus) {
    if (MaritalStatus.SINGLE == maritalStatus){
      getView().setMatrimonialRegime(MatrimonialRegime.NONE);
    }
    
  }
}
