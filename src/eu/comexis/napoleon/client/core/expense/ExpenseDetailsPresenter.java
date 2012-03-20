package eu.comexis.napoleon.client.core.expense;

import static eu.comexis.napoleon.client.Napoleon.ginjector;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.AbstractPresenter;
import eu.comexis.napoleon.client.core.HasPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.events.AddedFileEvent;
import eu.comexis.napoleon.client.events.AddedFileEvent.AddedFileHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotExpense;
import eu.comexis.napoleon.client.widget.DocumentPanelPresenter;
import eu.comexis.napoleon.client.widget.DocumentPanelView;
import eu.comexis.napoleon.shared.command.expense.GetExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseCommand;
import eu.comexis.napoleon.shared.command.expense.UpdateExpenseResponse;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.HasFiles;

public class ExpenseDetailsPresenter extends
    AbstractPresenter<ExpenseDetailsPresenter.MyView, ExpenseDetailsPresenter.MyProxy> implements
    ExpenseDetailUiHandlers, AddedFileHandler {

  @ProxyCodeSplit
  @NameToken(NameTokens.expense)
  public interface MyProxy extends ProxyPlace<ExpenseDetailsPresenter> {
  }
  public interface MyView extends View, HasPresenter<ExpenseDetailUiHandlers>  {
    public void addDocumentWidget(Widget w);
    public void setExpense(Expense l);
  }

  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";

  private static final Logger LOG = Logger.getLogger(ExpenseDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private DocumentPanelPresenter filesPresenter;
  private String id;
  private String realEstateId;
  private Expense expense;

  @Inject
  public ExpenseDetailsPresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }

  @Override
  public void onAddedFile(AddedFileEvent event) {
   HasFiles entity = event.getEntity();
   if (expense.getId() != null && expense.getId().equals(entity.getId())){
       saveFile(event.getFile());
   }
    
  }
  
  @Override
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.expenselist);
    myRequest = myRequest.with(UUID_PARAMETER, ExpenseDetailsPresenter.this.realEstateId);
    placeManager.revealPlace(myRequest);
  }
  
  
  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.updateExpense);
    // add the id of the realEstate to load
    myRequest = myRequest.with(UUID_PARAMETER, ExpenseDetailsPresenter.this.expense.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, ExpenseDetailsPresenter.this.realEstateId);
    placeManager.revealPlace(myRequest);
  }
  /**
   * Retrieve the id of the expense to show it
   */
  @Override
  public void prepareFromRequest(PlaceRequest placeRequest) {
    super.prepareFromRequest(placeRequest);

    // In the next call, "view" is the default value,
    // returned if "action" is not found on the URL.
    id = placeRequest.getParameter(UUID_PARAMETER, null);
    realEstateId = placeRequest.getParameter(ESTATE_UUID_PARAMETER, null);

    if (id == null || id.length() == 0 || realEstateId == null || realEstateId.length()== 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id: null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }
  }

  @Override
  protected Menus getMenu() {
    return Menus.LEASE;
  }

  protected String getTitle() {
    return Literals.INSTANCE.expenseDetailsTitle();
  }

  @Override
  protected void onBind() {
    super.onBind();
    
    getEventBus().addHandler(AddedFileEvent.getType(), this);
    
    getView().setPresenter(this);
    
    
    //TODO replace that
    //filesPresenter = ginjector.getDocumentPanelPresenter().get();
    DocumentPanelView.Binder binder = GWT.create(DocumentPanelView.Binder.class);
    DocumentPanelView view = new DocumentPanelView(binder);
    filesPresenter = new DocumentPanelPresenter(ginjector.getEventBus(), view);
    filesPresenter.bind();
    
    getView().addDocumentWidget(filesPresenter.getWidget());
  }
  @Override
  protected void onReset() {
    super.onReset();

    new GetExpenseCommand(id, realEstateId).dispatch(new GotExpense() {

      @Override
      public void got(Expense expense) {
        setExpense(expense);
        getView().setExpense(expense);
        doReveal();
      }
    });

  }
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
  protected void saveFile(FileDescriptor file) {
    new UpdateExpenseCommand(getData()).dispatch(new AsyncCallback<UpdateExpenseResponse>() {
      
      @Override
      public void onSuccess(UpdateExpenseResponse result) {}
      
      @Override
      public void onFailure(Throwable caught) {
        //TODO improve that
        Window.alert("Impossible de lier le fichier à la location. Veuillez reessayer.");
        
      }
    });
  }
  protected Expense getData() {
    return expense;
  }
  
  protected void setExpense(Expense expense) {
    this.expense = expense;
    filesPresenter.setDocumentHolder(expense);
  }

}
