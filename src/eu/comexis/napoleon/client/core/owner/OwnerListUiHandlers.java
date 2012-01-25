package eu.comexis.napoleon.client.core.owner;

import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

/**
 * Interface of the {@link OwnerListPresenter} used to communicate with the view
 * 
 * @author jDramaix
 * 
 */
public interface OwnerListUiHandlers {
	
	/**
	 * interface implemented by the view
	 * @author jDramaix
	 *
	 */
	public interface HasOwnerListUiHandlers {
		public void setOwnerListUiHandler(OwnerListUiHandlers handler);
	}

	/**
	 * Method call when the user select
	 * 
	 * @param selectedOwner
	 */
	public void onSelect(SimpleOwner selectedOwner);
}
