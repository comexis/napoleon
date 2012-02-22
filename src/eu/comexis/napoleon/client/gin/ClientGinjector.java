package eu.comexis.napoleon.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import eu.comexis.napoleon.client.core.DashBoardPresenter;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateDetailsPresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateListPresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateUpdatePresenter;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerListPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerUpdatePresenter;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantListPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantUpdatePresenter;
import eu.comexis.napoleon.client.widget.DocumentPanelPresenter;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface ClientGinjector extends Ginjector {

  AsyncProvider<DashBoardPresenter> getDashBoardPresenter();

  EventBus getEventBus();

  AsyncProvider<MainLayoutPresenter> getMainLayoutPresenter();

  AsyncProvider<OwnerDetailsPresenter> getOwnerDetailsPresenter();

  AsyncProvider<OwnerListPresenter> getOwnerListPresenter();

  AsyncProvider<OwnerUpdatePresenter> getOwnerUpdatePresenter();

  PlaceManager getPlaceManager();

  AsyncProvider<RealEstateDetailsPresenter> getRealEstateDetailsPresenter();

  AsyncProvider<RealEstateListPresenter> getRealEstateListPresenter();

  AsyncProvider<RealEstateUpdatePresenter> getRealEstateUpdatePresenter();

  AsyncProvider<TenantDetailsPresenter> getTenantDetailsPresenter();

  AsyncProvider<TenantListPresenter> getTenantListPresenter();

  AsyncProvider<TenantUpdatePresenter> getTenantUpdatePresenter();
  
  Provider<DocumentPanelPresenter> getDocumentPanelPresenter();
}