package eu.comexis.napoleon.client.core.lease;

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
import eu.comexis.napoleon.client.core.estate.RealEstateDetailsPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter;
import eu.comexis.napoleon.client.events.AddedFileEvent;
import eu.comexis.napoleon.client.events.AddedFileEvent.AddedFileHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotLease;
import eu.comexis.napoleon.client.widget.DocumentPanelPresenter;
import eu.comexis.napoleon.client.widget.DocumentPanelView;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseResponse;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.HasFiles;
import eu.comexis.napoleon.shared.model.Lease;

public class LeaseDetailsPresenter extends
    AbstractPresenter<LeaseDetailsPresenter.MyView, LeaseDetailsPresenter.MyProxy> implements
    LeaseDetailUiHandlers, AddedFileHandler {

  @ProxyCodeSplit
  @NameToken(NameTokens.lease)
  public interface MyProxy extends ProxyPlace<LeaseDetailsPresenter> {
  }
  public interface MyView extends View, HasPresenter<LeaseDetailUiHandlers>  {
    public void addDocumentWidget(Widget w);
    public void setLease(Lease l);
    public void bind();
    public void unbind();
  }

  public static final String UUID_PARAMETER = "uuid";
  public static final String ESTATE_UUID_PARAMETER = "estate_uuid";

  private static final Logger LOG = Logger.getLogger(LeaseDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private DocumentPanelPresenter filesPresenter;
  private String id;
  private String realEstateId;
  private Lease lease;

  @Inject
  public LeaseDetailsPresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }

  @Override
  public void onAddedFile(AddedFileEvent event) {
   HasFiles entity = event.getEntity();
   if (lease.getId() != null && lease.getId().equals(entity.getId())){
       saveFile(event.getFile());
   }
    
  }
  
  @Override
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.leaselist);
    placeManager.revealPlace(myRequest);
  }
  
  @Override
  public void onButtonPaymentClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentBoardlist);
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonPaymentOwnerClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentOwnerlist);
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonPaymentTenantClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.paymentTenantlist);
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }
  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.updateLease);
    // add the id of the realEstate to load
    myRequest = myRequest.with(UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getId());
    myRequest = myRequest.with(ESTATE_UUID_PARAMETER, LeaseDetailsPresenter.this.lease.getRealEstate().getId());
    placeManager.revealPlace(myRequest);
  }
  /**
   * Retrieve the id of the lease to show it
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
    return Menus.REAL_ESTATE;
  }

  protected String getTitle() {
    return Literals.INSTANCE.leaseDetailsTitle();
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
    
    getView().bind();
  }
  
  @Override
  protected void onUnbind() {
    super.onUnbind();
    
    getView().unbind();
  }
  
  
  @Override
  protected void onReset() {
    super.onReset();

    new GetLeaseCommand(id, realEstateId).dispatch(new GotLease() {

      @Override
      public void got(Lease lease) {
        setLease(lease);
      }
    });

  }
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
  protected void saveFile(FileDescriptor file) {
    new UpdateLeaseCommand(getData()).dispatch(new AsyncCallback<UpdateLeaseResponse>() {
      
      @Override
      public void onSuccess(UpdateLeaseResponse result) {}
      
      @Override
      public void onFailure(Throwable caught) {
        //TODO improve that
        Window.alert("Impossible de lier le fichier à la location. Veuillez reessayer.");
        
      }
    });
  }
  protected Lease getData() {
    return lease;
  }
  
  protected void setLease(Lease lease) {
    this.lease = lease;
    filesPresenter.setDocumentHolder(lease);
    getView().setLease(lease);
    doReveal();
  }

  @Override
  public void showOwner() {
    OwnerDetailsPresenter.show(lease.getRealEstate().getOwnerId());
  }

  @Override
  public void showReference() {
    RealEstateDetailsPresenter.show(lease.getRealEstate().getId());
  }

  @Override
  public void showTenant() {
    TenantDetailsPresenter.show(lease.getTenant().getId());
    
  }

}
