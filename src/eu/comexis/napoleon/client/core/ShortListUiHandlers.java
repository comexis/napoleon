package eu.comexis.napoleon.client.core;

/**
 * Interface of the {@link AbstractListPresenter} used to communicate with the view
 * 
 * @author jDramaix
 * 
 */
public interface ShortListUiHandlers<T> {

  /**
   * interface implemented by the view
   * 
   * @author jDramaix
   * 
   */
  public interface HasShortListUiHandlers<T> {
    public void setPresenter(ShortListUiHandlers<T> handler);
  }
  
  public void onButtonDeleteClick();

  public void onButtonNewClick();

  public void onSelect(T selectedRealEstate);

}
