package eu.comexis.napoleon.client.core.lease;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Lease;

public class LeaseDetailsView extends ViewImpl implements LeaseDetailsPresenter.MyView {

  public interface Binder extends UiBinder<Widget, LeaseDetailsView> {
  }

  private static final Binder binder = GWT.create(Binder.class);
  private final Widget widget;
  private LeaseDetailUiHandlers presenter;

  @UiField
  Element reference;
  @UiField
  Element academicYear;
  @UiField
  Element tenantName;
  @UiField
  Element ownerName;
  @UiField
  Element startDate;
  @UiField
  Element endDate;
  @UiField
  Element eleDate;
  @UiField
  Element elsDate;
  @UiField
  Element depositDate;
  @UiField
  Element depositType;
  @UiField
  Element iban;
  @UiField
  Element bic;
  @UiField
  Element deposit;
  @UiField
  Element fee;
  @UiField
  Element feeOwner;
  @UiField
  Element rent;
  @UiField
  Element charges;
  @UiField
  Element type;
  @UiField
  Element bookkeepingRef;
  @UiField
  Element hasFurnituresRental;
  @UiField
  Element hasFurnituresWithContract;
  @UiField
  Element furnituresPayment;
  @UiField
  Element furnituresDate;
  @UiField
  Element furnituresAmount;
  @UiField
  Element coocuppant;
  @UiField
  SimplePanel documentsPanel;

  @Inject
  public LeaseDetailsView() {
    widget = binder.createAndBindUi(this);
  }

  public void bind() {
    $(reference).click(new Function() {
      @Override
      public void f() {
        presenter.showReference();
      }
    });
    
    $(tenantName).click(new Function() {
      @Override
      public void f() {
        presenter.showTenant();
      }
    });
    
    $(ownerName).click(new Function() {
      @Override
      public void f() {
        presenter.showOwner();
      }
    });
    
  }
  
  public void unbind(){
    $(reference).unbind(Event.ONCLICK);
    $(tenantName).unbind(Event.ONCLICK);
    $(ownerName).unbind(Event.ONCLICK);
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

  @UiHandler("btnPayment")
  public void onPaymentClicked(ClickEvent e) {
    presenter.onButtonPaymentClick();
  }

  @UiHandler("btnPaymentOwner")
  public void onPaymentOwnerClicked(ClickEvent e) {
    presenter.onButtonPaymentOwnerClick();
  }

  @UiHandler("btnPaymentTenant")
  public void onPaymentTenantClicked(ClickEvent e) {
    presenter.onButtonPaymentTenantClick();
  }

  @Override
  public void setLease(Lease l) {
    if (l == null){
      return;
    }
    // TODO improve and continue

    this.reference.setInnerText(l.getRealEstate().getReference());
    this.academicYear.setInnerText(l.getAcademicYear());
    this.coocuppant.setInnerHTML(l.getCooccupant().replace("\n", "<br/>"));
    this.startDate.setInnerText(UiHelper.displayDate(l.getStartDate()));
    this.endDate.setInnerText(UiHelper.displayDate(l.getEndDate()));
    this.tenantName.setInnerText(l.getTenant().getName());
    this.ownerName.setInnerText(l.getRealEstate().getOwner());
    this.type.setInnerText(UiHelper.translateEnum("TypeOfRent_", l.getType()));
    this.fee.setInnerText(UiHelper.FloatToString(l.getFee()));
    float rent = l.getRent() != null ? l.getRent() : 0;
    float fee = l.getFee() != null ? l.getFee() : 0;
    float charges = l.getServiceCharges() != null ? l.getServiceCharges() : 0;
    this.feeOwner.setInnerText(UiHelper.FloatToString(rent + charges - fee));
    this.charges.setInnerText(UiHelper.FloatToString(l.getServiceCharges()));
    this.deposit.setInnerText(UiHelper.FloatToString(l.getSecurityDeposit()));
    this.rent.setInnerText(UiHelper.FloatToString(l.getRent()));
    this.eleDate.setInnerText(UiHelper.displayDate(l.getEleDate()));
    this.elsDate.setInnerText(UiHelper.displayDate(l.getElsDate()));
    this.depositDate.setInnerText(UiHelper.displayDate(l.getDepositDate()));
    this.furnituresPayment.setInnerText((l.getFurnituresPaymentOK() != null && l
        .getFurnituresPaymentOK().equals(true)) ? "Oui" : "Non");
    this.hasFurnituresWithContract.setInnerText((l.getHasFurnituresWithContract() != null && l
        .getHasFurnituresWithContract().equals(true)) ? "Oui" : "Non");
    this.hasFurnituresRental.setInnerText((l.getHasFurnituresRental() != null && l
        .getHasFurnituresRental().equals(true)) ? "Oui" : "Non");    
    this.bookkeepingRef.setInnerText(l.getBookkeepingReference());
    if(l.getDepositInCash() != null && l.getDepositInCash().equals(true)){
     this.depositType.setInnerText("Cash");
     this.iban.setInnerText("");
	 this.bic.setInnerText("");
    } else {
    	if(l.getDepositAgency()!= null && l.getDepositAgency().equals(true)) {
    		this.depositType.setInnerText("Compte agence");
    		this.iban.setInnerText(l.getIban());
    	    this.bic.setInnerText(l.getBic());
    	} else {
    		this.depositType.setInnerText("Compte bloqué");
    		this.iban.setInnerText(l.getIbanOwner());
    	    this.bic.setInnerText(l.getBicOwner());
    	}    	
    }
    
    this.furnituresAmount.setInnerText(UiHelper.FloatToString(l.getFurnituresAnnualAmount()));
    this.furnituresDate.setInnerText(UiHelper.displayDate(l.getFurnituresDate()));
  }

  @Override
  public void setPresenter(LeaseDetailUiHandlers handler) {
    this.presenter = handler;
  }

  @Override
  public void addDocumentWidget(Widget w) {
    documentsPanel.add(w);

  }
  

}
