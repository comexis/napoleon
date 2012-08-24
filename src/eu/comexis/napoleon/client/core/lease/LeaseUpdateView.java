package eu.comexis.napoleon.client.core.lease;

import java.util.List;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.EntityStatus;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.TypeOfRent;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class LeaseUpdateView extends ViewImpl implements LeaseUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, LeaseUpdateView> {
  }

  private final Widget widget;
  private LeaseUpdateUiHandlers presenter;

  @UiField
  SpanElement reference;
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
  RadioButton depositInCashYes;
  @UiField
  RadioButton depositInCashNo;
  @UiField
  RadioButton depositAgency;
  @UiField
  TextBox ibanOwner;
  @UiField
  TextBox bicOwner;
  @UiField
  SuggestBox iban;
  @UiField
  TextBox bic;
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
  @UiField
  TextBox bookkeepingRef;
  @UiField
  CheckBox hasFurnituresRental;
  @UiField
  CheckBox hasFurnituresWithContract;
  @UiField
  CheckBox furnituresPaymentOK;
  @UiField
  DateBox furnituresDate;
  @UiField
  TextBox furnituresAmount;
  @UiField
  TextArea coocuppant;

  @Inject
  public LeaseUpdateView(final Binder binder) {
    init();
    widget = binder.createAndBindUi(this);
    initNames();
  }

  protected void initNames() {
    academicYear.getTextBox().setName("academicYear");
    iban.getTextBox().setName("iban");
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
  
  @Override
  public void fillIbanList(List<String> ibans) {
	MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) iban.getSuggestOracle();
		oracle.clear();
		if (ibans != null) {
		for (String sIban : ibans) {
				oracle.add(sIban);
		}
	}
  }	

  public SimpleTenant getTenant() {
    SimpleTenant t = null;
    String tenantId = tenantName.getValue(tenantName.getSelectedIndex());
    if (!tenantId.equals("-")) {
      t = new SimpleTenant();
      t.setId(tenantId);
    }
    return t;
  }

  @UiHandler("rent")
  public void onChangeRent(ChangeEvent e) {
    presenter.onRentChanged(UiHelper.stringToFloat(rent.getValue()));
    rent.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(rent.getValue())));
    feeOwner.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(rent.getValue())
    					+ UiHelper.stringToFloat(charges.getValue())
    					- UiHelper.stringToFloat(fee.getValue())));
  }

  @UiHandler("fee")
  public void onChangeFee(ChangeEvent e) {
	presenter.onRentChanged(UiHelper.stringToFloat(rent.getValue()));
    feeOwner.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(rent.getValue())
    		+ UiHelper.stringToFloat(charges.getValue())
    		- UiHelper.stringToFloat(fee.getValue())));
  }

  @UiHandler("deposit")
  public void onChangeDeposit(ChangeEvent e) {
    this.deposit.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(deposit.getValue())));
  }

  @UiHandler("charges")
  public void onChangeCharges(ChangeEvent e) {
    this.charges.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(charges.getValue())));
    presenter.onRentChanged(UiHelper.stringToFloat(rent.getValue()));
    feeOwner.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(rent.getValue())
    					+ UiHelper.stringToFloat(charges.getValue())
    					- UiHelper.stringToFloat(fee.getValue())));
  }

  @UiHandler("furnituresAmount")
  public void onChangeFurnituresAmount(ChangeEvent e) {
    this.furnituresAmount.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(furnituresAmount
        .getValue())));
  }

  @UiHandler("depositInCashYes")
  public void onChangeDepositInCashYes(ClickEvent e) {
	  changeDespositType();  
  }

  @UiHandler("depositInCashNo")
  public void onChangeDepositInCashNo(ClickEvent e) {
	  changeDespositType();  
  }
  
  @UiHandler("depositAgency")
  public void onChangeDepositAgency(ClickEvent e) {
	changeDespositType();    
  }

  

