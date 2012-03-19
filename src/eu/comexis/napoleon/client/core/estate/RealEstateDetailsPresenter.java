package eu.comexis.napoleon.client.core.estate;

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

import eu.comexis.napoleon.client.Napoleon;
import eu.comexis.napoleon.client.core.AbstractPresenter;
import eu.comexis.napoleon.client.core.HasPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter;
import eu.comexis.napoleon.client.events.AddedFileEvent;
import eu.comexis.napoleon.client.events.AddedFileEvent.AddedFileHandler;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotRealEstate;
import eu.comexis.napoleon.client.widget.DocumentPanelPresenter;
import eu.comexis.napoleon.client.widget.DocumentPanelView;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateResponse;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.HasFiles;
import eu.comexis.napoleon.shared.model.RealEstate;

public class RealEstateDetailsPresenter extends
    AbstractPresenter<RealEstateDetailsPresenter.MyView, RealEstateDetailsPresenter.MyProxy> implements
    RealEstateDetailUiHandlers, AddedFileHandler {

  @ProxyCodeSplit
  @NameToken(NameTokens.realEstate)
  public interface MyProxy extends ProxyPlace<RealEstateDetailsPresenter> {
  }
  public interface MyView extends View, HasPresenter<RealEstateDetailUiHandlers> {
    public void addDocumentWidget(Widget w);
    public void setRealEstate(RealEstate e);
    public void bind();
    public void unbind();
  }
  
  public static void show(String realEstateId) {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstate);
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, realEstateId);
    
    Napoleon.ginjector.getPlaceManager().revealPlace(myRequest);
    
  }

  public static final String UUID_PARAMETER = "uuid";

  private static final Logger LOG = Logger.getLogger(RealEstateDetailsPresenter.class.getName());

  private PlaceManager placeManager;
  private String id;
  private DocumentPanelPresenter filesPresenter;
  private RealEstate realEstate;

  @Inject
  public RealEstateDetailsPresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }
  @Override
  public void onAddedFile(AddedFileEvent event) {
   HasFiles entity = event.getEntity();
   if (realEstate.getId() != null && realEstate.getId().equals(entity.getId())){
       saveFile(event.getFile());
   }
    
  }
  @Override
  public void onButtonRentClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.leaselist);
    myRequest = myRequest.with(UUID_PARAMETER, RealEstateDetailsPresenter.this.realEstate.getId());
    placeManager.revealPlace(myRequest);
  }
  
  @Override
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.realEstatelist);
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(NameTokens.updateRealEstate);
    // add the id of the realEstate to load
    myRequest = myRequest.with(UUID_PARAMETER, RealEstateDetailsPresenter.this.realEstate.getId());
    placeManager.revealPlace(myRequest);
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

    if (id == null || id.length() == 0) {
      if (LogConfiguration.loggingIsEnabled()) {
        LOG.severe("invalid id is null or empty");
      }
      placeManager.revealErrorPlace(placeRequest.getNameToken());
    }
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

    new GetRealEstateCommand(id).dispatch(new GotRealEstate() {

      @Override
      public void got(RealEstate realEstate) {
        setRealEstate(realEstate);
      }
    });

  }
 
  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }
  protected void saveFile(FileDescriptor file) {
    new UpdateRealEstateCommand(getData()).dispatch(new AsyncCallback<UpdateRealEstateResponse>() {
      
      @Override
      public void onSuccess(UpdateRealEstateResponse result) {}
      
      @Override
      public void onFailure(Throwable caught) {
        //TODO improve that
        Window.alert("Impossible de lier le fichier à la location. Veuillez reessayer.");
        
      }
    });
  }
  protected RealEstate getData() {
    return realEstate;
  }
  protected void setRealEstate(RealEstate realEstate) {
    this.realEstate = realEstate;
    filesPresenter.setDocumentHolder(realEstate);
    getView().setRealEstate(realEstate);
    
    doReveal();
  }
  @Override
  protected Menus getMenu() {
    return Menus.REAL_ESTATE;
  }

  @Override
  protected String getTitle() {
    return Literals.INSTANCE.realEstateDetailsTitle();
  }
  
  @Override
  public void showOwner() {
    OwnerDetailsPresenter.show(realEstate.getOwner().getId());
  }

}
