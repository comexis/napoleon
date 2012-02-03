package eu.comexis.napoleon.client.core.owner;

import static eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter.UUID_PARAMETER;

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
import eu.comexis.napoleon.client.rpc.callback.GotAllCities;
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotAllOwner;
import eu.comexis.napoleon.client.rpc.callback.GotOwner;
import eu.comexis.napoleon.client.rpc.callback.UpdatedOwner;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerCommand;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerUpdatePresenter extends
		Presenter<OwnerUpdatePresenter.MyView, OwnerUpdatePresenter.MyProxy> implements OwnerUpdateUiHandlers{

	public static final String UUID_PARAMETER = "uuid";
	private static final Logger LOG = Logger.getLogger(OwnerDetailsPresenter.class.getName());
	
	public interface MyView extends View, HasOwnerUpdateUiHandler{
		public void setOwner(Owner o);
		public Owner updateOwner(Owner o);
		public void fillCityList(List<String> cities);
		public String getSelectedCountry();
		public void fillCountryList(List<Country> countries);
		public void showCountryOther(Boolean show);
    public void showCityOther(Boolean show);
    public void displayError(String error);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.updateOwner)
	public interface MyProxy extends ProxyPlace<OwnerUpdatePresenter> {
	}
	
	private PlaceManager placeManager;
	private String id;
	private Owner owner;
	private List<String> allCities;
	private List<Country> allCountries;

	@Inject
	public OwnerUpdatePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}
	@Override
  protected void onBind() {
    super.onBind();
    getView().setOwnerUpdateUiHandler(this);
  }
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		
		new GetOwnerCommand(id).dispatch(new GotOwner() {
		  @Override
      public void got(Owner owner) {
        OwnerUpdatePresenter.this.owner = owner;
        getView().setOwner(owner);
      }
    });
		new GetAllCountriesCommand().dispatch(new GotAllCountries() {
       @Override
       public void got(List<Country> countries) {
         OwnerUpdatePresenter.this.allCountries = countries;
         getView().fillCountryList(countries);
         GetAllCitiesCommand cmd = new GetAllCitiesCommand();
         cmd.setName(getView().getSelectedCountry());
         cmd.dispatch(new GotAllCities() {
           @Override
           public void got(List<String> cities) {
             OwnerUpdatePresenter.this.allCities = cities;
             getView().fillCityList(cities);
           }
         });
       }
     });
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
    PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, owner.getId());
    placeManager.revealPlace(myRequest);
  }
	@Override
  public void onButtonSaveClick() {
	  //Try to save the owner
	  //Get the owner to save
	  owner = getView().updateOwner(owner);
	  //Save it
	  new UpdateOwnerCommand(owner).dispatch(new UpdatedOwner() {
      @Override
      public void got(Owner owner) {
        if (owner != null){
          PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
          myRequest = myRequest.with(UUID_PARAMETER, owner.getId());
          placeManager.revealPlace(myRequest);
        }else{
          getView().displayError("The owner cannot be save");
        }
      }
    });
	  //On success display the owner detail screen
	  //On failure display the owner update screen with the reason why it cannot be saved
    
  }
}
