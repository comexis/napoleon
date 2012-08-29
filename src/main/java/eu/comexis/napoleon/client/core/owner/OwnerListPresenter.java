package eu.comexis.napoleon.client.core.owner;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.rpc.callback.GotAllOwner;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerListPresenter extends
    AbstractListPresenter<SimpleOwner, OwnerListPresenter.MyView, OwnerListPresenter.MyProxy> {
  
  private static class OwnerListFilter implements ListFilter<SimpleOwner>{

    @Override
    public boolean filter(SimpleOwner owner, String filter) {
      return !owner.getName().toLowerCase().startsWith(filter.toLowerCase());
    }
    
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.ownerlist)
  public interface MyProxy extends ProxyPlace<OwnerListPresenter> {
  }

  public interface MyView extends AbstractListPresenter.MyView<SimpleOwner> {
  }

  @Inject
  public OwnerListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.owner;
  }

  @Override
  protected String getUpdateNameTokens() {
    return NameTokens.updateOwner;
  }


  protected void requestData() {
    new GetAllOwnerCommand().dispatch(new GotAllOwner() {
      @Override
      public void got(List<SimpleOwner> owners) {
        setDatas(owners);
      }
    });
  }
  
  @Override
  protected ListFilter<SimpleOwner> createFilter() {
    return new OwnerListFilter();
  }

  @Override
  protected Menus getMenu() {
    return Menus.OWNER;
  }

  @Override
  protected String getTitle() {
    return Literals.INSTANCE.ownerListTitle();
  }
  
  @Override
  protected boolean manageActive() {
    return true;
  }
}
