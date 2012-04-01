package eu.comexis.napoleon.client.core.expense;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
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

public class ExpenseDetailsView extends ViewImpl implements ExpenseDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, ExpenseDetailsView> {
  }
  public interface Templates extends SafeHtmlTemplates {

    Templates INSTANCE = GWT.create(Templates.class);

    @Template("{0}<br/>{1} {2}")
    SafeHtml address(String street, String postalCode, String city);

    @Template("<a href='mailto:{0}'>{0}</a>")
    SafeHtml mailto(String email);
  }

  private static final Binder binder = GWT.create(Binder.class);
  private final Widget widget;

  private ExpenseDetailUiHandlers presenter;
  @UiField
  Element reference;
  @UiField
  Element amount;
  @UiField
  Element toBePaidByOwner;
  @UiField
  Element toBePaidByTenant;
  @UiField
  Element dateWork;
  @UiField
  Element dateInform;
  @UiField
  Element typeWork;
  @UiField
  Element paidByTenant;
  @UiField
  Element datePaidByTenant;
  @UiField
  Element contractorTel;
  @UiField
  Element contractorGSM;
  @UiField
  Element contractorAccount;
  @UiField
  Element contractorName;
  @UiField
  Element dateInvoice;
  @UiField
  SimplePanel documentsPanel;
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
  public ExpenseDetailsView() {
    widget = binder.createAndBindUi(this);
  }

  @Override
  public void addDocumentWidget(Widget w) {
    documentsPanel.add(w);

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
  public void setEstate(RealEstate e) {
    Templates t = Templates.INSTANCE;
    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    this.estateRef.setInnerText(e.getReference());
    builder.append(t.address(e.getFullAddressLine(), e.getPostalCode(), e.getCity()));
    this.estateAddress.setInnerHTML(builder.toSafeHtml().asString());
    SimpleOwner o = e.getOwner();
    this.ownerName.setInnerText(o.getName());
    this.ownerTel.setInnerText(o.getPhoneNumber());
    this.ownerGSM.setInnerText(o.getMobileNumber());
    builder = new SafeHtmlBuilder();
    builder.append(t.mailto(o.getEmail()));
    this.ownerMail.setInnerHTML(builder.toSafeHtml().asString());
    builder = new SafeHtmlBuilder();
    builder.append(t.address(o.getAddress(), o.getPostalCode(), o.getCity()));
    this.ownerAddress.setInnerHTML(builder.toSafeHtml().asString());
  }

  @Override
  public void setExpense(Expense l) {
    // TODO improve and continue

    this.reference.setInnerText(l.getReference());
    this.amount.setInnerText(UiHelper.FloatToString(l.getAmount()));
    this.dateInvoice.setInnerText(UiHelper.displayDate(l.getDateInvoice()));
    this.datePaidByTenant.setInnerText(UiHelper.displayDate(l.getDatePaymentTenant()));
    this.dateInform.setInnerText(UiHelper.displayDate(l.getDateInform()));
    this.dateWork.setInnerText(UiHelper.displayDate(l.getDateWork()));
    this.toBePaidByOwner.setInnerText(UiHelper.FloatToString(l.getToBePaidByOwner()));
    this.toBePaidByTenant.setInnerText(UiHelper.FloatToString(l.getToBePaidByTenant()));
    this.paidByTenant.setInnerText((l.getIsPaidByTenant()!=null && l.getIsPaidByTenant().equals(true)) ? "oui" : "non");
    this.typeWork.setInnerText(l.getTypeOfWork());
    Contractor ctor = l.getContractor();
    if (ctor!=null){
      this.contractorName.setInnerText(l.getContractor().getName());
      this.contractorAccount.setInnerText(l.getContractor().getAccount());
      this.contractorTel.setInnerText(l.getContractor().getPhone());
      this.contractorGSM.setInnerText(l.getContractor().getMobile());
    }
  }

  @Override
  public void setPresenter(ExpenseDetailUiHandlers handler) {
    this.presenter = handler;
  }
}
