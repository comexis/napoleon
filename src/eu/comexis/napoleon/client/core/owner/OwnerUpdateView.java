package eu.comexis.napoleon.client.core.owner;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.MatrimonialRegime;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.Title;

public class OwnerUpdateView extends ViewImpl implements OwnerUpdatePresenter.MyView {
  public interface Binder extends UiBinder<Widget, OwnerUpdateView> {
  }
  private static final Logger LOG = Logger.getLogger(OwnerUpdateView.class.getName());

  private final Widget widget;

  private OwnerUpdateUiHandlers presenter;

  @UiField
  TextBox name;
  @UiField
  TextBox firstName;
  @UiField
  TextBox email;
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
  @UiField(provided = true)
  ListBox maritalStatus;
  @UiField(provided = true)
  ListBox matrimonialRegime;
  @UiField
  DateBox birthDayDateBox;
  @UiField(provided = true)
  ListBox title;
  @UiField
  TextBox fee;
  @UiField(provided = true)
  ListBox unit;
  @UiField
  TextBox iban;
  @UiField
  TextBox bic;
  @UiField
  TextBox fax;
  @UiField
  TextBox placeOfBirth;
  @UiField(provided = true)
  SuggestBox nationality;
  @UiField(provided = true)
  SuggestBox job;
  @UiField
  TextBox nationalRegister;

  @Inject
  public OwnerUpdateView(final Binder binder) {
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
  public void setOwner(Owner o) {
    countryOther.setText("");
    cityOther.setText("");
    if (LogConfiguration.loggingIsEnabled()) {
      LOG.info("set owner " + o.getId());
    }
    if (o.getTitle() != null) {
      for (int i = 0; i < title.getItemCount(); i++) {
        if (title.getValue(i).equals(o.getTitle().name())) {
          title.setSelectedIndex(i);
        }
      }
    }
    name.setText(o.getLastName());
    firstName.setText(o.getFirstName());
    fee.setText(o.getFee().toString());
    if (o.getUnit() != null) {
      if (o.getUnit().equals("%")) {
        unit.setSelectedIndex(0);
      } else {
        unit.setSelectedIndex(1);
      }
    }
    bic.setText(o.getBic());
    iban.setText(o.getIban());
    email.setText(o.getEmail());
    phoneNumber.setText(o.getPhoneNumber());
    mobileNumber.setText(o.getMobilePhoneNumber());
    fax.setText(o.getFax());
    birthDayDateBox.setValue(o.getDateOfBirth());
    DateTimeFormat dateFormat = DateTimeFormat.getShortDateFormat();
    birthDayDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
    placeOfBirth.setText(o.getPlaceOfBirth());
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
    nationality.setText(o.getNationality());
    job.setText(o.getJobTitle());
    nationalRegister.setText(o.getNationalRegisterNumber());
    if (o.getMaritalStatus()!=null){
      for (int i = 0; i < maritalStatus.getItemCount(); i++) {
        if (maritalStatus.getValue(i).equals(o.getMaritalStatus().name())) {
          maritalStatus.setSelectedIndex(i);
        }
      }
    }
    if (o.getMatrimonialRegime()!=null){
      for (int i = 0; i < matrimonialRegime.getItemCount(); i++) {
        if (matrimonialRegime.getValue(i).equals(o.getMatrimonialRegime().name())) {
          matrimonialRegime.setSelectedIndex(i);
        }
      }
    }
    
    $("#countryOther").hide();
    $("#cityOther").hide();
    
    // TODO Auto-generated method stub

  }
  @Override
  public void setOwnerUpdateUiHandler(OwnerUpdateUiHandlers handler) {
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
  public Owner updateOwner(Owner o) {
    o.setTitle(Title.valueOf(title.getValue(title.getSelectedIndex())));
    o.setFirstName(firstName.getValue());
    o.setLastName(name.getValue());
    o.setFee(fee.getValue());
    o.setUnit((unit.getSelectedIndex() == 0) ? "%" : "EUR");
    o.setBic(bic.getValue());
    o.setIban(iban.getValue());
    o.setEmail(email.getValue());
    o.setPhoneNumber(phoneNumber.getValue());
    o.setMobilePhoneNumber(mobileNumber.getValue());
    o.setFax(fax.getValue());
    o.setDateOfBirth(birthDayDateBox.getValue());
    o.setPlaceOfBirth(placeOfBirth.getValue());
    o.setStreet(addresse.getValue());
    o.setCity(city.getValue(city.getSelectedIndex()).equals("(...)") ? cityOther.getValue() : city
        .getValue(city.getSelectedIndex()));
    o.setCountry(country.getItemText(country.getSelectedIndex()).equals("(...)") ? countryOther
        .getValue() : country.getItemText(country.getSelectedIndex()));
    o.setJobTitle(job.getValue());
    o.setNationality(nationality.getValue());
    o.setNationalRegisterNumber(nationalRegister.getValue());
    o.setMaritalStatus(MaritalStatus.fromStringToEnum(maritalStatus.getValue(maritalStatus
        .getSelectedIndex())));
    o.setMatrimonialRegime(MatrimonialRegime.fromStringToEnum(matrimonialRegime
        .getValue(matrimonialRegime.getSelectedIndex())));
    return o;
  }

  private void init() {

    title = UiHelper.createListBoxForEnum(Title.class, "Title_", false);
    maritalStatus = UiHelper.createListBoxForEnum(MaritalStatus.class, "MaritalStatus_", false);
    matrimonialRegime =
        UiHelper.createListBoxForEnum(MatrimonialRegime.class, "MatrimonialRegime_", false);
    MultiWordSuggestOracle oracleNationality = new MultiWordSuggestOracle();
    oracleNationality.add("Belge");
    oracleNationality.add("Français");
    nationality = new SuggestBox(oracleNationality);
    MultiWordSuggestOracle oracleJob = new MultiWordSuggestOracle();
    oracleJob.add("Enseignant");
    oracleJob.add("Ingénieur");
    oracleJob.add("Informaticien");
    job = new SuggestBox(oracleJob);
    unit = new ListBox();
    unit.addItem("% LOYER");
    unit.addItem("Somme forfaitaire (EUR)");

    city = new ListBox();
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

  private void selectCityByName(String name){
    for (int i = 0; i < city.getItemCount(); i++) {
      if (city.getItemText(i).equals(name)) {
        city.setSelectedIndex(i);
        break;
      }
    }
  }
}