package eu.comexis.napoleon.client;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

import eu.comexis.napoleon.client.gin.ClientGinjector;
import eu.comexis.napoleon.client.resources.Resources;
import eu.comexis.napoleon.client.widget.MessagePanel;

public class Napoleon implements EntryPoint {

  public static final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

  @Override
  public void onModuleLoad() {
	//setProgressBar(40);
    
	// This is required for Gwt-Platform proxy's generator
    DelayedBindRegistry.bind(ginjector);
    //setProgressBar(50);
    
    // inject css
    Resources.INSTANCE.css().ensureInjected();
    //setProgressBar(60);
    
    //init message 
    MessagePanel.INSTANCE.bind(ginjector.getEventBus());
    //setProgressBar(80);
    
    ginjector.getPlaceManager().revealCurrentPlace();
    
    //setProgressBar(100);
    $("#loading").remove();
  }
  
  
//  private void setProgressBar(int progress) {
//	  	GWT.log("set progress bqr to "+progress);
//		$("#progressBar").css("width", progress+"%");
		
//	}

}
