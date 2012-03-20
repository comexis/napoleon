package eu.comexis.napoleon.client.core.expense;

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
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotAllExpense;
import eu.comexis.napoleon.shared.command.expense.GetAllExpenseCommand;
import eu.comexis.napoleon.shared.model.Expense;

public class ExpenseListPresenter extends
    AbstractShortListPresenter<Expense, ExpenseListPresenter.MyView, ExpenseListPresenter.MyProxy> {
  private String estateId;
  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";
  private static final Logger LOG = Logger.getLogger(ExpenseListPresenter.class.getName());
  
  @ProxyCodeSplit
  @NameToken(NameTokens.expenselist)
  public interface MyProxy extends ProxyPlace<ExpenseListPresenter> {
  }

  public interface MyView extends AbstractShortListPresenter.MyView<Expense> {
  }

  @Inject
  public ExpenseListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.expense;
  }

  @Override
  protected String getUpdateNameTokens() {
    return NameTokens.updateExpense;
  }
  @Override
  public void onButtonNewClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateNameTokens());
    myRequest = myRequest.with(UUID_PARAMETER, "new");
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, estateId);
    super.getPlaceManager().revealPlace(myRequest);
  }
  
  @Override
  public void onButtonDeleteClick() {
    
  }
  
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);
    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    estateId = placeRequest.getParameter(UUID_PARAMETER, null);
    
    if (estateId == null || estateId.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      super.getPlaceManager().revealErrorPlace(placeRequest.getNameToken());
    }
  }
  protected void requestData() {
    new GetAllExpenseCommand(estateId).dispatch(new GotAllExpense() {
      @Override
      public void got(List<Expense> expenses) {
        setDatas(expenses);
        doReveal();
      }
    });
  }
  
  @Override
  public void onSelect(Expense data) {
    if (data == null){
      return;
    }
    
    PlaceRequest myRequest = new PlaceRequest(getDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, data.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, data.getRealEstateId());
    super.getPlaceManager().revealPlace(myRequest);
  }
  

  @Override
  protected Menus getMenu() {
    return Menus.LEASE;
  }

  @Override
  protected String getTitle() {
    return Literals.INSTANCE.expenseListTitle();
  }
  
  
}
