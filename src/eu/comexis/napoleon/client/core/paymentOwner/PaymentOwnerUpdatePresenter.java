package eu.comexis.napoleon.client.core.paymentOwner;

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
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.validation.PaymentValidator;

public class PaymentOwnerUpdatePresenter
    extends
    PaymentUpdatePresenter<PaymentOwner, PaymentOwnerUpdatePresenter.MyView, PaymentOwnerUpdatePresenter.MyProxy> {

  @ProxyCodeSplit
  @NameToken(NameTokens.updatePaymentOwner)
  public interface MyProxy extends ProxyPlace<PaymentOwnerUpdatePresenter> {
  }

  public interface MyView extends PaymentUpdatePresenter.MyView<PaymentOwner> {
  }

  @Inject
  public PaymentOwnerUpdatePresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  protected PaymentValidator<PaymentOwner> createValidator() {
    return new PaymentValidator<PaymentOwner>();
  }

  @Override
  protected PaymentOwner createNewDataModel() {
    return new PaymentOwner();
  }

  @Override
  protected void requestData(String id) {
    new GetPaymentCommand<PaymentOwner>(id, this.leaseId, this.estateId,PaymentOwner.class.toString())
        .dispatch(new GotPayment<PaymentOwner>() {
          @Override
          public void got(PaymentOwner payment) {
            if (payment!=null){
              setDataObjectModel(payment);
              getView().setData(payment);
            }else{
              PaymentOwnerUpdatePresenter.this.onButtonCancelClick();
            }
          }
        });
  }

  @Override
  protected void save() {
    new UpdatePaymentCommand<PaymentOwner>(getDataObjectModel(),PaymentOwner.class.toString())
        .dispatch(new UpdatedPayment<PaymentOwner>() {
          @Override
          public void updated(PaymentOwner payment) {
            if (payment != null) {
              PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentOwnerlist);
              myRequest = myRequest.with(UUID_PARAMETER, payment.getLeaseId());
              myRequest = myRequest.with(ESTATE_UUID_PARAMETER, payment.getEstateId());
              placeManager.revealPlace(myRequest);
            } else {
              getView().displayError("The payment cannot be save");
            }
          }
        });

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.paymentOwner;
  }

  @Override
  protected String getListNameTokens() {
    return NameTokens.paymentOwnerlist;
  }

  @Override
  protected Menus getMenu() {
    return Menus.LEASE;
  }
  
  @Override
  protected String getTitle() {
    return Literals.INSTANCE.paymentOwnerUpdateTitle();
  }
}