@UiHandler("hasFurnituresRental")
  public void onChangehasFurnituresRental(ClickEvent e) {
    if (hasFurnituresRental.getValue().equals(true)) {
      enableFurniture();
    } else {
      disableFurniture();
    }
  }

  @UiHandler("hasFurnituresWithContract")
  public void onChangeFurnituresWithContractYes(ClickEvent e) {
    if (hasFurnituresWithContract.getValue().equals(true)) {
      enableFurnitureContract();
    } else {
      disableFurnitureContract();
    }
  }

  private void enableFurniture() {
    hasFurnituresWithContract.setEnabled(true);
    disableFurnitureContract();
  }  
  
  private void enableDepositBank(boolean enabled) {
	  if(!enabled){
		  this.ibanOwner.setValue("");
		  this.bicOwner.setValue("");
	  }
	  this.bicOwner.setEnabled(enabled);	  
	  this.ibanOwner.setEnabled(enabled);    
  }
  
  private void enableDepositAgencyBank(boolean enabled) {
	  if(!enabled){
		  this.iban.setValue("");
		  this.bic.setValue("");
	  }
	  this.bic.setEnabled(enabled);	  
	  this.iban.getTextBox().setEnabled(enabled);
  }

  
  
  private void changeDespositType() {
	  if (depositInCashYes.getValue().equals(true)) {
		  enableDepositBank(false);
		  enableDepositAgencyBank(false);
	  } else {
		  if (depositAgency.getValue().equals(true)) {
			  enableDepositBank(false);
			  enableDepositAgencyBank(true);
		  } else {
			  enableDepositBank(true);
			  enableDepositAgencyBank(false);
		  }
	  }
  }  	
    

  private void disableFurniture() {
    hasFurnituresWithContract.setEnabled(false);
    disableFurnitureContract();
  }

  private void disableFurnitureContract() {
    furnituresAmount.setValue("");
    furnituresAmount.setEnabled(false);
    furnituresDate.setValue(null);
    furnituresDate.setEnabled(false);
    furnituresPaymentOK.setEnabled(false);
  }

  private void enableFurnitureContract() {
    furnituresAmount.setEnabled(true);
    furnituresDate.setEnabled(true);
    furnituresPaymentOK.setEnabled(true);
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
  public void setFee(Float fee) {
    this.fee.setValue(UiHelper.FloatToString(fee));
  }

  @Override
  public void setLease(Lease l) {
    // cleanup
    // set date format
    coocuppant.setSize("400px", "80px");
    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    this.startDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.endDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.eleDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.elsDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.depositDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.furnituresDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.coocuppant.setValue("");
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
    this.reference.setInnerHTML("");
    UiHelper.selectTextItemBoxByValue(this.tenantName, "-");
    UiHelper.selectTextItemBoxByValue(this.type, "NONE");
    this.furnituresPaymentOK.setValue(false);
    this.hasFurnituresWithContract.setValue(false);
    this.hasFurnituresRental.setValue(false);
    this.depositInCashYes.setValue(false);
    this.depositInCashNo.setValue(false);
    this.depositAgency.setValue(true);
    enableDepositBank(false);
    enableDepositAgencyBank(true);
    disableFurniture();

    if (l != null) {
      this.reference.setInnerText(l.getRealEstate().getReference());
      UiHelper.selectTextItemBoxByValue(this.tenantName, ((l.getTenant() != null && l.getTenant()
          .getId() != null) ? l.getTenant().getId() : "-"));
      this.coocuppant.setValue(l.getCooccupant());
      this.academicYear.setText(l.getAcademicYear());
      this.startDate.setValue(l.getStartDate());
      this.endDate.setValue(l.getEndDate());
      this.charges.setValue(UiHelper.FloatToString(l.getServiceCharges()));
      this.deposit.setValue(UiHelper.FloatToString(l.getSecurityDeposit()));
      this.depositDate.setValue(l.getDepositDate());
      if(l.getDepositInCash() != null){
    	  this.depositAgency.setValue(false);
    	  if(l.getDepositInCash()){
    		  this.depositInCashYes.setValue(true);
    		  enableDepositBank(false);
    		  enableDepositAgencyBank(false);
    	  } else {
    		  if(l.getDepositAgency() != null && l.getDepositAgency()){
    			  this.depositAgency.setValue(true);
    			  this.depositInCashNo.setValue(false);
    			  enableDepositBank(false);
    			  enableDepositAgencyBank(true);
    		  } else {
    			  this.depositInCashNo.setValue(true);
    			  enableDepositBank(true);
    			  enableDepositAgencyBank(false);
    		  }    		  
    	  } 
      } else {
    	  this.depositAgency.setValue(true);  
      }      
      this.iban.setValue(l.getIban());
      this.bic.setValue(l.getBic());
      this.ibanOwner.setValue(l.getIbanOwner());
      this.bicOwner.setValue(l.getBicOwner());
      this.eleDate.setValue(l.getEleDate());
      this.elsDate.setValue(l.getElsDate());
      this.rent.setValue(UiHelper.FloatToString(l.getRent()));
      this.fee.setValue(UiHelper.FloatToString(l.getFee()));
      float rent = l.getRent() != null ? l.getRent() : 0;
      float fee = l.getFee() != null ? l.getFee() : 0;
      float charges = l.getServiceCharges() != null ? l.getServiceCharges() : 0;
      this.feeOwner.setValue(UiHelper.FloatToString(rent + charges - fee));      
      this.bookkeepingRef.setValue(l.getBookkeepingReference());
      UiHelper
          .selectTextItemBoxByValue(this.type, (l.getType() != null ? l.getType().name() : "NONE"));
      this.hasFurnituresRental.setValue(l.getHasFurnituresRental() != null ? l
          .getHasFurnituresRental() : false);
      if (this.hasFurnituresRental.getValue()) {
        enableFurniture();
      }
      this.hasFurnituresWithContract.setValue(l.getHasFurnituresWithContract() != null ? l
          .getHasFurnituresWithContract() : false);
      if (this.hasFurnituresWithContract.getValue()) {
        enableFurnitureContract();
      }
      this.furnituresPaymentOK.setValue(l.getFurnituresPaymentOK() != null ? l
          .getFurnituresPaymentOK() : false);
      this.furnituresDate.setValue(l.getFurnituresDate());
      this.furnituresAmount.setValue(UiHelper.FloatToString(l.getFurnituresAnnualAmount()));
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
    l.setStartDate(startDate.getValue());
    l.setEndDate(endDate.getValue());
    l.setServiceCharges(UiHelper.stringToFloat(charges.getValue()));
    l.setSecurityDeposit(UiHelper.stringToFloat(deposit.getValue()));
    l.setDepositInCash(depositInCashYes.getValue());
    l.setDepositAgency(depositAgency.getValue());
    l.setDepositDate(depositDate.getValue());
    l.setIban(iban.getValue());
    l.setBic(bic.getValue());
    l.setIbanOwner(ibanOwner.getValue());
    l.setBicOwner(bicOwner.getValue());
    l.setEleDate(eleDate.getValue());
    l.setElsDate(elsDate.getValue());
    l.setRent(UiHelper.stringToFloat(rent.getValue()));
    // l.setUnit(FeeUnit.LUMP_SUM);
    l.setFee(UiHelper.stringToFloat(fee.getValue()));
    l.setBookkeepingReference(bookkeepingRef.getValue());
    l.setType(TypeOfRent.valueOf(type.getValue(type.getSelectedIndex())));
    l.setHasFurnituresRental(hasFurnituresRental.getValue());
    l.setHasFurnituresWithContract(hasFurnituresWithContract.getValue());
    l.setFurnituresPaymentOK(furnituresPaymentOK.getValue());
    l.setFurnituresDate(furnituresDate.getValue());
    l.setFurnituresAnnualAmount(UiHelper.stringToFloat(furnituresAmount.getValue()));
    l.setCooccupant(coocuppant.getValue());
    return l;
  }

  private void init() {
    type = UiHelper.createListBoxForEnum(TypeOfRent.class, "TypeOfRent_", false);
  }

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }

  @Override
  public void fillTenantList(List<SimpleTenant> tenants) {
    tenantName.clear();
    tenantName.addItem("-", "-");
    for (SimpleTenant t : tenants) {
      if(EntityStatus.isActif(t.getEntityStatus())){  //Only ACTIVE tenants must be displayed in the Dropdown
    	  tenantName.addItem(t.getName(), t.getId());
      }
    }    

  }
}