package eu.comexis.napoleon.client.core.estate;

import static com.google.gwt.query.client.GQuery.$;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.RealEstateState;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.TypeOfRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class RealEstateUpdateView extends ViewImpl implements RealEstateUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateUpdateView> {
  }

  private final Widget widget;
  private RealEstateUpdateUiHandlers presenter;

  @UiField
  TextBox reference;
  @UiField
  TextBox addressRealEstate;
  @UiField
  SuggestBox city;
  @UiField
  SuggestBox postalCode;
  @UiField
  SuggestBox country;
  @UiField
  SuggestBox condo;
  @UiField
  SuggestBox association;
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
  @UiField
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
  public void displayValidationMessage(List<ValidationMessage> validationMessages) {
    // TODO Display the messages in a PopupPanel
    StringBuilder msgBuilder = new StringBuilder("Veuillez corriger les erreurs suivantes : \n\n");

    for (ValidationMessage msg : validationMessages) {
      msgBuilder.append(msg.getMessage()).append("\n");
    }

    Window.alert(msgBuilder.toString());

  }

  @Override
  public void fillCityList(List<String> cities) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) city.getSuggestOracle();
    oracle.clear();
    if (cities != null) {
      for (String sCity : cities) {
        oracle.add(sCity);
      }
    }
  }
  
  @Override
  public void fillCondoList(List<String> condoNames) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) condo.getSuggestOracle();
    oracle.clear();
    if (condoNames != null) {
      for (String sCdo : condoNames) {
        oracle.add(sCdo);
      }
    }
  }
  
  @Override
  public void fillAssocList(List<String> assocNames) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) this.association.getSuggestOracle();
    oracle.clear();
    if (assocNames != null) {
      for (String sCdo : assocNames) {
        oracle.add(sCdo);
      }
    }
  }

  @Override
  public void fillCountryList(List<Country> countries) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) country.getSuggestOracle();
    oracle.clear();
    if (countries != null) {
      for (Country cnty : countries) {
        oracle.add(cnty.getName());
      }
    }
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
  public void fillPostalCodeList(List<String> postCdes) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) postalCode.getSuggestOracle();
    oracle.clear();
    if (postCdes != null) {
      for (String sPC : postCdes) {
        if (sPC != null) {
          oracle.add(sPC);
        }
      }
    }
  }

  @Override
  public void fillSquareList(List<String> squares) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) square.getSuggestOracle();
    oracle.clear();
    if (squares != null) {
      for (String sSquare : squares) {
        oracle.add(sSquare);
      }
    }
  }

  public SimpleOwner getOwner() {
    SimpleOwner o = new SimpleOwner();
    o.setId(ownerName.getValue(ownerName.getSelectedIndex()));
    return o;
  }

  @Override
  public String getSelectedCountry() {
    return country.getValue();
  }

  @UiHandler("btnCancel")
  public void onCancel(ClickEvent e) {
    presenter.onButtonCancelClick();
  }

  @UiHandler("city")
  public void onCityChange(ValueChangeEvent<String> event) {
    square.setValue("");
  }
  
  @UiHandler("association")
  public void onAssocChange(SelectionEvent<SuggestOracle.Suggestion> event) {
    presenter.onAssocSelect(event.getSelectedItem().getReplacementString());
  }
  
  @UiHandler("condo")
  public void onCondoChange(SelectionEvent<SuggestOracle.Suggestion> event) {
    presenter.onCondoSelect(event.getSelectedItem().getReplacementString());
  }

  @UiHandler("city")
  public void onCitySelect(SelectionEvent<SuggestOracle.Suggestion> event) {
    presenter.onCitySelect(event.getSelectedItem().getReplacementString());
    square.setValue("");
  }

  @UiHandler("country")
  public void onCountryChange(ValueChangeEvent<String> event) {
    city.setValue("");
    postalCode.setValue("");
  }

  @UiHandler("country")
  public void onCountrySelect(SelectionEvent<SuggestOracle.Suggestion> event) {
    presenter.onCountrySelect(event.getSelectedItem().getReplacementString());
    city.setValue("");
    postalCode.setValue("");
  }

  @UiHandler("postalCode")
  public void onPostalCodeChange(ValueChangeEvent<String> event) {
    city.setValue("");
  }

  @UiHandler("postalCode")
  public void onPostalCodeSelect(SelectionEvent<SuggestOracle.Suggestion> event) {
    presenter.onPostalCodeSelect(event.getSelectedItem().getReplacementString());
    city.setValue("");
  }

  @UiHandler("btnSave")
  public void onSave(ClickEvent e) {
    presenter.onButtonSaveClick();
  }

  @Override
  public void setRealEstate(RealEstate e) {
    // cleanup
    this.reference.setText("");
    this.addressRealEstate.setText("");
    this.number.setText("");
    this.box.setText("");
    this.square.setText("");
    this.dimension.setText("");
    this.condo.setText("");
    this.association.setText("");
    this.address.setText("");
    this.city.setText("");
    this.postalCode.setText("");
    this.country.setText("");
    this.email.setText("");
    this.mobileNumber.setText("");
    this.phoneNumber.setText("");
    UiHelper.selectTextItemBoxByValue(this.ownerName, "(...)");
    if (e != null) {
      SimpleOwner o = e.getOwner();
      this.reference.setText(e.getReference());
      this.number.setText(e.getNumber());
      this.box.setText(e.getBox());
      this.dimension.setText(e.getDimension());
      UiHelper.selectTextItemBoxByValue(this.state, (e.getState() != null ? e.getState().name() : ""));
      UiHelper.selectTextItemBoxByValue(this.type, (e.getType() != null ? e.getType().name() : ""));
      UiHelper.selectTextItemBoxByValue(this.ownerName, o.getId());
      if (!e.getCondominium().isEmpty()) {
        this.condo.setText(e.getCondominium());
        this.association.setText(e.getHomeownerAssociation());
        this.address.setText(e.getAssocAdresss());
        this.email.setText(e.getAssocEmail());
        this.mobileNumber.setText(e.getAssocMobilePhoneNumber());
        this.phoneNumber.setText(e.getAssocPhoneNumber());
      }
      this.country.setValue(e.getCountry());
      this.presenter.onCountrySelect(country.getValue());
      this.addressRealEstate.setText(e.getStreet());
      this.postalCode.setText(e.getPostalCode());
      this.presenter.onPostalCodeSelect(postalCode.getValue());
      this.city.setValue(o.getCity());
      this.presenter.onCitySelect(city.getValue());
      this.square.setText(e.getSquare());
    }

  }

  @Override
  public void setRealEstateUpdateUiHandler(RealEstateUpdateUiHandlers handler) {
    this.presenter = handler;
  }

  @Override
  public RealEstate updateRealEstate(RealEstate e) {
    e.setReference(reference.getValue());
    e.setNumber(number.getValue());
    e.setBox(box.getValue());
    e.setDimension(dimension.getValue());
    e.setType(TypeOfRealEstate.valueOf(type.getValue(type.getSelectedIndex())));
    e.setState(RealEstateState.valueOf(state.getValue(state.getSelectedIndex())));
    e.setStreet(addressRealEstate.getValue());
    e.setSquare(square.getValue());
    e.setPostalCode(postalCode.getValue());
    e.setCity(city.getValue());
    e.setCountry(country.getValue());
    // Condominum
    e.setCondominium(condo.getValue());
    //HomeownerAssociation info
    e.setHomeownerAssociation(association.getValue());
    e.setAssocAdresss(address.getValue());
    e.setAssocEmail(email.getValue());
    e.setAssocMobilePhoneNumber(mobileNumber.getValue());
    e.setAssocPhoneNumber(phoneNumber.getValue());
    e.setOwner(getOwner());
    e.setOwnershipDate(new Date());
    return e;
  }

  private void init() {
    type = UiHelper.createListBoxForEnum(TypeOfRealEstate.class, "TypeOfRealEstate_", false);
    state = UiHelper.createListBoxForEnum(RealEstateState.class, "RealEstateState_", false);
  }

  @Override
  public void fillCondo(Condo cdo) {
    this.association.setText(cdo.getHomeownerAssociation());
    this.country.setValue(cdo.getCountry());
    this.presenter.onCountrySelect(country.getValue());
    this.addressRealEstate.setText(cdo.getStreet());
    this.number.setText(cdo.getNumber());
    this.postalCode.setText(cdo.getPostalCode());
    this.presenter.onPostalCodeSelect(postalCode.getValue());
    this.city.setValue(cdo.getCity());
    this.presenter.onCitySelect(city.getValue());
    this.square.setText(cdo.getSquare());
    presenter.onAssocSelect(cdo.getHomeownerAssociation());
  }

  @Override
  public void fillAssoc(Association assoc) {
    this.address.setText(assoc.getStreet());
    this.email.setText(assoc.getEmail());
    this.mobileNumber.setText(assoc.getMobilePhoneNumber());
    this.phoneNumber.setText(assoc.getPhoneNumber());
  }
}