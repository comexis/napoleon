package eu.comexis.napoleon.client.core.tenant;

import static eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter.UUID_PARAMETER;

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
import eu.comexis.napoleon.client.core.tenant.TenantUpdateUiHandlers.HasTenantUpdateUiHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllCities;
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotAllTenant;
import eu.comexis.napoleon.client.rpc.callback.GotTenant;
import eu.comexis.napoleon.client.rpc.callback.UpdatedTenant;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public class TenantUpdatePresenter extends
		Presenter<TenantUpdatePresenter.MyView, TenantUpdatePresenter.MyProxy> implements TenantUpdateUiHandlers{

	public static final String UUID_PARAMETER = "uuid";
	private static final Logger LOG = Logger.getLogger(TenantDetailsPresenter.class.getName());
	
	public interface MyView extends View, HasTenantUpdateUiHandler{
		public void setTenant(Tenant o);
		public Tenant updateTenant(Tenant o);
		public void fillCityList(List<String> cities);
		public String getSelectedCountry();
		public void fillCountryList(List<Country> countries);
		public void showCountryOther(Boolean show);
    public void showCityOther(Boolean show);
    public void displayError(String error);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.updateTenant)
	public interface MyProxy extends ProxyPlace<TenantUpdatePresenter> {
	}
	
	private PlaceManager placeManager;
	private String id;
	private Tenant tenant;
	private List<String> allCities;
	private List<Country> allCountries;

	@Inject
	public TenantUpdatePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}
	@Override
  protected void onBind() {
    super.onBind();
    getView().setTenantUpdateUiHandler(this);
  }
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
	}

	@Override
	protected void onReset() {
		super.onReset();
		
		new GetTenantCommand(id).dispatch(new GotTenant() {
		  @Override
      public void got(Tenant tenant) {
        TenantUpdatePresenter.this.tenant = tenant;
        getView().setTenant(tenant);
      }
    });
		new GetAllCountriesCommand().dispatch(new GotAllCountries() {
       @Override
       public void got(List<Country> countries) {
         TenantUpdatePresenter.this.allCountries = countries;
         getView().fillCountryList(countries);
         GetAllCitiesCommand cmd = new GetAllCitiesCommand();
         cmd.setName(getView().getSelectedCountry());
         cmd.dispatch(new GotAllCities() {
           @Override
           public void got(List<String> cities) {
             TenantUpdatePresenter.this.allCities = cities;
             getView().fillCityList(cities);
           }
         });
       }
     });
	}
	
	/**
	 * Retrieve the id of the tenant to show it
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
    PlaceRequest myRequest = new PlaceRequest(NameTokens.tenant);
    // add the id of the tenant to load
    myRequest = myRequest.with(UUID_PARAMETER, tenant.getId());
    placeManager.revealPlace(myRequest);
  }
	@Override
  public void onButtonSaveClick() {
	  //Try to save the tenant
	  //Get the tenant to save
	  tenant = getView().updateTenant(tenant);
	  //Save it
	  new UpdateTenantCommand(tenant).dispatch(new UpdatedTenant() {
      @Override
      public void got(Tenant tenant) {
        if (tenant != null){
          PlaceRequest myRequest = new PlaceRequest(NameTokens.tenant);
          myRequest = myRequest.with(UUID_PARAMETER, tenant.getId());
          placeManager.revealPlace(myRequest);
        }else{
          getView().displayError("The tenant cannot be save");
        }
      }
    });
	  //On success display the tenant detail screen
	  //On failure display the tenant update screen with the reason why it cannot be saved
    
  }
}
