package eu.comexis.napoleon.shared.command;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Marker interface for Command
 * @author julien
 *
 * @param <T> the response
 */
public interface Action<T extends Response> extends IsSerializable{
	
	public void dispatch(AsyncCallback<T> callback);
}
