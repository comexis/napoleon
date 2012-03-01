package eu.comexis.napoleon.client.core.lease;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.RealEstateState;
import eu.comexis.napoleon.shared.model.TypeOfRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class LeaseUpdateView extends ViewImpl implements LeaseUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, LeaseUpdateView> {
  }

  private final Widget widget;
  private LeaseUpdateUiHandlers presenter;

  @UiField
  ListBox reference;
  @UiField
  SuggestBox academicYear;
  @UiField
  ListBox tenantName;

  @Inject
  public LeaseUpdateView(final Binder binder) {
    init();
    widget = binder.createAndBindUi(this);
  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @Override
  public void displayError(String error) {
    Window.alert(error);
  }

  @Override
  public void displayValidationMessage(List<ValidationMessage> validationMessages) {
    UiHelper.displayValidationMessage(validationMessages, asWidget());
  }

  public SimpleTenant getTenant() {
    SimpleTenant t = null;
    String tenantId = tenantName.getValue(tenantName.getSelectedIndex());
    if (!tenantId.equals("(...)")){
      t = new SimpleTenant();
      t.setId(tenantId);
    }
    return t;
  }

  @UiHandler("btnCancel")
  public void onCancel(ClickEvent e) {
    presenter.onButtonCancelClick();
  }

  @UiHandler("btnSave")
  public void onSave(ClickEvent e) {
    presenter.onButtonSaveClick();
  }

  @Override
  public void setLease(Lease l) {
    // cleanup
    this.academicYear.setText("");
    
    if (l != null) {
      //this.reference.setText(l.getRealEstate().getReference());
      this.academicYear.setText(l.getAcademicYear());
    }
  }

  @Override
  public void setLeaseUpdateUiHandler(LeaseUpdateUiHandlers handler) {
    this.presenter = handler;
  }

  @Override
  public Lease updateLease(Lease l) {
    l.setAcademicYear(academicYear.getValue());
    return l;
  }

  private void init() {
    //
  }

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }
}