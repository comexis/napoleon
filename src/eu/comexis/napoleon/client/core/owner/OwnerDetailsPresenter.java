package eu.comexis.napoleon.client.core.owner;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.party.PartyDetailsPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotOwner;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerResponse;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.Owner;

public class OwnerDetailsPresenter
    extends
    PartyDetailsPresenter<Owner, OwnerDetailsPresenter.MyView , OwnerDetailsPresenter.MyProxy>{

  @ProxyCodeSplit
  @NameToken(NameTokens.owner)
  public interface MyProxy extends ProxyPlace<OwnerDetailsPresenter> {
  }
  
  public interface MyView extends PartyDetailsPresenter.MyView<Owner>{
    
  }

  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(OwnerDetailsPresenter.class.getName());

  @Inject
  public OwnerDetailsPresenter(final EventBus eventBus, final OwnerDetailsPresenter.MyView view,
      final MyProxy proxy, final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }

  @Override
  protected String getListNameTokens() {
    return NameTokens.ownerlist;
  }

  @Override
  protected String getUpdateDetailsNameTokens() {
    return NameTokens.updateOwner;
  }

  @Override
  protected void requestData(String id) {
    new GetOwnerCommand(id).dispatch(new GotOwner() {

      @Override
      public void got(Owner owner) {
        setData(owner);
      }
    });

  }

  @Override
  protected void saveFile(FileDescriptor file) {
    new UpdateOwnerCommand(getData()).dispatch(new AsyncCallback<UpdateOwnerResponse>() {
      
      @Override
      public void onSuccess(UpdateOwnerResponse result) {}
      
      @Override
      public void onFailure(Throwable caught) {
        //TODO improve that
        Window.alert("Impossible de lier le fichier au proprietaire. Veuillez reessayer.");
        
      }
    });

    
  }
  
  @Override
  protected Menus getMenu() {
    return Menus.OWNER;
  }
  
  @Override
  protected String getTitle() {
    return Literals.INSTANCE.ownerDetailsTitle();
  }

}
