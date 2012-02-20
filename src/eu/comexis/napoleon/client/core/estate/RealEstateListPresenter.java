package eu.comexis.napoleon.client.core.estate;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import eu.comexis.napoleon.client.core.AbstractListPresenter;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.rpc.callback.GotAllRealEstate;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateListPresenter
    extends
    AbstractListPresenter<SimpleRealEstate, RealEstateListPresenter.MyView, RealEstateListPresenter.MyProxy> {

  private static class RealEstateFilter implements ListFilter<SimpleRealEstate>{

    @Override
    public boolean filter(SimpleRealEstate estate, String filter) {
      return !estate.getReference().toLowerCase().startsWith(filter.toLowerCase());
    }
    
  }
  
  @ProxyCodeSplit
  @NameToken(NameTokens.realEstatelist)
  public interface MyProxy extends ProxyPlace<RealEstateListPresenter> {
  }

  public interface MyView extends AbstractListPresenter.MyView<SimpleRealEstate> {
  }

  @Inject
  public RealEstateListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final PlaceManager placeManager) {
    super(eventBus, view, proxy, placeManager);

  }

  @Override
  protected String getDetailsNameTokens() {
    return NameTokens.realEstate;
  }

  @Override
  protected String getUpdateNameTokens() {
    return NameTokens.updateRealEstate;
  }

  @Override
  protected void onReset() {
    super.onReset();

  }

  @Override
  protected void requestData() {
    new GetAllRealEstateCommand().dispatch(new GotAllRealEstate() {
      @Override
      public void got(List<SimpleRealEstate> realEstates) {
        setDatas(realEstates);
      }
    });

  }
  
  @Override
  protected ListFilter<SimpleRealEstate> createFilter() {
    return new RealEstateFilter();
  }
  
  

}
