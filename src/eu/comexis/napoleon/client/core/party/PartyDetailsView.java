package eu.comexis.napoleon.client.core.party;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Party;

public abstract class PartyDetailsView<T extends Party> extends ViewImpl implements
    PartyDetailsPresenter.MyView<T> {

  public interface Binder extends UiBinder<Widget, PartyDetailsView<?>> {
  }

  private static final Binder binder = GWT.create(Binder.class);

  @UiField
  Element additionnalData;

  @UiField
  Element addresse;
  @UiField
  Element number;
  @UiField
  Element box;
  @UiField
  Element city;
  @UiField
  Element postalCode;
  @UiField
  Element country;

  @UiField
  Element bic;
  @UiField
  Element birthDay;
  @UiField
  Element bottomAdditionnalData;
  @UiField
  Element addressAdditionnalData;
  @UiField
  Element email;
  @UiField
  Element fax;
  @UiField
  Element iban;
  @UiField
  Element job;
  @UiField
  Element maritalStatus;
  @UiField
  Element matrimonialRegime;
  @UiField
  Element mobileNumber;
  @UiField
  Element name;
  @UiField
  Element nationality;
  @UiField
  Element nationalRegister;
  @UiField
  Element vatNumber;
  @UiField
  Element phoneNumber;
  @UiField
  Element placeOfBirth;
  @UiField
  SimplePanel documentsPanel;
  
  private PartyDetailsUiHandlers presenter;
  private final Widget widget;

  @Inject
  public PartyDetailsView() {
    widget = binder.createAndBindUi(this);
  }

  @Override
  public void addDocumentWidget(Widget w) {
    documentsPanel.add(w);
  }
  
  @Override
  public Widget asWidget() {
    return widget;
  }

  private void displayName(T party) {
    StringBuilder nameBuilder = new StringBuilder();
    nameBuilder.append(UiHelper.translateEnum("Title_", party.getTitle())).append(" ");
    nameBuilder.append(UiHelper.formatLastName(party.getLastName())).append(" ");
    nameBuilder.append(UiHelper.formatFirstName(party.getFirstName()));
    name.setInnerText(nameBuilder.toString());
  }

  @UiHandler("btnDelete")
  public void onDeleteClicked(ClickEvent e) {
    Window.alert("Supprimer");
  }

  @UiHandler("btnToList")
  public void onGoToListClicked(ClickEvent e) {
    presenter.onButtonBackToListClick();
  }

  @UiHandler("btnUpdate")
  public void onUpdateClicked(ClickEvent e) {
    presenter.onButtonUpdateClick();
  }

  protected void setBottomAdditionnalData(SafeHtml data) {
    bottomAdditionnalData.setInnerHTML(data.asString());

  }
  protected void setAddressAdditionnalData(SafeHtml data) {
    addressAdditionnalData.setInnerHTML(data.asString());
  }

  @Override
  public void setData(T party) {

    displayName(party);

    iban.setInnerText(party.getIban());
    bic.setInnerText(party.getBic());
    email.setInnerText(party.getEmail());
    phoneNumber.setInnerText(party.getPhoneNumber());
    mobileNumber.setInnerText(party.getMobilePhoneNumber());
    fax.setInnerText(party.getFax());
    placeOfBirth.setInnerText(party.getPlaceOfBirth());
    nationality.setInnerText(party.getNationality());
    job.setInnerText(party.getJobTitle());
    nationalRegister.setInnerText(party.getNationalRegisterNumber());
    vatNumber.setInnerText(party.getVatNumber());
    birthDay.setInnerText(UiHelper.displayDate(party.getDateOfBirth()));
    addresse.setInnerText(party.getStreet());
    number.setInnerText(party.getNumber());
    postalCode.setInnerText(party.getPostalCode());
    city.setInnerText(party.getCity());
    country.setInnerText(party.getCountry());
    maritalStatus.setInnerText(party.getMaritalStatus() != null ? UiHelper.translateEnum(
        "MaritalStatus_", party.getMaritalStatus()) : "");
    matrimonialRegime.setInnerText(party.getMatrimonialRegime() != null ? UiHelper.translateEnum(
        "MatrimonialRegime_", party.getMatrimonialRegime()) : "");
    box.setInnerText(party.getBox());
    
  }

  @Override
  public void setPresenter(PartyDetailsUiHandlers handler) {
    this.presenter = handler;
  }

  protected void setTopAdditionnalData(SafeHtml data) {
    additionnalData.setInnerHTML(data.asString());

  }

}
