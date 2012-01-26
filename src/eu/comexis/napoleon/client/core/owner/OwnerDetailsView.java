package eu.comexis.napoleon.client.core.owner;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.shared.model.Owner;

public class OwnerDetailsView extends ViewImpl implements
		OwnerDetailsPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, OwnerDetailsView> {
	}
	
	@UiField
	Element name;
	@UiField
	Element firstName;
	@UiField
	Element email;
	@UiField
	Element phoneNumber;
	@UiField
	Element mobileNumber;
	@UiField
	Element birthDay;
	@UiField
	Element addresse;
	@UiField
	Element maritalStatus;

	@Inject
	public OwnerDetailsView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setOwner(Owner o) {
		//TODO improve and continue
		
		name.setInnerText(o.getLastName());
		firstName.setInnerText(o.getFirstName());
		email.setInnerText(o.getEmail());
		phoneNumber.setInnerText(o.getPhoneNumber());
		mobileNumber.setInnerText(o.getMobilePhoneNumber());
		birthDay.setInnerText(o.getDateOfBirth() != null ? o.getDateOfBirth().toGMTString() : "");
		addresse.setInnerText(o.getStreet()+ " " +o.getPostalCode()+ " " +o.getCity()+ " " +o.getCountry());
		maritalStatus.setInnerText(o.getMaritalStatus() != null ? o.getMaritalStatus().name().toLowerCase() : "");
		
		
		// TODO Auto-generated method stub
		
	}
}
