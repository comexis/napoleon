package eu.comexis.napoleon.client;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

import eu.comexis.napoleon.client.gin.ClientGinjector;
import eu.comexis.napoleon.client.resources.Resources;

public class Napoleon implements EntryPoint {

  private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

  @Override
  public void onModuleLoad() {
    // This is required for Gwt-Platform proxy's generator
    DelayedBindRegistry.bind(ginjector);

    // inject css
    Resources.INSTANCE.css().ensureInjected();

    ginjector.getPlaceManager().revealCurrentPlace();

    $("#loading").remove();
  }

}
