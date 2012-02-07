package eu.comexis.napoleon.client.core.estate;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
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
  @UiField(provided = true)
  Button btnUpdate;
  @UiField(provided = true)
  Button btnDelete;
  @UiField(provided = true)
  Button btnToDashBoard;

  @Inject
  public RealEstateDetailsView(final Binder binder) {
    init();
    widget = binder.createAndBindUi(this);
  }

  @Override
  public Widget asWidget() {
    return widget;
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

  private void init() {
    btnUpdate = new Button("Editer", new ClickListener() {
      public void onClick(Widget sender) {
        presenter.onButtonUpdateClick();
      }
    });
    btnToDashBoard = new Button("Retour vers le tableau de bord", new ClickListener() {
      public void onClick(Widget sender) {
        presenter.onButtonBackToDashBoardClick();
      }
    });
    btnDelete = new Button("Supprimer", new ClickListener() {
      public void onClick(Widget sender) {
        Window.alert("Supprimer");
      }
    });
  }
}
