package eu.comexis.napoleon.client.core.tenant;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import static com.google.gwt.query.client.GQuery.$;

import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.MatrimonialRegime;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.utils.MaritalStatusTranslator;
import eu.comexis.napoleon.shared.model.utils.MatrimonialRegimeTranslator;
import eu.comexis.napoleon.shared.model.utils.TitleTranslator;

public class TenantUpdateView extends ViewImpl implements
  TenantUpdatePresenter.MyView {

	private final Widget widget;
	private TenantUpdateUiHandlers presenter;
	public interface Binder extends UiBinder<Widget, TenantUpdateView> {
	}
	
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
	@UiField(provided = true)
	Button btnSave;
	@UiField(provided = true)
  Button btnCancel;

	@Inject
	public TenantUpdateView(final Binder binder) {
	  init();
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
  @Override
  public void fillCityList(List<String> cities) {
    String cityToSelect = city.getItemText(city.getSelectedIndex());
    city.clear();
    Integer index = 0;
    for(int i=0; i < cities.size();i++){
      String sCity = cities.get(i);
      city.addItem(cities.get(i));
      if (cityToSelect!=null && sCity.equals(cityToSelect)){
        index = i;
      }
    }
    city.addItem("(...)");
    city.setItemSelected(index, true);
  }
  @Override
  public String getSelectedCountry(){
    Integer index = country.getSelectedIndex();
    String countryToSelect = country.getValue(index);
    return countryToSelect;
  }
  @Override
  public void fillCountryList(List<Country> countries) {
    String countryToSelect = country.getValue(country.getSelectedIndex());
    country.clear();
    Integer index = 0;
    for(int i=0; i < countries.size();i++){
      String sCountry = countries.get(i).getName();
      country.addItem(countries.get(i).getName(),countries.get(i).getId());
      if (countryToSelect!=null && sCountry.equals(countryToSelect)){
        index = i;
      }
    }
    country.addItem("(...)","(...)");
    country.setItemSelected(index, true);
  }
  @Override
  public Tenant updateTenant(Tenant o) {
    o.setTitle(Title.fromStringToEnum(title.getValue(title.getSelectedIndex())));
    o.setFirstName(firstName.getValue());
    o.setLastName(name.getValue());
    o.setEmail(email.getValue());
    o.setPhoneNumber(phoneNumber.getValue());
    o.setMobilePhoneNumber(mobileNumber.getValue());
    o.setDateOfBirth(birthDayDateBox.getValue());
    o.setStreet(addresse.getValue());
    o.setCity(city.getValue(city.getSelectedIndex()).equals("(...)") ? cityOther.getValue():city.getValue(city.getSelectedIndex()));
    o.setCountry(country.getItemText(country.getSelectedIndex()).equals("(...)") ? countryOther.getValue():country.getItemText(country.getSelectedIndex()));
    o.setMaritalStatus(MaritalStatus.fromStringToEnum(maritalStatus.getValue(maritalStatus.getSelectedIndex())));
    o.setMatrimonialRegime(MatrimonialRegime.fromStringToEnum(matrimonialRegime.getValue(matrimonialRegime.getSelectedIndex())));
    return o;
  }
	@Override
	public void setTenant(Tenant o) {
	  for(int i=0; i < title.getItemCount();i++){
      if (title.getValue(i).equals(o.getTitle().name())){
        title.setSelectedIndex(i);
      }
    }
  	name.setText(o.getLastName());
  	firstName.setText(o.getFirstName());
  	email.setText(o.getEmail());
  	phoneNumber.setText(o.getPhoneNumber());
  	mobileNumber.setText(o.getMobilePhoneNumber());
  	birthDayDateBox.setValue(o.getDateOfBirth());
  	DateTimeFormat dateFormat = DateTimeFormat.getShortDateFormat();
  	birthDayDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
  	addresse.setText(o.getStreet());
  	country.clear();
  	country.addItem(o.getCountry(),o.getCountry());
  	country.setSelectedIndex(0);
  	city.clear();
  	city.addItem(o.getCity(),o.getCity());
  	city.setSelectedIndex(0);
  	for(int i=0; i < maritalStatus.getItemCount();i++){
  	  if (maritalStatus.getValue(i).equals(o.getMaritalStatus().name())){
  	    maritalStatus.setSelectedIndex(i);
  	  }
  	}
  	for(int i=0; i < matrimonialRegime.getItemCount();i++){
      if (matrimonialRegime.getValue(i).equals(o.getMaritalStatus().name())){
        matrimonialRegime.setSelectedIndex(i);
      }
    }
  	$("#countryOther").hide();
    $("#cityOther").hide();
		
		// TODO Auto-generated method stub
		
	}
	private void init() {
	  title = new ListBox(false);
	  TitleTranslator translate = new TitleTranslator();
    title.addItem(translate.fromEnumToString(Title.MISS), Title.MISS.name());
    title.addItem(translate.fromEnumToString(Title.MRS), Title.MRS.name());
    title.addItem(translate.fromEnumToString(Title.MR), Title.MR.name());
	  maritalStatus = new ListBox(false);
	  MaritalStatusTranslator translateMT = new MaritalStatusTranslator();
    maritalStatus.addItem(translateMT.fromEnumToString(MaritalStatus.COHABITATION),MaritalStatus.COHABITATION.name());
    maritalStatus.addItem(translateMT.fromEnumToString(MaritalStatus.MARRIED),MaritalStatus.MARRIED.name());
    maritalStatus.addItem(translateMT.fromEnumToString(MaritalStatus.SINGLE),MaritalStatus.SINGLE.name());
    matrimonialRegime = new ListBox(false);
    MatrimonialRegimeTranslator translateMR = new MatrimonialRegimeTranslator();
    matrimonialRegime.addItem(translateMR.fromEnumToString(MatrimonialRegime.NONE),MatrimonialRegime.NONE.name());
    matrimonialRegime.addItem(translateMR.fromEnumToString(MatrimonialRegime.COMMUNITY),MatrimonialRegime.COMMUNITY.name());
    matrimonialRegime.addItem(translateMR.fromEnumToString(MatrimonialRegime.SEPARATION),MatrimonialRegime.SEPARATION.name());
	  city = new ListBox(false);
	  city.addChangeHandler(new ChangeHandler()
	  {
	    public void onChange(ChangeEvent event)
	    {
	      int selectedIndex = city.getSelectedIndex();
	      if (selectedIndex > -1){
	        presenter.onCitySelect(city.getValue(selectedIndex));
	      }
	    }
	  });
	  country = new ListBox();
	  country.addChangeHandler(new ChangeHandler()
    {
      public void onChange(ChangeEvent event)
      {
        int selectedIndex = country.getSelectedIndex();
        if (selectedIndex > -1){
          presenter.onCountrySelect(country.getValue(selectedIndex));
        }
      }
    });
	  btnSave = new Button("Sauver",new ClickListener() {
      public void onClick(Widget sender) {
        presenter.onButtonSaveClick();
      }
    });
	  btnCancel = new Button("Abandonner",new ClickListener() {
      public void onClick(Widget sender) {
        presenter.onButtonCancelClick();
      }
    });
	}
	@Override
	public void displayError(String error){
	  Window.alert(error);
	}
	@Override
	public void showCountryOther(Boolean show){
	  if (show.equals(true)){
	    $("#countryOther").show();
	  }else{
	    $("#countryOther").hide();
	  }
	}
	@Override
  public void showCityOther(Boolean show){
    if (show.equals(true)){
      $("#cityOther").show();
    }else{
      $("#cityOther").hide();
    }
  }
	@Override
  public void setTenantUpdateUiHandler(TenantUpdateUiHandlers handler) {
    this.presenter = handler;
  }
}
