package eu.comexis.napoleon.client.core.owner;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.core.party.PartyUpdatePresenter;
import eu.comexis.napoleon.client.events.SelectedMenuEvent;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotOwner;
import eu.comexis.napoleon.client.rpc.callback.UpdatedOwner;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerCommand;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.validation.OwnerValidator;
import eu.comexis.napoleon.shared.validation.PartyValidator;

public class OwnerUpdatePresenter extends
    PartyUpdatePresenter<Owner, OwnerUpdatePresenter.MyView, OwnerUpdatePresenter.MyProxy>{

  @ProxyCodeSplit
  @NameToken(NameTokens.updateOwner)
  public interface MyProxy extends ProxyPlace<OwnerUpdatePresenter> {
  }
  
  public interface MyView extends PartyUpdatePresenter.MyView<Owner>{ 
  }


  @Inject
  public OwnerUpdatePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);
  }


  @Override
  protected PartyValidator<Owner> createValidator() {
   return new OwnerValidator();
  }

  @Override
  protected Owner createNewDataModel() {
   return new Owner();
  }

  @Override
  protected void requestData(String id) {
    new GetOwnerCommand(id).dispatch(new GotOwner() {
      @Override
      public void got(Owner owner) {
        setDataObjectModel(owner);
        getView().setData(owner);
      }
    });
  }

  
  @Override
  protected void onReveal() {
    super.onReveal();
    SelectedMenuEvent.fire(getEventBus(), Menus.OWNER);
  }
  
  @Override
  protected void save() {
    new UpdateOwnerCommand(getDataObjectModel()).dispatch(new UpdatedOwner() {
      @Override
      public void updated(Owner owner) {
        if (owner != null) {
          PlaceRequest myRequest = new PlaceRequest(NameTokens.owner);
          myRequest = myRequest.with(UUID_PARAMETER, owner.getId());
          placeManager.revealPlace(myRequest);
        } else {
          getView().displayError("The owner cannot be save");
        }
      }
    });
    
  }


  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.owner;
  }


  @Override
  protected String getListNameTokens() {
    return NameTokens.ownerlist;
  }
}
