package eu.comexis.napoleon.client.core.expense;

import java.util.List;

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
import eu.comexis.napoleon.shared.model.FeeUnit;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.TypeOfRent;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class ExpenseUpdateView extends ViewImpl implements ExpenseUpdatePresenter.MyView {

  public interface Binder extends UiBinder<Widget, ExpenseUpdateView> {
  }

  private final Widget widget;
  private ExpenseUpdateUiHandlers presenter;

  @UiField
  TextBox reference;
  @UiField
  TextBox amount;
  @UiField
  DateBox dateInvoice;
  

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
    this.reference.setValue("");
    this.amount.setValue("");
    this.dateInvoice.setValue(null);
    
    
    if (l != null) {
      this.reference.setValue(l.getReference());
      this.amount.setValue(UiHelper.FloatToString(l.getAmount()));
      this.dateInvoice.setValue(l.getDateFacture());
    }
  }

  @Override
  public void setExpenseUpdateUiHandler(ExpenseUpdateUiHandlers handler) {
    this.presenter = handler;
  }

  @Override
  public Expense updateExpense(Expense l) {
    l.setReference(reference.getValue());
    l.setAmount(UiHelper.stringToFloat(this.amount.getValue()));
    return l;
  }

  private void init() {
    //type = UiHelper.createListBoxForEnum(TypeOfRent.class, "TypeOfRent_", false);
  }

  @Override
  public void reset() {
    UiHelper.resetForm(asWidget());
  }
}