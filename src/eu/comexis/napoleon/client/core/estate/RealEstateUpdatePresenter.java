package eu.comexis.napoleon.client.core.estate;

import static eu.comexis.napoleon.client.core.estate.RealEstateDetailsPresenter.UUID_PARAMETER;

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
import eu.comexis.napoleon.client.core.estate.RealEstateUpdateUiHandlers.HasRealEstateUpdateUiHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllCities;
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotAllRealEstate;
import eu.comexis.napoleon.client.rpc.callback.GotRealEstate;
import eu.comexis.napoleon.client.rpc.callback.UpdatedRealEstate;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateUpdatePresenter extends
		Presenter<RealEstateUpdatePresenter.MyView, RealEstateUpdatePresenter.MyProxy> implements RealEstateUpdateUiHandlers{

	public static final String UUID_PARAMETER = "uuid";
	private static final Logger LOG = Logger.getLogger(RealEstateDetailsPresenter.class.getName());

	public interface MyView extends View, HasRealEstateUpdateUiHandler{
		public void setRealEstate(RealEstate o);
		public RealEstate updateRealEstate(RealEstate o);
		public void fillCityList(List<String> cities);
		public String getSelectedCountry();
		public void fillCountryList(List<Country> countries);
		public void showCountryOther(Boolean show);
    public void showCityOther(Boolean show);
    public void displayError(String error);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.updateRealEstate)
	public interface MyProxy extends ProxyPlace<RealEstateUpdatePresenter> {
	}

	private PlaceManager placeManager;
	private String id;
	private RealEstate realEstate;
	private List<String> allCities;
	private List<Country> allCountries;

	@Inject
	public RealEstateUpdatePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}
	@Override
  protected void onBind() {
    super.onBind();
    getView().setRealEstateUpdateUiHandler(this);
  }
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

	@Override
	protected void onReset() {
		super.onReset();

		new GetRealEstateCommand(id).dispatch(new GotRealEstate() {
		  @Override
      public void got(RealEstate realEstate) {
        RealEstateUpdatePresenter.this.realEstate = realEstate;
        getView().setRealEstate(realEstate);
      }
    });
		new GetAllCountriesCommand().dispatch(new GotAllCountries() {
       @Override
       public void got(List<Country> countries) {
         RealEstateUpdatePresenter.this.allCountries = countries;
         getView().fillCountryList(countries);
         GetAllCitiesCommand cmd = new GetAllCitiesCommand();
         cmd.setName(getView().getSelectedCountry());
         cmd.dispatch(new GotAllCities() {
           @Override
           public void got(List<String> cities) {
             RealEstateUpdatePresenter.this.allCities = cities;
             getView().fillCityList(cities);
           }
         });
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
	}
	@Override 
	public void onCountrySelect(String selectedCountry){
	  if (selectedCountry.equals("(...)")){
        getView().showCountryOther(true);
	  }else{
	    // get all the already encoded cities for the given country
	    GetAllCitiesCommand cmd = new GetAllCitiesCommand();
	    cmd.setName(selectedCountry);
	    cmd.dispatch(new GotAllCities() {
	      @Override
	      public void got(List<String> cities) {
	        getView().fillCityList(cities);
	      }
	    });
	    getView().showCountryOther(false);
	  }
	}
	@Override 
  public void onCitySelect(String selectedCity){
	  if (selectedCity.equals("(...)")){
	    getView().showCityOther(true);
    }else{
      getView().showCityOther(false);
    }
  }
	@Override
  public void onButtonCancelClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
    // add the id of the realEstate to load
    myRequest = myRequest.with(UUID_PARAMETER, realEstate.getId());
    placeManager.revealPlace(myRequest);
  }
	@Override
  public void onButtonSaveClick() {
	  //Try to save the realEstate
	  //Get the realEstate to save
	  realEstate = getView().updateRealEstate(realEstate);
	  //Save it
	  new UpdateRealEstateCommand(realEstate).dispatch(new UpdatedRealEstate() {
      @Override
      public void got(RealEstate realEstate) {
        if (realEstate != null){
          PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
          myRequest = myRequest.with(UUID_PARAMETER, realEstate.getId());
          placeManager.revealPlace(myRequest);
        }else{
          getView().displayError("The realEstate cannot be save");
        }
      }
    });
	  //On success display the realEstate detail screen
	  //On failure display the realEstate update screen with the reason why it cannot be saved
    
  }
}