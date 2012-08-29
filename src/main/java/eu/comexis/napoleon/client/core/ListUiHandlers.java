package eu.comexis.napoleon.client.core;

/**
 * Interface of the {@link AbstractListPresenter} used to communicate with the view
 * 
 * @author jDramaix
 * 
 */
public interface ListUiHandlers<T> {

  /**
   * interface implemented by the view
   * 
   * @author jDramaix
   * 
   */
  public interface HasListUiHandlers<T> {
    public void setPresenter(ListUiHandlers<T> handler);
  }

  public void onButtonBackToDashBoardClick();

  public void onButtonNewClick();
  
  public void onShowOnlyActiveClicked(boolean clicked, String filterString);

  /**
   * Method call when the user select
   * 
   * @param selectedRealEstate
   */
  public void onSelect(T selectedRealEstate);

  public void filter(String filterString, boolean showOnlyActive);
}
