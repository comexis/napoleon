package eu.comexis.napoleon.client.core.tenant;

import java.util.logging.Logger;

import com.google.inject.Inject;

import eu.comexis.napoleon.client.core.party.PartyUpdateView;
import eu.comexis.napoleon.shared.model.Tenant;

public class TenantUpdateView extends PartyUpdateView<Tenant> implements TenantUpdatePresenter.MyView {
  
  @SuppressWarnings("unused")
  private static final Logger LOG = Logger.getLogger(TenantUpdateView.class.getName());

 

  @Inject
  public TenantUpdateView() {
    super();
  }

 
}