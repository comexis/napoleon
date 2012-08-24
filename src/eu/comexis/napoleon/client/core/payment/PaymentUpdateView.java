package eu.comexis.napoleon.client.core.payment;

import java.util.List;
import java.util.logging.Logger;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.model.PaymentTenant;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class PaymentUpdateView<T extends Payment> extends ViewImpl implements
    PaymentUpdatePresenter.MyView<T> {

  public interface Binder extends UiBinder<Widget, PaymentUpdateView<?>> {
  }

  @UiField
  Element reference;
  @UiField
  Element academicYear;
  @UiField
  TextBox amount;
  @UiField
  DateBox date;
  @UiField
  DateBox fromDate;
  @UiField
  DateBox toDate;
  @UiField
  RadioButton inCashYes;
  @UiField
  RadioButton inCashNo;
  @UiField
  TextBox number;
  @UiField
  TextBox communication;
  @UiField
  SuggestBox account;
  @UiField
  Element dueToOwner;
  @UiField
  Element expense;
  @UiField
  TextBox balance;
  @UiField
  Element fee;
  @UiField
  Element rent;
  @UiField
  Element previousBalance;
  @UiField
  Element rentWithoutFee;
  @UiField
  TextArea comments;
  
  @UiHandler("amount")
  public void onChangeAmount(ChangeEvent e) {
    Float solde = UiHelper.stringToFloat(dueToOwner.getInnerText()) - UiHelper.stringToFloat(amount.getValue());
    this.amount.setValue(UiHelper.FloatToString(UiHelper.stringToFloat(amount.getValue())));
    this.balance.setValue(UiHelper.FloatToString(solde));
  }
  @UiHandler("inCashYes")
  public void onInCashYes(ClickEvent e) {
	  changeDespositType();
  }
  @UiHandler("inCashNo")
  public void onInCashNo(ClickEvent e) {
	  changeDespositType();
  }
  
  private void changeDespositType() {
	  if (inCashYes.getValue().equals(true)) {
		  enableDepositBank(false);		  
	  } else {
		  enableDepositBank(true);	
	  }
  }  
  
  
  private void enableDepositBank(boolean enabled) {
	  if(!enabled){
		  this.account.setValue("");
		  this.number.setValue("");
		  this.communication.setValue("");
	  }
	  this.account.getTextBox().setEnabled(enabled);
	  this.number.setEnabled(enabled);
	  this.communication.setEnabled(enabled);
	  
  }      

  
  @UiHandler("btnCancel")
  public void onCancel(ClickEvent e) {
    presenter.onButtonCancelClick();
  }

  @UiHandler("btnSave")
  public void onSave(ClickEvent e) {
    presenter.onButtonSaveClick();
  }
  
  private final Widget widget;
  protected PaymentUpdateUiHandlers presenter;
  private static final Binder binder = GWT.create(Binder.class);

  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(PaymentUpdateView.class.getName());

  public PaymentUpdateView() {
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

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }
  @Override
  public void setLease(Lease l) {
    this.academicYear.setInnerText("");
    this.reference.setInnerText("");
    this.rent.setInnerText("");
    if (l!=null){
      this.academicYear.setInnerText(l.getAcademicYear());
      this.reference.setInnerText(l.getRealEstate().getReference());
      this.rent.setInnerText(UiHelper.FloatToString(l.getRent()));
    }
  }
  @Override
  public void setData(T payment) {
    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    this.fromDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.toDate.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.date.setFormat(new DateBox.DefaultFormat(dateFormat));
    this.date.setValue(null);
    this.fromDate.setValue(null);
    this.toDate.setValue(null);
    this.amount.setValue("");
    this.inCashNo.setValue(true);
    this.number.setValue("");
    this.communication.setValue("");
    this.dueToOwner.setInnerText("");
    this.balance.setValue("");
    this.fee.setInnerText("");
    this.previousBalance.setInnerText("");
    this.rentWithoutFee.setInnerText("");
    this.comments.setText("");
    $("#moreDetailTenant").show();
    $("#moreDetailOwner").show();
    if (payment != null) {
      this.date.setValue(payment.getPaymentDate());
      this.fromDate.setValue(payment.getPeriodStartDate());
      this.toDate.setValue(payment.getPeriodEndDate());
      this.amount.setValue(UiHelper.FloatToString(payment.getAmount()));
      this.comments.setText(payment.getComments());
      if (payment.getClass().equals(PaymentTenant.class)){
        this.account.setValue(((PaymentTenant)payment).getAccount()!=null ? ((PaymentTenant)payment).getAccount():"");
        this.inCashYes.setValue(((PaymentTenant)payment).getPaymentInCash()!=null ? ((PaymentTenant)payment).getPaymentInCash(): false);
        this.inCashNo.setValue(!this.inCashYes.getValue());
        this.enableDepositBank(!this.inCashYes.getValue());
        this.number.setValue(((PaymentTenant)payment).getNumber()!=null ? ((PaymentTenant)payment).getNumber():"");
        this.communication.setValue(((PaymentTenant)payment).getCommunication()!=null ? ((PaymentTenant)payment).getCommunication():"");
        $("#moreDetailOwner").hide();
      }else{
        PaymentOwner po = (PaymentOwner)payment;
        this.fee.setInnerText(UiHelper.FloatToString(((PaymentOwner)payment).getFee()) + " " + UiHelper.translateEnum("FeeUnit_", ((PaymentOwner)payment).getFeeUnit()));
        this.previousBalance.setInnerText(UiHelper.FloatToString(((PaymentOwner)payment).getPreviousbalance()));
        Float fRentWithoutFee = po.getRentWithoutFee()!=null ? po.getRentWithoutFee() : 0f;
        Float fPreviousBalance = po.getPreviousbalance()!=null ? po.getPreviousbalance() : 0f;
        Float fAmount = po.getAmount()!=null ? po.getAmount() : 0f;
        Float fExpense  = po.getExpense()!=null ? po.getExpense() : 0f;
        Float toOwner  = fRentWithoutFee+ fPreviousBalance - fExpense;
        this.dueToOwner.setInnerText(UiHelper.FloatToString(toOwner));
        this.balance.setValue(UiHelper.FloatToString(toOwner - fAmount));
        this.rentWithoutFee.setInnerText(UiHelper.FloatToString(fRentWithoutFee));
        this.expense.setInnerText(UiHelper.FloatToString(fExpense));
        $("#moreDetailTenant").hide();
      }
    }
  }

  @Override
  public void setPresenter(PaymentUpdateUiHandlers presenter) {
    this.presenter = presenter;

  }

  @Override
  public void updateData(T payment) {
    payment.setAmount(UiHelper.stringToFloat(amount.getValue()));
    payment.setPaymentDate(date.getValue());
    payment.setPeriodEndDate(this.toDate.getValue());
    payment.setPeriodStartDate(this.fromDate.getValue());
    payment.setComments(comments.getText());
    try{
      ((PaymentTenant)payment).setPaymentInCash(inCashYes.getValue());
      ((PaymentTenant)payment).setNumber(number.getValue());
      ((PaymentTenant)payment).setCommunication(communication.getValue());
      ((PaymentTenant)payment).setAccount(account.getValue());
    }catch(Exception e){
      //
    }
    try{
      ((PaymentOwner)payment).setBalance((UiHelper.stringToFloat(balance.getValue())));
    }catch(Exception e){
      //
    }
  }

  private void init() {
    //
  }
  
  @Override
  public void fillAccountList(List<String> ibans) {
	MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) account.getSuggestOracle();
		oracle.clear();
		if (ibans != null) {
		for (String sIban : ibans) {
				oracle.add(sIban);
		}
	}
  }	

}
