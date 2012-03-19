package eu.comexis.napoleon.client.core.party;

import static eu.comexis.napoleon.client.Napoleon.ginjector;
import static eu.comexis.napoleon.client.core.party.PartyUpdatePresenter.UUID_PARAMETER;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import eu.comexis.napoleon.client.core.AbstractPresenter;
import eu.comexis.napoleon.client.core.HasPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.events.AddedFileEvent;
import eu.comexis.napoleon.client.events.AddedFileEvent.AddedFileHandler;
import eu.comexis.napoleon.client.widget.DocumentPanelPresenter;
import eu.comexis.napoleon.client.widget.DocumentPanelView;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.HasFiles;
import eu.comexis.napoleon.shared.model.Party;

public abstract class PartyDetailsPresenter<T extends Party, V extends PartyDetailsPresenter.MyView<T>, P extends Proxy<?>>
    extends AbstractPresenter<V, P> implements PartyDetailsUiHandlers, AddedFileHandler {

  public interface MyView<T extends Party> extends View, HasPresenter<PartyDetailsUiHandlers> {
    public void setData(T p);
    public void addDocumentWidget(Widget w);
  }

  private static final Logger LOG = Logger.getLogger(PartyDetailsPresenter.class.getName());

  private String id;
  private T party;
  private PlaceManager placeManager;
  private DocumentPanelPresenter filesPresenter;

  @Inject
  public PartyDetailsPresenter(final EventBus eventBus, final V view, final P proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
  }

  protected T getData() {
    return party;
  }

  protected abstract String getListNameTokens();

  public PlaceManager getPlaceManager() {
    return placeManager;
  }

  protected abstract String getUpdateDetailsNameTokens();

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
  public void onButtonBackToListClick() {
    PlaceRequest myRequest = new PlaceRequest(getListNameTokens());
    placeManager.revealPlace(myRequest);
  }

  @Override
  public void onButtonUpdateClick() {
    PlaceRequest myRequest = new PlaceRequest(getUpdateDetailsNameTokens());
    // add the id of the owner to load
    myRequest = myRequest.with(UUID_PARAMETER, party.getId());
    placeManager.revealPlace(myRequest);
  }

  @Override
  protected void onReset() {
    super.onReset();
    requestData(id);
  }

  /**
   * Retrieve the id of the owner to show it
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

  protected abstract void requestData(String id);
  protected abstract void saveFile(FileDescriptor file);

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainLayoutPresenter.MAIN_CONTENT, this);
  }

  protected void setData(T party) {
    this.party = party;
    filesPresenter.setDocumentHolder(party);
    getView().setData(party);
    doReveal();
  }
  
  @Override
  public void onAddedFile(AddedFileEvent event) {
   HasFiles entity = event.getEntity();
   if (party.getId() != null && party.getId().equals(entity.getId())){
       saveFile(event.getFile());
   }
    
  }

 
}
