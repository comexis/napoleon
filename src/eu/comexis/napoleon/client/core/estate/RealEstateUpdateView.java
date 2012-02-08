package eu.comexis.napoleon.client.core.estate;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.RealEstate;

public class RealEstateUpdateView extends ViewImpl implements RealEstateUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateUpdateView> {
  }

  private final Widget widget;
  private RealEstateUpdateUiHandlers presenter;

  @UiField
  TextBox reference;
  @UiField
  TextBox phoneNumber;
  @UiField
  TextBox mobileNumber;
  @UiField
  TextBox addresse;
  @UiField(provided = true)
  ListBox city;
  @UiField
  TextBox cityOther;
  @UiField(provided = true)
  ListBox country;
  @UiField
  TextBox countryOther;

  @Inject
  public RealEstateUpdateView(final Binder binder) {
    init();
    widget = binder.createAndBindUi(this);
  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @Override
  public void displayError(String error) {
    Window.alert(error);
  }

  @Override
  public void fillCityList(List<String> cities) {
    city.clear();
    for (String sCity : cities) {
      city.addItem(sCity);
    }
    city.addItem("(...)");
    selectCityByName(cityOther.getText());
  }

  @Override
  public void fillCountryList(List<Country> countries) {
    country.clear();
    for (Country cnty : countries) {
      country.addItem(cnty.getName(), cnty.getId());
    }
    country.addItem("(...)", "(...)");
  }

  @Override
  public String getSelectedCountry() {
    Integer index = country.getSelectedIndex();
    String countryToSelect = country.getValue(index);
    return countryToSelect;
  }

  @UiHandler("btnCancel")
  public void onCancel(ClickEvent e) {
    presenter.onButtonCancelClick();
  }

  @UiHandler("btnSave")
  public void onSave(ClickEvent e) {
    presenter.onButtonSaveClick();
  }

  @Override
  public void setRealEstate(RealEstate o) {

    reference.setText(o.getReference());
    addresse.setText(o.getStreet());
    cityOther.setText(o.getCity());
    for (int i = 0; i < country.getItemCount(); i++) {
      if (country.getItemText(i).equals(o.getCountry())) {
        country.setSelectedIndex(i);
        // load the corresponding cities
        presenter.onCountrySelect(country.getValue(i));
        break;
      }
    }
    $("#countryOther").hide();
    $("#cityOther").hide();

    // TODO Auto-generated method stub

  }

  @Override
  public void setRealEstateUpdateUiHandler(RealEstateUpdateUiHandlers handler) {
    this.presenter = handler;
  }

  @Override
  public void showCityOther(Boolean show) {
    if (show.equals(true)) {
      $("#cityOther").show();
    } else {
      $("#cityOther").hide();
    }
  }

  @Override
  public void showCountryOther(Boolean show) {
    if (show.equals(true)) {
      $("#countryOther").show();
    } else {
      $("#countryOther").hide();
    }
  }

  @Override
  public RealEstate updateRealEstate(RealEstate o) {
    o.setReference(reference.getValue());
    o.setStreet(addresse.getValue());
    o.setCity(city.getValue(city.getSelectedIndex()).equals("(...)") ? cityOther.getValue() : city
        .getValue(city.getSelectedIndex()));
    o.setCountry(country.getItemText(country.getSelectedIndex()).equals("(...)") ? countryOther
        .getValue() : country.getItemText(country.getSelectedIndex()));
    return o;
  }

  private void init() {
    city = new ListBox(false);
    city.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        int selectedIndex = city.getSelectedIndex();
        if (selectedIndex > -1) {
          presenter.onCitySelect(city.getValue(selectedIndex));
        }
      }
    });
    country = new ListBox();
    country.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        int selectedIndex = country.getSelectedIndex();
        if (selectedIndex > -1) {
          presenter.onCountrySelect(country.getValue(selectedIndex));
        }
      }
    });
  }

  private void selectCityByName(String name) {
    for (int i = 0; i < city.getItemCount(); i++) {
      if (city.getItemText(i).equals(name)) {
        city.setSelectedIndex(i);
        break;
      }
    }
  }
}