package eu.comexis.napoleon.client.core.paymentTenant;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.payment.PaymentUpdatePresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotPayment;
import eu.comexis.napoleon.client.rpc.callback.UpdatedPayment;
import eu.comexis.napoleon.shared.command.payment.GetPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentCommand;
import eu.comexis.napoleon.shared.model.PaymentTenant;
import eu.comexis.napoleon.shared.validation.PaymentValidator;

public class PaymentTenantUpdatePresenter
    extends
    PaymentUpdatePresenter<PaymentTenant, PaymentTenantUpdatePresenter.MyView, PaymentTenantUpdatePresenter.MyProxy> {

  @ProxyCodeSplit
  @NameToken(NameTokens.updatePaymentTenant)
  public interface MyProxy extends ProxyPlace<PaymentTenantUpdatePresenter> {
  }

  public interface MyView extends PaymentUpdatePresenter.MyView<PaymentTenant> {
  }

  @Inject
  public PaymentTenantUpdatePresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  protected PaymentValidator<PaymentTenant> createValidator() {
    return new PaymentValidator<PaymentTenant>();
  }

  @Override
  protected PaymentTenant createNewDataModel() {
    return new PaymentTenant();
  }

  @Override
  protected void requestData(String id) {
    new GetPaymentCommand<PaymentTenant>(id, this.leaseId, this.estateId,PaymentTenant.class.toString())
        .dispatch(new GotPayment<PaymentTenant>() {
          @Override
          public void got(PaymentTenant payment,String msg) {
            if (payment!=null){
              setDataObjectModel(payment);
            }else{
              getView().displayError(msg);
              PaymentTenantUpdatePresenter.this.onButtonCancelClick();
            }
          }
        });
  }

  @Override
  protected void save() {
    new UpdatePaymentCommand<PaymentTenant>(getDataObjectModel(),PaymentTenant.class.toString())
        .dispatch(new UpdatedPayment<PaymentTenant>() {
          @Override
          public void updated(PaymentTenant payment,String msg) {
            if (payment != null) {
              PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentTenantlist);
              myRequest = myRequest.with(UUID_PARAMETER, payment.getLeaseId());
              myRequest = myRequest.with(ESTATE_UUID_PARAMETER, payment.getEstateId());
              placeManager.revealPlace(myRequest);
            } else {
              getView().displayError("msg");
            }
          }
        });

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.paymentTenant;
  }

  @Override
  protected String getListNameTokens() {
    return NameTokens.paymentTenantlist;
  }
  
  @Override
  protected Menus getMenu() {
    return Menus.REAL_ESTATE;
  }
  
  @Override
  protected String getTitle() {
    return Literals.INSTANCE.paymentTenantUpdateTitle();
  }

}
