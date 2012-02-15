package eu.comexis.napoleon.client.core.tenant;

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
import eu.comexis.napoleon.client.rpc.callback.GotAllCountries;
import eu.comexis.napoleon.client.rpc.callback.GotCountry;
import eu.comexis.napoleon.client.rpc.callback.GotTenant;
import eu.comexis.napoleon.client.rpc.callback.UpdatedTenant;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetCountryCommand;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.validation.TenantValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class TenantUpdatePresenter extends
    Presenter<TenantUpdatePresenter.MyView, TenantUpdatePresenter.MyProxy> implements
    TenantUpdateUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.updateTenant)
  public interface MyProxy extends ProxyPlace<TenantUpdatePresenter> {
  }
  public interface MyView extends View, HasTenantUpdateUiHandler {
    public void displayError(String error);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void fillCityList(List<String> cities);

    public void fillCountryList(List<Country> countries);

    public void fillPostalCodeList(List<String> postCdes);

    public String getSelectedCountry();

    public void setTenant(Tenant o);

    public Tenant updateTenant(Tenant o);
  }

  public static final String UUID_PARAMETER = "uuid";

  private static final Logger LOG = Logger.getLogger(TenantDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private Tenant tenant;
  private Country country;
  private TenantValidator validator;

  @Inject
  public TenantUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = new TenantValidator();
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
    getView().updateTenant(tenant);

    List<ValidationMessage> validationMessages = validator.validate(tenant);

    if (validationMessages.isEmpty()) {
      saveTenant();
    } else {
      getView().displayValidationMessage(validationMessages);
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
        TenantUpdatePresenter.this.country = cnty;
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
    if (TenantUpdatePresenter.this.country != null) {
      for (City c : TenantUpdatePresenter.this.country
          .getListCitiesByPostalCode(selectedPostalCode)) {
        lstCities.add(c.getName());
      }
    }
    getView().fillCityList(lstCities);

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
    getView().setTenantUpdateUiHandler(this);
    init();
  }

  @Override
  protected void onReset() {
    super.onReset();
    if (id != "new") { // call the server to get the requested tenant
      new GetTenantCommand(id).dispatch(new GotTenant() {
        @Override
        public void got(Tenant tenant) {
          TenantUpdatePresenter.this.tenant = tenant;
          getView().setTenant(tenant);
        }
      });
    } else {
      Tenant tenant = new Tenant();
      TenantUpdatePresenter.this.tenant = tenant;
      getView().setTenant(tenant);
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
  }

  private void saveTenant() {
    new UpdateTenantCommand(tenant).dispatch(new UpdatedTenant() {
      @Override
      public void got(Tenant tenant) {
        if (tenant != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
          myRequest = myRequest.with(UUID_PARAMETER, tenant.getId());
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The tenant cannot be save");
        }
      }
    });
  }
}
