package eu.comexis.napoleon.client.core.party;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.MatrimonialRegime;
import eu.comexis.napoleon.shared.model.Party;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public abstract class PartyUpdateView<T extends Party> extends ViewImpl implements
    PartyUpdatePresenter.MyView<T> {

  public interface Binder extends UiBinder<Widget, PartyUpdateView<?>> {
  }

  private static final Binder binder = GWT.create(Binder.class);

  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(PartyUpdateView.class.getName());

  @UiField
  TextBox addresse;
  @UiField
  TextBox number;
  @UiField
  TextBox box;
  @UiField(provided = true)
  ListBox title;
  @UiField
  TextBox bic;
  @UiField
  DateBox birthDayDate;
  @UiField
  SuggestBox city;
  @UiField
  SuggestBox country;
  @UiField
  TextBox email;
  @UiField
  TextBox fax;
  @UiField
  TextBox firstName;
  @UiField
  TextBox iban;
  @UiField
  SuggestBox job;
  @UiField(provided = true)
  ListBox maritalStatus;
  @UiField(provided = true)
  ListBox matrimonialRegime;
  @UiField
  TextBox mobileNumber;
  @UiField
  TextBox name;
  @UiField
  SuggestBox nationality;
  @UiField
  TextBox nationalRegister;
  @UiField
  TextBox phoneNumber;
  @UiField
  TextBox placeOfBirth;
  @UiField
  SuggestBox postalCode;
  @UiField
  HTMLPanel additionnalData;
  
  protected PartyUpdateUiHandlers presenter;

  private final Widget widget;

  public PartyUpdateView() {
    init();
    widget = binder.createAndBindUi(this);
    initNames();
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
    UiHelper.displayValidationMessage(validationMessages, asWidget());
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
  public void fillNationalityList(List<String> nationalities) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) nationality.getSuggestOracle();
    oracle.clear();
    if (nationalities != null) {
      for (String sVal : nationalities) {
        if (sVal != null) {
          oracle.add(sVal);
        }
      }
    }
  }
  
  @Override
  public void fillJobList(List<String> jobs) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) this.job.getSuggestOracle();
    oracle.clear();
    if (jobs != null) {
      for (String sVal : jobs) {
        if (sVal != null) {
          oracle.add(sVal);
        }
      }
    }
  }

  @Override
  public String getSelectedCountry() {
    return country.getValue();
  }

  private void init() {
    title = UiHelper.createListBoxForEnum(Title.class, "Title_", false);
    maritalStatus = UiHelper.createListBoxForEnum(MaritalStatus.class, "MaritalStatus_", false);
    matrimonialRegime =
        UiHelper.createListBoxForEnum(MatrimonialRegime.class, "MatrimonialRegime_", false);
  }

  /**
   * For some widgets (SuggestBox, DateBox), it is not possible to set the name via uibinder
   * 
   */
  protected void initNames() {
    city.getTextBox().setName("city");
    country.getTextBox().setName("country");
    postalCode.getTextBox().setName("postalCode");
    nationality.getTextBox().setName("nationality");
    job.getTextBox().setName("job");
    birthDayDate.getTextBox().setName("birthDayDate");
  }
  
  protected void setAdditionalData(Widget w){
    additionnalData.add(w);
  }

  @UiHandler("btnCancel")
  public void onCancel(ClickEvent e) {
    presenter.onButtonCancelClick();
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

  @UiHandler("name")
  public void onNameChange(ValueChangeEvent<String> event) {
    name.setValue(UiHelper.formatLastName(name.getValue()));
  }
  
  @UiHandler("firstName")
  public void onFirstNameChange(ValueChangeEvent<String> event) {
    firstName.setValue(UiHelper.formatFirstName(firstName.getValue()));
  }
  
  @UiHandler("btnSave")
  public void onSave(ClickEvent e) {
    presenter.onButtonSaveClick();
  }
  
  @UiHandler("maritalStatus")
  public void onMaritalStatusChange(ChangeEvent event) {
    String value = maritalStatus.getValue(maritalStatus.getSelectedIndex());
    MaritalStatus status = MaritalStatus.valueOf(value);
    presenter.onMaritalStatusSelected(status);
  }

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }
  private void emptyFields(){
    name.setText("");
    firstName.setText("");
    bic.setText("");
    iban.setText("");
    email.setText("");
    phoneNumber.setText("");
    mobileNumber.setText("");
    fax.setText("");
    birthDayDate.setValue(null);
    placeOfBirth.setText("");
    country.setValue("");
    addresse.setText("");
    number.setText("");
    postalCode.setText("");
    city.setValue("");
    nationality.setText("");
    job.setText("");
    nationalRegister.setText("");
    box.setText("");
    UiHelper.selectTextItemBoxByValue(maritalStatus, null, MaritalStatus.SINGLE);
    UiHelper.selectTextItemBoxByValue(matrimonialRegime, null, MatrimonialRegime.NONE);
    UiHelper.selectTextItemBoxByValue(title, Title.MR);
  }
  @Override
  public void setData(T party) {
    emptyFields();
    UiHelper.selectTextItemBoxByValue(title, party.getTitle());

    name.setText(UiHelper.formatLastName(party.getLastName()));
    firstName.setText(UiHelper.formatFirstName(party.getFirstName()));
    bic.setText(party.getBic());
    iban.setText(party.getIban());
    email.setText(party.getEmail());
    phoneNumber.setText(party.getPhoneNumber());
    mobileNumber.setText(party.getMobilePhoneNumber());
    fax.setText(party.getFax());
    birthDayDate.setValue(party.getDateOfBirth());

    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    birthDayDate.setFormat(new DateBox.DefaultFormat(dateFormat));

    placeOfBirth.setText(party.getPlaceOfBirth());
    country.setValue(party.getCountry());
    presenter.onCountrySelect(country.getValue());
    addresse.setText(party.getStreet());
    number.setText(party.getNumber());
    box.setText(party.getBox());
    postalCode.setText(party.getPostalCode());
    presenter.onPostalCodeSelect(postalCode.getValue());
    city.setValue(party.getCity());
    nationality.setText(party.getNationality());
    job.setText(party.getJobTitle());
    nationalRegister.setText(party.getNationalRegisterNumber());

    UiHelper.selectTextItemBoxByValue(maritalStatus, party.getMaritalStatus(), MaritalStatus.SINGLE);
    UiHelper.selectTextItemBoxByValue(matrimonialRegime, party.getMatrimonialRegime(), MatrimonialRegime.NONE);

  }

  @Override
  public void setPresenter(PartyUpdateUiHandlers presenter) {
    this.presenter = presenter;
  }

  @Override
  public void updateData(T party) {
    party.setTitle(Title.valueOf(title.getValue(title.getSelectedIndex())));
    party.setFirstName(firstName.getValue());
    party.setLastName(name.getValue());
    party.setBic(bic.getValue());
    party.setIban(iban.getValue());
    party.setEmail(email.getValue());
    party.setPhoneNumber(phoneNumber.getValue());
    party.setMobilePhoneNumber(mobileNumber.getValue());
    party.setFax(fax.getValue());
    party.setDateOfBirth(birthDayDate.getValue());
    party.setPlaceOfBirth(placeOfBirth.getValue());
    party.setStreet(addresse.getValue());
    party.setNumber(number.getValue());
    party.setBox(box.getValue());
    party.setPostalCode(postalCode.getValue());
    party.setCity(city.getValue());
    party.setCountry(country.getValue());
    party.setJobTitle(job.getValue());
    party.setNationality(nationality.getValue());
    party.setNationalRegisterNumber(nationalRegister.getValue());
    party.setMaritalStatus(MaritalStatus.fromStringToEnum(maritalStatus.getValue(maritalStatus
        .getSelectedIndex())));
    party.setMatrimonialRegime(MatrimonialRegime.fromStringToEnum(matrimonialRegime
        .getValue(matrimonialRegime.getSelectedIndex())));

  }
  
  @Override
  public void setMatrimonialRegime(MatrimonialRegime matrimonialRegime) {
    UiHelper.selectTextItemBoxByValue(this.matrimonialRegime, matrimonialRegime); 
  }

}
