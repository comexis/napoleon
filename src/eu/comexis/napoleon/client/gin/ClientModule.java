package eu.comexis.napoleon.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import eu.comexis.napoleon.client.core.DashBoardPresenter;
import eu.comexis.napoleon.client.core.DashBoardView;
import eu.comexis.napoleon.client.core.MainLayoutPresenter;
import eu.comexis.napoleon.client.core.MainLayoutView;
import eu.comexis.napoleon.client.place.ClientPlaceManager;
import eu.comexis.napoleon.client.place.DefaultPlace;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.core.OwnerListPresenter;
import eu.comexis.napoleon.client.core.OwnerListView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindConstant().annotatedWith(DefaultPlace.class).to(
				NameTokens.dashboard);


		bindPresenter(MainLayoutPresenter.class,
				MainLayoutPresenter.MyView.class, MainLayoutView.class,
				MainLayoutPresenter.MyProxy.class);

		bindPresenter(DashBoardPresenter.class,
				DashBoardPresenter.MyView.class, DashBoardView.class,
				DashBoardPresenter.MyProxy.class);

		bindPresenter(OwnerListPresenter.class,
				OwnerListPresenter.MyView.class, OwnerListView.class,
				OwnerListPresenter.MyProxy.class);
	}
}
