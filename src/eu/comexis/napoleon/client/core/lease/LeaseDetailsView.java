package eu.comexis.napoleon.client.core.lease;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class LeaseDetailsView extends ViewImpl implements LeaseDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, LeaseDetailsView> {
  }

  private final Widget widget;
  private LeaseDetailUiHandlers presenter;

  @UiField
  Element reference;
  @UiField
  Element academicYear;
  

  @Inject
  public LeaseDetailsView(final Binder binder) {
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
  public void setLease(Lease l) {
    // TODO improve and continue

    reference.setInnerText(l.getRealEstate().getReference());
    academicYear.setInnerText(l.getAcademicYear());
    
  }

  @Override
  public void setLeaseDetailUiHandler(LeaseDetailUiHandlers handler) {
    this.presenter = handler;

  }
}
