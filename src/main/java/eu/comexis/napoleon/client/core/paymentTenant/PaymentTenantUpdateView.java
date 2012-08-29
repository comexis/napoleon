package eu.comexis.napoleon.client.core.paymentTenant;

import java.util.logging.Logger;

import com.google.inject.Inject;

import eu.comexis.napoleon.client.core.payment.PaymentUpdateView;
import eu.comexis.napoleon.shared.model.PaymentTenant;

public class PaymentTenantUpdateView extends PaymentUpdateView<PaymentTenant> implements PaymentTenantUpdatePresenter.MyView {
  
  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(PaymentTenantUpdateView.class.getName());

 

  @Inject
  public PaymentTenantUpdateView() {
    super();
  }

 
}