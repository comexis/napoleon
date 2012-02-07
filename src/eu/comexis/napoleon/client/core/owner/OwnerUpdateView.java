package eu.comexis.napoleon.client.core.owner;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.MatrimonialRegime;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.Title;

public class OwnerUpdateView extends ViewImpl implements
		OwnerUpdatePresenter.MyView {

	private final Widget widget;
	private OwnerUpdateUiHandlers presenter;

	public interface Binder extends UiBinder<Widget, OwnerUpdateView> {
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

	@Inject
	public OwnerUpdateView(final Binder binder) {
		init();
		widget = binder.createAndBindUi(this);
	}

	@UiHandler("btnSave")
	public void onSave(ClickEvent e){
		presenter.onButtonSaveClick();
	}

	@UiHandler("btnCancel")
	public void onCancel(ClickEvent e){
		presenter.onButtonCancelClick();
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
		for (int i = 0; i < cities.size(); i++) {
			String sCity = cities.get(i);
			city.addItem(cities.get(i));
			if (cityToSelect != null && sCity.equals(cityToSelect)) {
				index = i;
			}
		}
		city.addItem("(...)");
		city.setItemSelected(index, true);
	}

	@Override
	public String getSelectedCountry() {
		Integer index = country.getSelectedIndex();
		String countryToSelect = country.getValue(index);
		return countryToSelect;
	}

	@Override
	public void fillCountryList(List<Country> countries) {
		String countryToSelect = country.getValue(country.getSelectedIndex());
		country.clear();
		Integer index = 0;
		for (int i = 0; i < countries.size(); i++) {
			String sCountry = countries.get(i).getName();
			country.addItem(countries.get(i).getName(), countries.get(i)
					.getId());
			if (countryToSelect != null && sCountry.equals(countryToSelect)) {
				index = i;
			}
		}
		country.addItem("(...)", "(...)");
		country.setItemSelected(index, true);
	}

	@Override
	public Owner updateOwner(Owner o) {
		o.setTitle(Title.valueOf(title.getValue(title.getSelectedIndex())));
		o.setFirstName(firstName.getValue());
		o.setLastName(name.getValue());
		o.setEmail(email.getValue());
		o.setPhoneNumber(phoneNumber.getValue());
		o.setMobilePhoneNumber(mobileNumber.getValue());
		o.setDateOfBirth(birthDayDateBox.getValue());
		o.setStreet(addresse.getValue());
		o.setCity(city.getValue(city.getSelectedIndex()).equals("(...)") ? cityOther
				.getValue() : city.getValue(city.getSelectedIndex()));
		o.setCountry(country.getItemText(country.getSelectedIndex()).equals(
				"(...)") ? countryOther.getValue() : country
				.getItemText(country.getSelectedIndex()));
		o.setMaritalStatus(MaritalStatus.fromStringToEnum(maritalStatus
				.getValue(maritalStatus.getSelectedIndex())));
		o.setMatrimonialRegime(MatrimonialRegime
				.fromStringToEnum(matrimonialRegime.getValue(matrimonialRegime
						.getSelectedIndex())));
		return o;
	}

	@Override
	public void setOwner(Owner o) {
		for (int i = 0; i < title.getItemCount(); i++) {
			if (title.getValue(i).equals(o.getTitle().name())) {
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
		country.addItem(o.getCountry(), o.getCountry());
		country.setSelectedIndex(0);
		city.clear();
		city.addItem(o.getCity(), o.getCity());
		city.setSelectedIndex(0);
		for (int i = 0; i < maritalStatus.getItemCount(); i++) {
			if (maritalStatus.getValue(i).equals(o.getMaritalStatus().name())) {
				maritalStatus.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < matrimonialRegime.getItemCount(); i++) {
			if (matrimonialRegime.getValue(i).equals(
					o.getMaritalStatus().name())) {
				matrimonialRegime.setSelectedIndex(i);
			}
		}
		$("#countryOther").hide();
		$("#cityOther").hide();

		// TODO Auto-generated method stub

	}

	private void init() {

		title = UiHelper.createListBoxForEnum(Title.class, "Title_", false);
		maritalStatus = UiHelper.createListBoxForEnum(MaritalStatus.class, "MaritalStatus_", false);
		matrimonialRegime = UiHelper.createListBoxForEnum(MatrimonialRegime.class, "MatrimonialRegime_", false);

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

	@Override
	public void displayError(String error) {
		Window.alert(error);
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
	public void showCityOther(Boolean show) {
		if (show.equals(true)) {
			$("#cityOther").show();
		} else {
			$("#cityOther").hide();
		}
	}

	@Override
	public void setOwnerUpdateUiHandler(OwnerUpdateUiHandlers handler) {
		this.presenter = handler;
	}
}