package eu.comexis.napoleon.client.core.paymentBoard;

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

import eu.comexis.napoleon.client.core.AbstractShortListPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.lease.LeaseDetailsPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotPaymentsBoard;
import eu.comexis.napoleon.shared.command.payment.GetPaymentsBoardCommand;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public class PaymentBoardListPresenter extends
    AbstractShortListPresenter<PaymentListItem, PaymentBoardListPresenter.MyView, PaymentBoardListPresenter.MyProxy> {
  
  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";
  public static final String LEASE_UUID_PARAMETER = "lease_uuid";
  private static final Logger LOG = Logger.getLogger(PaymentBoardListPresenter.class.getName());
  
  private String id;
  private String estateId;
  

  @ProxyCodeSplit
  @NameToken(NameTokens.paymentBoardlist)
  public interface MyProxy extends ProxyPlace<PaymentBoardListPresenter> {
  }

  public interface MyView extends AbstractShortListPresenter.MyView<PaymentListItem> {
  }

  @Inject
  public PaymentBoardListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);

  }

  protected void requestData() {
    new GetPaymentsBoardCommand(id,estateId).dispatch(new GotPaymentsBoard() {
      @Override
      public void got(String title,List<PaymentListItem> payments) {
        setDatas(title,payments);
      }
    });
    this.getView().hideButtons(false);
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
  protected Menus getMenu() {
    return Menus.REAL_ESTATE;
  }

  @Override
  protected String getTitle() {
    return Literals.INSTANCE.paymentListTitle();
  }

  @Override
  protected String getDetailsNameTokens() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public void showParent() {
    LeaseDetailsPresenter.show(id, estateId);
  }

  @Override
  protected String getUpdateNameTokens() {
    // TODO Auto-generated method stub
    return null;
  }
}
