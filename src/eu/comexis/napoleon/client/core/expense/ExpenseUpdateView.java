package eu.comexis.napoleon.client.core.expense;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Contractor;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class ExpenseUpdateView extends ViewImpl implements ExpenseUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, ExpenseUpdateView> {
  }

  private final Widget widget;
  private ExpenseUpdateUiHandlers presenter;
  private HashMap<String,Contractor> allContractors = new HashMap<String,Contractor>();

  @UiField
  TextBox reference;
  @UiField
  TextBox amount;
  @UiField
  TextBox toBePaidByOwner;
  @UiField
  TextBox toBePaidByTenant;
  @UiField
  DateBox dateInvoice;
  @UiField
  DateBox dateWork;
  @UiField
  DateBox dateInform;
  @UiField
  SuggestBox typeWork;
  @UiField
  CheckBox paidByTenant;
  @UiField
  DateBox datePaidByTenant;
  @UiField
  ListBox contractor;
  @UiField
  CheckBox filtered;
  @UiField
  TextBox contractorTel;
  @UiField
  TextBox contractorGSM;
  @UiField
  TextBox contractorAccount;
  @UiField
  TextBox contractorName;
  @UiField
  Element ownerName;
  @UiField
  Element ownerAddress;
  @UiField
  Element ownerTel;
  @UiField
  Element ownerGSM;
  @UiField
  Element ownerMail;
  @UiField
  Element estateRef;
  @UiField
  Element estateAddress;
  
  @Inject
  public ExpenseUpdateView(final Binder binder) {
    init();
    widget = binder.createAndBindUi(this);
    initNames();
  }
  
  protected void initNames() {
    //academicYear.getTextBox().setName("academicYear");
  }
  
  @Override
  public void fillTypeWorkList(List<String> works) {
    MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) this.typeWork.getSuggestOracle();
    oracle.clear();
    if (works != null) {
      for (String sVal : works) {
        if (sVal != null) {
          oracle.add(sVal);
        }
      }
    }
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
  public void setEstate(RealEstate e){
    this.estateRef.setInnerText(e.getReference());
    this.estateAddress.setInnerText(e.getFullAddress());
    SimpleOwner o = e.getOwner();
    this.ownerName.setInnerText(o.getName());
    this.ownerTel.setInnerText(o.getPhoneNumber());
    this.ownerGSM.setInnerText(o.getMobileNumber());
    this.ownerMail.setInnerHTML("<a href='mailto:" + o.getEmail() + ">" + o.getEmail() + "</a>");
    this.ownerAddress.setInnerText(o.getAddress() + " " + o.getPostalCode() + " " + o.getCity());
  }
  public void setContractor (String ctorName){
    if (this.allContractors!=null){
      Contractor ctor = this.allContractors.get(ctorName);
      if (ctor!=null){
        this.contractorName.setValue(ctor.getName());
        this.contractorAccount.setValue(ctor.getAccount());
        this.contractorGSM.setValue(ctor.getMobile());
        this.contractorTel.setValue(ctor.getPhone());
      }else{
        this.contractorName.setValue("");
        this.contractorAccount.setValue("");
        this.contractorGSM.setValue("");
        this.contractorTel.setValue("");
      }
    }else{
      this.contractorName.setValue(ctorName);
    }
  }
  public Contractor getContractor() {
    Contractor ctor = null;
    if (this.contractor.getItemText(this.contractor.getSelectedIndex()).equals("-") && !this.contractorName.getValue().isEmpty()){
      ctor = new Contractor();
      ctor.setName(this.contractorName.getValue());
      ctor.setMobile(this.contractorGSM.getValue());
      ctor.setPhone(this.contractorTel.getValue());
      ctor.setAccount(this.contractorAccount.getValue());
    }else{
      ctor = allContractors.get(this.contractor.getItemText(this.contractor.getSelectedIndex()));
      ctor.setMobile(this.contractorGSM.getValue());
      ctor.setPhone(this.contractorTel.getValue());
      ctor.setAccount(this.contractorAccount.getValue());
    }
    return ctor;
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
  public void setExpense(Expense l) {
    // cleanup
    // set date format
    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    this.dateInvoice.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.dateInform.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.datePaidByTenant.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.dateWork.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.reference.setValue("");
    this.amount.setValue("");
    this.dateInvoice.setValue(null);
    this.datePaidByTenant.setValue(null);
    this.dateInform.setValue(null);
    this.dateWork.setValue(null);
    this.toBePaidByOwner.setValue("");
    this.toBePaidByTenant.setValue("");
    this.paidByTenant.setValue(false);
    
    
    if (l != null) {
      this.reference.setValue(l.getReference());
      this.amount.setValue(UiHelper.FloatToString(l.getAmount()));
      this.dateInvoice.setValue(l.getDateInvoice());
      this.dateInform.setValue(l.getDateInform());
      this.datePaidByTenant.setValue(l.getDatePaymentTenant());
      this.dateWork.setValue(l.getDateWork());
      this.toBePaidByOwner.setValue(UiHelper.FloatToString(l.getToBePaidByOwner()));
      this.toBePaidByTenant.setValue(UiHelper.FloatToString(l.getToBePaidByTenant()));
      this.paidByTenant.setValue(l.getIsPaidByTenant());
      this.typeWork.setValue(l.getTypeOfWork());
      if (l.getContractor() != null){
        this.contractorName.setValue(l.getContractor().getName());
        this.contractorAccount.setValue(l.getContractor().getAccount());
        this.contractorGSM.setValue(l.getContractor().getMobile());
        this.contractorTel.setValue(l.getContractor().getPhone());
        UiHelper.selectTextItemBoxByValue(this.contractor, l.getContractor().getName());
      }else{
        setContractor("-");
      }
    }
  }

  @Override
  public void setExpenseUpdateUiHandler(ExpenseUpdateUiHandlers handler) {
    this.presenter = handler;
  }
  @UiHandler("typeWork")
  public void onChangeTypeWork(SelectionEvent<SuggestOracle.Suggestion> event) {
    filterContractorList(event.getSelectedItem().getReplacementString());
    setContractor(this.contractor.getItemText(this.contractor.getSelectedIndex()));
  }
  @UiHandler("filtered")
  public void onChangeFiltered(ClickEvent event) {
    filterContractorList(typeWork.getValue());
    setContractor(this.contractor.getItemText(this.contractor.getSelectedIndex()));
  }
  @UiHandler("contractor")
  public void onChangeContractor(ChangeEvent event) {
    setContractor(this.contractor.getItemText(this.contractor.getSelectedIndex()));
  }
  @Override
  public Expense updateExpense(Expense l) {
    l.setReference(reference.getValue());
    l.setAmount(UiHelper.stringToFloat(this.amount.getValue()));
    l.setToBePaidByOwner(UiHelper.stringToFloat(this.toBePaidByOwner.getValue()));
    l.setToBePaidByTenant(UiHelper.stringToFloat(this.toBePaidByTenant.getValue()));
    l.setDateInvoice(this.dateInvoice.getValue());
    l.setDateInform(this.dateInform.getValue());
    l.setDatePaymentTenant(this.datePaidByTenant.getValue());
    l.setDateWork(dateWork.getValue());
    l.setIsPaidByTenant(this.paidByTenant.getValue());
    l.setTypeOfWork(this.typeWork.getValue());
    l.setContractor(getContractor());
    return l;
  }
  @Override
  public void fillContractorList(List<Contractor> contractors) {
    this.contractor.clear();
    for (Contractor c : contractors) {
      contractor.addItem(c.getName(), c.getName());
      allContractors.put(c.getName(), c);
    }
    contractor.addItem("-", "-");
  }
  private void filterContractorList(String value) {
    if (this.filtered.getValue().equals(true) && !value.isEmpty()){
      this.contractor.clear();
      for (Contractor c : allContractors.values()) {
        if (c.hasSkill(value)){
          contractor.addItem(c.getName(), c.getName());
        }
      }
      contractor.addItem("-", "-");
    }else{
      this.contractor.clear();
      for (Contractor c : allContractors.values()) {
          contractor.addItem(c.getName(), c.getName());
      }
      contractor.addItem("-", "-");
    }
    UiHelper.selectTextItemBoxByValue(this.contractor, contractorName.getValue());
  }
  private void init() {
    //type = UiHelper.createListBoxForEnum(TypeOfRent.class, "TypeOfRent_", false);
  }

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }
}