package eu.comexis.napoleon.client.core.expense;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.core.expense.ExpenseDetailUiHandlers;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Expense;

public class ExpenseDetailsView extends ViewImpl implements ExpenseDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, ExpenseDetailsView> {
  }
  private static final Binder binder = GWT.create(Binder.class);
  private final Widget widget;
  private ExpenseDetailUiHandlers presenter;

  @UiField
  Element reference;
  @UiField
  Element amount;
  @UiField
  Element dateInvoice;
  @UiField
  SimplePanel documentsPanel;
  
  @Inject
  public ExpenseDetailsView() {
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
  public void setExpense(Expense l) {
    // TODO improve and continue

    this.reference.setInnerText(l.getReference());
    this.amount.setInnerText(UiHelper.FloatToString(l.getAmount()));
    this.dateInvoice.setInnerText(UiHelper.displayDate(l.getDateInvoice()));
  }

  @Override
  public void setPresenter(ExpenseDetailUiHandlers handler) {
    this.presenter = handler;
  }
  @Override
  public void addDocumentWidget(Widget w) {
    documentsPanel.add(w);
    
  }
}
