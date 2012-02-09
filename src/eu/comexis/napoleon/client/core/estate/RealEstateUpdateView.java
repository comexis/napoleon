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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.RealEstateState;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.TypeOfRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class RealEstateUpdateView extends ViewImpl implements RealEstateUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateUpdateView> {
  }

  private final Widget widget;
  private RealEstateUpdateUiHandlers presenter;
  private MultiWordSuggestOracle oracleSquare;

  @UiField
  TextBox reference;
  @UiField
  TextBox addressRealEstate;
  @UiField(provided = true)
  ListBox city;
  @UiField
  TextBox cityOther;
  @UiField(provided = true)
  ListBox country;
  @UiField
  TextBox countryOther;
  @UiField
  TextBox condo;
  @UiField
  TextBox association;
  @UiField
  TextBox address;
  @UiField
  TextBox phoneNumber;
  @UiField
  TextBox mobileNumber;
  @UiField
  TextBox email;
  @UiField
  TextBox number;
  @UiField
  TextBox box;
  @UiField(provided = true)
  SuggestBox square;
  @UiField(provided = true)
  ListBox type;
  @UiField(provided = true)
  ListBox state;
  @UiField
  TextBox dimension;
  @UiField
  ListBox ownerName;

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
  public void fillCityList(List<City> cities) {
    city.clear();
    for (City oCity : cities) {
      city.addItem(oCity.getName());
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
  public void fillOwnerList(List<SimpleOwner> owners) {
    ownerName.clear();
    for (SimpleOwner o : owners) {
      ownerName.addItem(o.getName(), o.getId());
    }
    ownerName.addItem("(...)", "(...)");
  }

  @Override
  public void fillSquareList(List<String> squares) {
    oracleSquare.clear();
    for (String sSquare : squares) {
      oracleSquare.add(sSquare);
    }
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
  public void setRealEstate(RealEstate e, SimpleOwner o, Condo cdo) {

    this.reference.setText(e.getReference());
    this.addressRealEstate.setText(e.getStreet());
    this.cityOther.setText(e.getCity());
    this.number.setText(e.getNumber());
    this.box.setText(e.getBox());
    this.square.setText(e.getSquare());
    this.dimension.setText(e.getDimension());
    UiHelper.selectTextItemBoxByValue(this.state, e.getState().name());
    UiHelper.selectTextItemBoxByValue(this.type, e.getType().name());
    UiHelper.selectTextItemBoxByValue(this.ownerName, o.getId());
    if (cdo!=null){
      this.condo.setText(cdo.getName());
      this.association.setText(cdo.getHomeownerAssociation());
      this.address.setText(cdo.getStreet());
      this.email.setText(cdo.getEmail());
      this.mobileNumber.setText(cdo.getMobilePhoneNumber());
      this.phoneNumber.setText(cdo.getPhoneNumber());
    }
    for (int i = 0; i < country.getItemCount(); i++) {
      if (country.getItemText(i).equals(e.getCountry())) {
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
  public RealEstate updateRealEstate(RealEstate e) {
    e.setReference(reference.getValue());
    e.setStreet(addressRealEstate.getValue());
    e.setNumber(number.getValue());
    e.setBox(box.getValue());
    e.setDimension(dimension.getValue());
    e.setSquare(square.getValue());
    e.setType(TypeOfRealEstate.valueOf(type.getValue(type.getSelectedIndex())));
    e.setState(RealEstateState.valueOf(state.getValue(state.getSelectedIndex())));
    e.setCity(city.getValue(city.getSelectedIndex()).equals("(...)") ? cityOther.getValue() : city
        .getValue(city.getSelectedIndex()));
    e.setCountry(country.getItemText(country.getSelectedIndex()).equals("(...)") ? countryOther
        .getValue() : country.getItemText(country.getSelectedIndex()));
    return e;
  }
  @Override
  public String getOwnerId(){
    return ownerName.getValue(ownerName.getSelectedIndex());
  }
  @Override
  public Condo updateCondo(Condo cdo) {
    if (!condo.getValue().isEmpty()){
      if (cdo==null){
        cdo = new Condo();
      }
      cdo.setName(condo.getValue());
      cdo.setHomeownerAssociation(association.getValue());
      cdo.setStreet(address.getValue());
      cdo.setEmail(email.getValue());
      cdo.setMobilePhoneNumber(mobileNumber.getValue());
      cdo.setPhoneNumber(phoneNumber.getValue());
      return cdo;
    }
    return null;
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
    type = UiHelper.createListBoxForEnum(TypeOfRealEstate.class, "TypeOfRealEstate_", false);
    state = UiHelper.createListBoxForEnum(RealEstateState.class, "RealEstateState_", false);
    oracleSquare = new MultiWordSuggestOracle();
    square = new SuggestBox(oracleSquare);
  }

  private void selectCityByName(String name) {
    for (int i = 0; i < city.getItemCount(); i++) {
      if (city.getItemText(i).equals(name)) {
        city.setSelectedIndex(i);
        presenter.onCitySelect(name);
        break;
      }
    }
  }
}