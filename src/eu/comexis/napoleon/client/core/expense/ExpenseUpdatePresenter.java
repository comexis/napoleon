package eu.comexis.napoleon.client.core.expense;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.AbstractPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.expense.ExpenseUpdateUiHandlers.HasExpenseUpdateUiHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotAllContractor;
import eu.comexis.napoleon.client.rpc.callback.GotAllSuggest;
import eu.comexis.napoleon.client.rpc.callback.GotAllTenant;
import eu.comexis.napoleon.client.rpc.callback.GotExpense;
import eu.comexis.napoleon.client.rpc.callback.UpdatedExpense;
import eu.comexis.napoleon.shared.command.contractor.GetAllContractorCommand;
import eu.comexis.napoleon.shared.command.expense.GetExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseCommand;
import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.model.Contractor;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;
import eu.comexis.napoleon.shared.validation.ExpenseValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;
public class ExpenseUpdatePresenter extends
    AbstractPresenter<ExpenseUpdatePresenter.MyView, ExpenseUpdatePresenter.MyProxy> implements
    ExpenseUpdateUiHandlers {

  @ProxyCodeSplit
  @NameToken(NameTokens.updateExpense)
  public interface MyProxy extends ProxyPlace<ExpenseUpdatePresenter> {
  }
  public interface MyView extends View, HasExpenseUpdateUiHandler {
    public void displayError(String error);

    public void displayValidationMessage(List<ValidationMessage> validationMessages);

    public void reset();

    public void setExpense(Expense l);

    public Expense updateExpense(Expense l);
    
    public void fillTypeWorkList(List<String> works);
    
    public void fillContractorList(List<Contractor> contractors);
    
    public void setEstate(RealEstate e);
    
  }

  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";

  private static final Logger LOG = Logger.getLogger(ExpenseDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private String realEstateId;
  private Expense expense;
  private ExpenseValidator validator;
  
  
  @Inject
  public ExpenseUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.validator = new ExpenseValidator();
  }
  
  private void goToDetails() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.expense);
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, expense.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, realEstateId);
    placeManager.revealPlace(myRequest);
  }

  private void goToList() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.expenselist);
    myRequest = myRequest.with(UUID_PARAMETER, realEstateId);
    placeManager.revealPlace(myRequest);

  }
  @Override
  public void onButtonCancelClick() {
    if (expense == null || expense.getId() == null || expense.getId().length() == 0){
      goToList();
    }else {
      goToDetails();
    }
  }
  
  @Override
  public void onButtonSaveClick() {
    getView().updateExpense(expense);

    List<ValidationMessage> validationMessages = validator.validate(expense);
    
    if (validationMessages.isEmpty()) {
      saveExpense();
    } else {
      getView().displayValidationMessage(validationMessages);
    }
    
  }

  /**
   * Retrieve the id of the realEstate to show it
   */
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);
    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);
    realEstateId = placeRequest.getParameter(ESTATE_UUID_PARAMETER, null);

    if (id == null || id.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }

  }

  public void saveExpense() {
    // Save it
    UpdateExpenseCommand cmd = new UpdateExpenseCommand();
    cmd.setExpense(expense);
    cmd.dispatch(new UpdatedExpense() {
      @Override
      public void got(Expense expense) {
        if (expense != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.expense);
          myRequest = myRequest.with(UUID_PARAMETER, expense.getId());
          myRequest = myRequest.with(ESTATE_UUID_PARAMETER, ExpenseUpdatePresenter.this.realEstateId);
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The expense cannot be saved");
        }
      }
    });

  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().setExpenseUpdateUiHandler(this);
    init();
  }

  @Override
  protected void onHide() {
    super.onHide();
    getView().reset();
  }

  @Override
  protected void onReset() {
    super.onReset();
    if (id != null) { // call the server to get the requested owner
      new GetExpenseCommand(id,realEstateId).dispatch(new GotExpense() {
        @Override
        public void got(Expense expense, RealEstate estate) {
          ExpenseUpdatePresenter.this.expense = expense;
          getView().setExpense(expense);
          getView().setEstate(estate);
          doReveal();
        }
      });
    }
  }
  
  
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  private void init() {
    new GetAllSuggestCommand("TypeOfWork").dispatch(new GotAllSuggest() {
      @Override
      public void got(List<String> suggests) {
        getView().fillTypeWorkList(suggests);
      }
    });
    new GetAllContractorCommand().dispatch(new GotAllContractor() {
      @Override
      public void got(List<Contractor> contractors) {
        getView().fillContractorList(contractors);
      }
    });
    
  }
  
  @Override
  protected Menus getMenu() {
    return Menus.REAL_ESTATE;
  }
  
  @Override
  protected String getTitle() {
    if ("new".equals(id)) {
      return Literals.INSTANCE.expenseNewTitle();
    }
    return Literals.INSTANCE.expenseUpdateTitle();
  }
}