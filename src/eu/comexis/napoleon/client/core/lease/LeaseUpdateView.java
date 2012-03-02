package eu.comexis.napoleon.client.core.lease;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.AmountOfTimeUnit;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.MaritalStatus;
import eu.comexis.napoleon.shared.model.MatrimonialRegime;
import eu.comexis.napoleon.shared.model.Title;
import eu.comexis.napoleon.shared.model.TypeOfRent;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
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
  @UiField
  DateBox startDate;
  @UiField
  DateBox endDate;
  @UiField
  DateBox eleDate;
  @UiField
  DateBox elsDate;
  @UiField
  DateBox depositDate;
  @UiField
  TextBox deposit;
  @UiField
  TextBox fee;
  @UiField
  TextBox feeOwner;
  @UiField
  TextBox rent;
  @UiField
  TextBox charges;
  @UiField(provided = true)
  ListBox type;
  @UiField(provided = true)
  ListBox unit;
  @UiField
  TextBox
  bookkeepingRef;
  

  @Inject
  public LeaseUpdateView(final Binder binder) {
    init();
    widget = binder.createAndBindUi(this);
    initNames();
  }
  
  protected void initNames() {
    academicYear.getTextBox().setName("academicYear");
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
  
  @Override
  public void fillAcademicYearList(List<String> years) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) academicYear.getSuggestOracle();
    oracle.clear();
    if (years != null) {
      for (String sVal : years) {
        if (sVal != null) {
          oracle.add(sVal);
        }
      }
    }
  }

  public SimpleTenant getTenant() {
    SimpleTenant t = null;
    String tenantId = tenantName.getValue(tenantName.getSelectedIndex());
    if (!tenantId.equals("-")){
      t = new SimpleTenant();
      t.setId(tenantId);
    }
    return t;
  }
  
  public SimpleRealEstate getEstate() {
    SimpleRealEstate e = null;
    String estateId = reference.getValue(reference.getSelectedIndex());
    if (!estateId.equals("-")){
      e = new SimpleRealEstate();
      e.setId(estateId);
    }
    return e;
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
    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    this.startDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.endDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.eleDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.elsDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.depositDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.reference.setEnabled(true);
    
    this.academicYear.setText("");
    this.startDate.setValue(null);
    this.endDate.setValue(null);
    this.fee.setText("");
    this.feeOwner.setText("");
    this.charges.setText("");
    this.deposit.setText("");
    this.rent.setText("");
    this.eleDate.setValue(null);
    this.elsDate.setValue(null);
    this.depositDate.setValue(null);
    if (l != null) {
      UiHelper.selectTextItemBoxByValue(this.reference, (l.getRealEstate().getId() != null ? l.getRealEstate().getId() : "-"));
      UiHelper.selectTextItemBoxByValue(this.tenantName, (l.getTenant().getId() != null ? l.getTenant().getId() : "-"));
      this.academicYear.setText(l.getAcademicYear());
      this.startDate.setValue(l.getStartDate());
      this.endDate.setValue(l.getEndDate());
      this.reference.setEnabled(false);
      this.charges.setValue("" + l.getServiceCharges());
      this.deposit.setValue("" + l.getSecurityDeposit());
      this.depositDate.setValue(l.getDepositDate());
      this.eleDate.setValue(l.getEleDate());
      this.elsDate.setValue(l.getElsDate());
      this.fee.setValue(""); // should be calculated from owner and rent
      this.feeOwner.setValue(""); // should be calculated from owner and rent
      this.rent.setValue(l.getRent() + "");
      this.bookkeepingRef.setValue(l.getBookkeepingReference());
      UiHelper.selectTextItemBoxByValue(this.type, (l.getType() != null ? l.getType().name() : "-"));
      UiHelper.selectTextItemBoxByValue(this.unit, (l.getDurationUnit() != null ? l.getDurationUnit().name() : "-"));
    }
  }

  @Override
  public void setLeaseUpdateUiHandler(LeaseUpdateUiHandlers handler) {
    this.presenter = handler;
  }

  @Override
  public Lease updateLease(Lease l) {
    l.setAcademicYear(academicYear.getValue());
    l.setTenant(getTenant());
    l.setRealEstate(getEstate());
    l.setStartDate(startDate.getValue());
    l.setEndDate(endDate.getValue());
    return l;
  }

  private void init() {
    type = UiHelper.createListBoxForEnum(TypeOfRent.class, "TypeOfRent_", false);
    unit = UiHelper.createListBoxForEnum(AmountOfTimeUnit.class, "AmountOfTimeUnit_", false);
  }

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }

  @Override
  public void fillEstateList(List<SimpleRealEstate> estates) {
    reference.clear();
    for (SimpleRealEstate e : estates) {
      reference.addItem(e.getReference(), e.getId());
    }
    reference.addItem("-", "-");
    
  }

  @Override
  public void fillTenantList(List<SimpleTenant> tenants) {
    tenantName.clear();
    for (SimpleTenant t : tenants) {
      tenantName.addItem(t.getName(), t.getId());
    }
    tenantName.addItem("-", "-");
    
  }
}