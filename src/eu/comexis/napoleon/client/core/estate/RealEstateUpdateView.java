package eu.comexis.napoleon.client.core.estate;

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
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.utils.MaritalStatusTranslator;
import eu.comexis.napoleon.shared.model.utils.MatrimonialRegimeTranslator;
import eu.comexis.napoleon.shared.model.utils.TitleTranslator;

public class RealEstateUpdateView extends ViewImpl implements
  RealEstateUpdatePresenter.MyView {

	private final Widget widget;
	private RealEstateUpdateUiHandlers presenter;
	public interface Binder extends UiBinder<Widget, RealEstateUpdateView> {
	}
	
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
	@UiField(provided = true)
	Button btnSave;
	@UiField(provided = true)
  Button btnCancel;

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
  public RealEstate updateRealEstate(RealEstate o) {
    o.setReference(reference.getValue());
    o.setStreet(addresse.getValue());
    o.setCity(city.getValue(city.getSelectedIndex()).equals("(...)") ? cityOther.getValue():city.getValue(city.getSelectedIndex()));
    o.setCountry(country.getItemText(country.getSelectedIndex()).equals("(...)") ? countryOther.getValue():country.getItemText(country.getSelectedIndex()));
    return o;
  }
	@Override
	public void setRealEstate(RealEstate o) {
	  
  	reference.setText(o.getReference());
  	addresse.setText(o.getStreet());
  	country.clear();
  	country.addItem(o.getCountry(),o.getCountry());
  	country.setSelectedIndex(0);
  	city.clear();
  	city.addItem(o.getCity(),o.getCity());
  	city.setSelectedIndex(0);
  	$("#countryOther").hide();
    $("#cityOther").hide();
		
		// TODO Auto-generated method stub
		
	}
	private void init() {
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
  public void setRealEstateUpdateUiHandler(RealEstateUpdateUiHandlers handler) {
    this.presenter = handler;
  }
}
