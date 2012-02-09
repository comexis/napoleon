package eu.comexis.napoleon.client.core.estate;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class RealEstateDetailsView extends ViewImpl implements RealEstateDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateDetailsView> {
  }

  private final Widget widget;
  private RealEstateDetailUiHandlers presenter;

  @UiField
  Element reference;
  @UiField
  Element addresse;
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

  @Inject
  public RealEstateDetailsView(final Binder binder) {
    widget = binder.createAndBindUi(this);
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

  @UiHandler("btnUpdate")
  public void onUpdateClicked(ClickEvent e) {
    presenter.onButtonUpdateClick();
  }

  @Override
  public void setRealEstate(RealEstate e, SimpleOwner o,Condo cdo) {
    // TODO improve and continue

    reference.setInnerText(e.getReference());
    addresseRealEstate.setInnerText(e.getStreet() + " " + e.getCity() + " " + e.getCountry());
    number.setInnerText(e.getNumber());
    box.setInnerText(e.getBox());
    square.setInnerText(e.getSquare());
    type.setInnerText(e.getType().toString());
    state.setInnerText(e.getState().toString());
    dimension.setInnerText(e.getDimension());
    if (cdo!=null){
      condo.setInnerText(cdo.getName());
      association.setInnerText(cdo.getHomeownerAssociation());
      addresse.setInnerText(cdo.getStreet() + " " + cdo.getCity() + " " + cdo.getCountry());
      tel.setInnerText(cdo.getPhoneNumber());
      gsm.setInnerText(cdo.getMobilePhoneNumber());
      email.setInnerText(cdo.getEmail());
      }
    if (o != null){
      ownerName.setInnerText(o.getName());
      ownerTel.setInnerText(o.getPhoneNumber());
      ownerGSM.setInnerText(o.getMobileNumber());
    //ownerEmail.setInnerText(o.getEmail());
    }
    
  }

  @Override
  public void setRealEstateDetailUiHandler(RealEstateDetailUiHandlers handler) {
    this.presenter = handler;

  }
}
