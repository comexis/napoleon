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

import eu.comexis.napoleon.shared.model.RealEstate;

public class RealEstateDetailsView extends ViewImpl implements RealEstateDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateDetailsView> {
  }

  private final Widget widget;
  private RealEstateDetailUiHandlers presenter;

  @UiField
  Element reference;
  @UiField
  Element addresse;

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
  public void setRealEstate(RealEstate o) {
    // TODO improve and continue

    reference.setInnerText(o.getReference());
    addresse.setInnerText(o.getStreet() + " " + o.getCity() + " " + o.getCountry());

    // TODO Auto-generated method stub

  }

  @Override
  public void setRealEstateDetailUiHandler(RealEstateDetailUiHandlers handler) {
    this.presenter = handler;

  }
}
