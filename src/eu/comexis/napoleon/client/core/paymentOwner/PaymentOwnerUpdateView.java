package eu.comexis.napoleon.client.core.paymentOwner;

import java.util.logging.Logger;

import com.google.inject.Inject;

import eu.comexis.napoleon.client.core.payment.PaymentUpdateView;
import eu.comexis.napoleon.shared.model.PaymentOwner;

public class PaymentOwnerUpdateView extends PaymentUpdateView<PaymentOwner> implements PaymentOwnerUpdatePresenter.MyView {
  
  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(PaymentOwnerUpdateView.class.getName());

 

  @Inject
  public PaymentOwnerUpdateView() {
    super();
  }

 
}