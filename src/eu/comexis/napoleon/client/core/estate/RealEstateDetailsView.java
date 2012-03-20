package eu.comexis.napoleon.client.core.estate;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class RealEstateDetailsView extends ViewImpl implements RealEstateDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateDetailsView> {
  }
  private static final Binder binder = GWT.create(Binder.class);
  private final Widget widget;
  private RealEstateDetailUiHandlers presenter;

  @UiField
  Element reference;
  @UiField
  Element address;
  @UiField
  Element condo;
  @UiField
  Element association;
  @UiField
  Element tel;
  @UiField
  Element gsm;
  @UiField
  Element email;
  @UiField
  Element addresseRealEstate;
  @UiField
  Element postalCode;
  @UiField
  Element city;
  @UiField
  Element country;
  @UiField
  Element number;
  @UiField
  Element box;
  @UiField
  Element square;
  @UiField
  Element type;
  @UiField
  Element state;
  @UiField
  Element dimension;
  @UiField
  Element ownerName;
  @UiField
  Element ownerTel;
  @UiField
  Element ownerGSM;
  @UiField
  Element ownerEmail;
  @UiField
  SimplePanel documentsPanel;

  @Inject
  public RealEstateDetailsView() {
    widget = binder.createAndBindUi(this);
  }
  
  public void bind() {
    $(ownerName).click(new Function() {
      @Override
      public void f() {
        presenter.showOwner();
      }
    });
    
  }
  
  public void unbind(){
    $(ownerName).unbind(Event.ONCLICK);
  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @UiHandler("btnDelete")
  public void onDeleteClicked(ClickEvent e) {
    Window.alert("Supprimer");
  }

  @UiHandler("btnToList")
  public void onGoToListClicked(ClickEvent e) {
    presenter.onButtonBackToListClick();
  }
  
  @UiHandler("btnRent")
  public void onGoToRentClicked(ClickEvent e) {
    presenter.onButtonRentClick();
  }
  
  @UiHandler("btnExpense")
  public void onGoToExpenseClicked(ClickEvent e) {
    presenter.onButtonExpenseClick();
  }
 

  @UiHandler("btnUpdate")
  public void onUpdateClicked(ClickEvent e) {
    presenter.onButtonUpdateClick();
  }

  @Override
  public void setRealEstate(RealEstate e) {
    // TODO improve and continue

    reference.setInnerText(e.getReference());
    type.setInnerText(e.getType() != null ? UiHelper.translateEnum(
        "TypeOfRealEstate_", e.getType()) : "");
    state.setInnerText(e.getState() != null ? UiHelper.translateEnum(
        "RealEstateState_", e.getState()) : "");
    dimension.setInnerText(e.getDimension());
    condo.setInnerText(e.getCondominium());
    association.setInnerText(e.getHomeownerAssociation());
    address.setInnerText(e.getAssocAdresss());
    tel.setInnerText(e.getAssocPhoneNumber());
    gsm.setInnerText(e.getAssocMobilePhoneNumber());
    email.setInnerText(e.getAssocEmail());
    addresseRealEstate.setInnerText(e.getStreet());
    postalCode.setInnerText(e.getPostalCode());
    city.setInnerText(e.getCity());
    country.setInnerText(e.getCountry());
    number.setInnerText(e.getNumber());
    box.setInnerText(e.getBox());
    square.setInnerText(e.getSquare());
    SimpleOwner o = e.getOwner();
    ownerName.setInnerText(o.getName());
    ownerTel.setInnerText(o.getPhoneNumber());
    ownerGSM.setInnerText(o.getMobileNumber());
    ownerEmail.setInnerText(o.getEmail());
    
  }

  @Override
  public void setPresenter(RealEstateDetailUiHandlers handler) {
    this.presenter = handler;
  }
  @Override
  public void addDocumentWidget(Widget w) {
    documentsPanel.add(w);
    
  }
}
