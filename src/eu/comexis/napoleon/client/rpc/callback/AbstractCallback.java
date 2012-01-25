package eu.comexis.napoleon.client.rpc.callback;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Abstract class for all callback managing the case where a rpc call fails.
 * @author jDramaix
 *
 * @param <T>
 */
public abstract class AbstractCallback<T> implements AsyncCallback<T> {
	
	Logger logger = Logger.getLogger(AbstractCallback.class.getName());
    

	@Override
	public void onFailure(Throwable caught) {
		
		if (LogConfiguration.loggingIsEnabled()){
			logger.log(Level.SEVERE, "RPC call returns an error "+ caught.getMessage(), caught);
		}
		
		//TODO fire an event !!!

	}

}
