package eu.comexis.napoleon.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import eu.comexis.napoleon.client.core.DashBoardPresenter;
import eu.comexis.napoleon.client.core.DashBoardView;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.MainLayoutView;
import eu.comexis.napoleon.client.core.estate.RealEstateDetailsPresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateDetailsView;
import eu.comexis.napoleon.client.core.estate.RealEstateListPresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateListView;
import eu.comexis.napoleon.client.core.estate.RealEstateUpdatePresenter;
import eu.comexis.napoleon.client.core.estate.RealEstateUpdateView;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsView;
import eu.comexis.napoleon.client.core.owner.OwnerListPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerListView;
import eu.comexis.napoleon.client.core.owner.OwnerUpdatePresenter;
import eu.comexis.napoleon.client.core.owner.OwnerUpdateView;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsView;
import eu.comexis.napoleon.client.core.tenant.TenantListPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantListView;
import eu.comexis.napoleon.client.core.tenant.TenantUpdatePresenter;
import eu.comexis.napoleon.client.core.tenant.TenantUpdateView;
import eu.comexis.napoleon.client.place.ClientPlaceManager;
import eu.comexis.napoleon.client.place.DefaultPlace;
import eu.comexis.napoleon.client.place.NameTokens;

public class ClientModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    install(new DefaultModule(ClientPlaceManager.class));

    bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.dashboard);

    bindPresenter(MainLayoutPresenter.class, MainLayoutPresenter.MyView.class,
        MainLayoutView.class, MainLayoutPresenter.MyProxy.class);

    bindPresenter(DashBoardPresenter.class, DashBoardPresenter.MyView.class, DashBoardView.class,
        DashBoardPresenter.MyProxy.class);

    bindPresenter(OwnerListPresenter.class, OwnerListPresenter.MyView.class, OwnerListView.class,
        OwnerListPresenter.MyProxy.class);

    bindPresenter(OwnerDetailsPresenter.class, OwnerDetailsPresenter.MyView.class,
        OwnerDetailsView.class, OwnerDetailsPresenter.MyProxy.class);

    bindPresenter(OwnerUpdatePresenter.class, OwnerUpdatePresenter.MyView.class,
        OwnerUpdateView.class, OwnerUpdatePresenter.MyProxy.class);

    bindPresenter(TenantListPresenter.class, TenantListPresenter.MyView.class,
        TenantListView.class, TenantListPresenter.MyProxy.class);

    bindPresenter(TenantDetailsPresenter.class, TenantDetailsPresenter.MyView.class,
        TenantDetailsView.class, TenantDetailsPresenter.MyProxy.class);

    bindPresenter(TenantUpdatePresenter.class, TenantUpdatePresenter.MyView.class,
        TenantUpdateView.class, TenantUpdatePresenter.MyProxy.class);

    bindPresenter(RealEstateListPresenter.class, RealEstateListPresenter.MyView.class,
        RealEstateListView.class, RealEstateListPresenter.MyProxy.class);

    bindPresenter(RealEstateDetailsPresenter.class, RealEstateDetailsPresenter.MyView.class,
        RealEstateDetailsView.class, RealEstateDetailsPresenter.MyProxy.class);

    bindPresenter(RealEstateUpdatePresenter.class, RealEstateUpdatePresenter.MyView.class,
        RealEstateUpdateView.class, RealEstateUpdatePresenter.MyProxy.class);
  }
}
