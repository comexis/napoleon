package eu.comexis.napoleon.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.inject.client.AsyncProvider;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.DashBoardPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerListPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerUpdatePresenter;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantListPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantUpdatePresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<MainLayoutPresenter> getMainLayoutPresenter();

	AsyncProvider<DashBoardPresenter> getDashBoardPresenter();

	AsyncProvider<OwnerListPresenter> getOwnerListPresenter();

	AsyncProvider<OwnerDetailsPresenter> getOwnerDetailsPresenter();
	
	AsyncProvider<OwnerUpdatePresenter> getOwnerUpdatePresenter();
	
	AsyncProvider<TenantListPresenter> getTenantListPresenter();

  AsyncProvider<TenantDetailsPresenter> getTenantDetailsPresenter();
  
  AsyncProvider<TenantUpdatePresenter> getTenantUpdatePresenter();
}