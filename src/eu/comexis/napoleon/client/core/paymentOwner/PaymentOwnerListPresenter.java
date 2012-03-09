package eu.comexis.napoleon.client.core.paymentOwner;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllPayment;
import eu.comexis.napoleon.shared.command.payment.GetAllPaymentCommand;
import eu.comexis.napoleon.shared.model.PaymentOwner;

public class PaymentOwnerListPresenter extends
    AbstractListPresenter<PaymentOwner, PaymentOwnerListPresenter.MyView, PaymentOwnerListPresenter.MyProxy> {
  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";
  public static final String LEASE_UUID_PARAMETER = "lease_uuid";
  private String id;
  private String estateId;
  private static final Logger LOG = Logger.getLogger(PaymentOwnerListPresenter.class.getName());

  @ProxyCodeSplit
  @NameToken(NameTokens.paymentOwnerlist)
  public interface MyProxy extends ProxyPlace<PaymentOwnerListPresenter> {
  }

  public interface MyView extends AbstractListPresenter.MyView<PaymentOwner> {
  }

  @Inject
  public PaymentOwnerListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.paymentOwner;
  }

  @Override
  protected String getUpdateNameTokens() {
    return NameTokens.updatePaymentOwner;
  }
  @Override
  public void onButtonNewClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, "next");
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, estateId);
    myRequest = myRequest.with(LEASE_UUID_PARAMETER, id);
    super.getPlaceManager().revealPlace(myRequest);
  }

  protected void requestData() {
    new GetAllPaymentCommand<PaymentOwner>(id,estateId,PaymentOwner.class.toString()).dispatch(new GotAllPayment<PaymentOwner>() {
      @Override
      public void got(List<PaymentOwner> payments) {
        setDatas(payments);
      }
    });
  }
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);
    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);
    this.estateId = placeRequest.getParameter(ESTATE_UUID_PARAMETER, null);
    
    if (id == null || id.length() == 0 
        || estateId == null || estateId.length() ==0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      super.getPlaceManager().revealErrorPlace(placeRequest.getNameToken());
    }
  }
  @Override
  public void onSelect(PaymentOwner data) {
    if (data == null){
      return;
    }
    
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, data.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, estateId);
    myRequest = myRequest.with(LEASE_UUID_PARAMETER, id);
    super.getPlaceManager().revealPlace(myRequest);
  }

  @Override
  protected eu.comexis.napoleon.client.core.AbstractListPresenter.ListFilter<PaymentOwner> createFilter() {
    // TODO Auto-generated method stub
    return null;
  }
}
