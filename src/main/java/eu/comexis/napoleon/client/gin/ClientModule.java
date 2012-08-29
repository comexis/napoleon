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
import eu.comexis.napoleon.client.core.expense.ExpenseDetailsPresenter;
import eu.comexis.napoleon.client.core.expense.ExpenseDetailsView;
import eu.comexis.napoleon.client.core.expense.ExpenseListPresenter;
import eu.comexis.napoleon.client.core.expense.ExpenseListView;
import eu.comexis.napoleon.client.core.expense.ExpenseUpdatePresenter;
import eu.comexis.napoleon.client.core.expense.ExpenseUpdateView;
import eu.comexis.napoleon.client.core.lease.LeaseDetailsPresenter;
import eu.comexis.napoleon.client.core.lease.LeaseDetailsView;
import eu.comexis.napoleon.client.core.lease.LeaseListPresenter;
import eu.comexis.napoleon.client.core.lease.LeaseListView;
import eu.comexis.napoleon.client.core.lease.LeaseUpdatePresenter;
import eu.comexis.napoleon.client.core.lease.LeaseUpdateView;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerDetailsView;
import eu.comexis.napoleon.client.core.owner.OwnerListPresenter;
import eu.comexis.napoleon.client.core.owner.OwnerListView;
import eu.comexis.napoleon.client.core.owner.OwnerUpdatePresenter;
import eu.comexis.napoleon.client.core.owner.OwnerUpdateView;
import eu.comexis.napoleon.client.core.paymentBoard.PaymentBoardListPresenter;
import eu.comexis.napoleon.client.core.paymentBoard.PaymentBoardListView;
import eu.comexis.napoleon.client.core.paymentOwner.PaymentOwnerListPresenter;
import eu.comexis.napoleon.client.core.paymentOwner.PaymentOwnerListView;
import eu.comexis.napoleon.client.core.paymentOwner.PaymentOwnerUpdatePresenter;
import eu.comexis.napoleon.client.core.paymentOwner.PaymentOwnerUpdateView;
import eu.comexis.napoleon.client.core.paymentTenant.PaymentTenantListPresenter;
import eu.comexis.napoleon.client.core.paymentTenant.PaymentTenantListView;
import eu.comexis.napoleon.client.core.paymentTenant.PaymentTenantUpdatePresenter;
import eu.comexis.napoleon.client.core.paymentTenant.PaymentTenantUpdateView;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantDetailsView;
import eu.comexis.napoleon.client.core.tenant.TenantListPresenter;
import eu.comexis.napoleon.client.core.tenant.TenantListView;
import eu.comexis.napoleon.client.core.tenant.TenantUpdatePresenter;
import eu.comexis.napoleon.client.core.tenant.TenantUpdateView;
import eu.comexis.napoleon.client.place.ClientPlaceManager;
import eu.comexis.napoleon.client.place.DefaultPlace;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.widget.DocumentPanelPresenter;
import eu.comexis.napoleon.client.widget.DocumentPanelView;

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
		
		bindPresenter(LeaseListPresenter.class,
        LeaseListPresenter.MyView.class, LeaseListView.class,
        LeaseListPresenter.MyProxy.class);
		
		bindPresenter(PaymentBoardListPresenter.class,
        PaymentBoardListPresenter.MyView.class, PaymentBoardListView.class,
        PaymentBoardListPresenter.MyProxy.class);
		
		bindPresenter(PaymentTenantListPresenter.class,
		    PaymentTenantListPresenter.MyView.class, PaymentTenantListView.class,
		    PaymentTenantListPresenter.MyProxy.class);
		
		bindPresenter(PaymentTenantUpdatePresenter.class,
        PaymentTenantUpdatePresenter.MyView.class, PaymentTenantUpdateView.class,
        PaymentTenantUpdatePresenter.MyProxy.class);
		
		bindPresenter(PaymentOwnerListPresenter.class,
        PaymentOwnerListPresenter.MyView.class, PaymentOwnerListView.class,
        PaymentOwnerListPresenter.MyProxy.class);
    
    bindPresenter(PaymentOwnerUpdatePresenter.class,
        PaymentOwnerUpdatePresenter.MyView.class, PaymentOwnerUpdateView.class,
        PaymentOwnerUpdatePresenter.MyProxy.class);
		
		bindPresenter(LeaseDetailsPresenter.class,
		    LeaseDetailsPresenter.MyView.class, LeaseDetailsView.class,
		    LeaseDetailsPresenter.MyProxy.class);
		
		bindPresenter(LeaseUpdatePresenter.class,
		    LeaseUpdatePresenter.MyView.class, LeaseUpdateView.class,
		    LeaseUpdatePresenter.MyProxy.class);

		bindPresenter(OwnerDetailsPresenter.class,
				OwnerDetailsPresenter.MyView.class, OwnerDetailsView.class,
				OwnerDetailsPresenter.MyProxy.class);

		bindPresenter(OwnerUpdatePresenter.class,
				OwnerUpdatePresenter.MyView.class, OwnerUpdateView.class,
				OwnerUpdatePresenter.MyProxy.class);

		bindPresenter(TenantListPresenter.class,
				TenantListPresenter.MyView.class, TenantListView.class,
				TenantListPresenter.MyProxy.class);

		bindPresenter(TenantDetailsPresenter.class,
				TenantDetailsPresenter.MyView.class, TenantDetailsView.class,
				TenantDetailsPresenter.MyProxy.class);
    
    bindPresenter(TenantUpdatePresenter.class,
        TenantUpdatePresenter.MyView.class, TenantUpdateView.class,
        TenantUpdatePresenter.MyProxy.class);
    
    bindPresenter(RealEstateListPresenter.class,
        RealEstateListPresenter.MyView.class, RealEstateListView.class,
        RealEstateListPresenter.MyProxy.class);

    bindPresenter(RealEstateDetailsPresenter.class,
        RealEstateDetailsPresenter.MyView.class, RealEstateDetailsView.class,
        RealEstateDetailsPresenter.MyProxy.class);
    
    bindPresenter(RealEstateUpdatePresenter.class,
        RealEstateUpdatePresenter.MyView.class, RealEstateUpdateView.class,
        RealEstateUpdatePresenter.MyProxy.class);
    
    bindPresenter(ExpenseListPresenter.class,
        ExpenseListPresenter.MyView.class, ExpenseListView.class,
        ExpenseListPresenter.MyProxy.class);

    bindPresenter(ExpenseDetailsPresenter.class,
        ExpenseDetailsPresenter.MyView.class, ExpenseDetailsView.class,
        ExpenseDetailsPresenter.MyProxy.class);
    
    bindPresenter(ExpenseUpdatePresenter.class,
        ExpenseUpdatePresenter.MyView.class, ExpenseUpdateView.class,
        ExpenseUpdatePresenter.MyProxy.class);

    bindPresenterWidget(DocumentPanelPresenter.class, DocumentPanelPresenter.MyView.class,
        DocumentPanelView.class);
	}
}