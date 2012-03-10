package eu.comexis.napoleon.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import eu.comexis.napoleon.client.core.MainLayoutPresenter.Menus;

public class SelectedMenuEvent extends GwtEvent<SelectedMenuEvent.SelectedMenuHandler> {
  

  public static Type<SelectedMenuHandler> TYPE = new Type<SelectedMenuHandler>();
  private Menus menu;

  public interface SelectedMenuHandler extends EventHandler {
    void onSelectedMenu(SelectedMenuEvent event);
  }

  public SelectedMenuEvent(Menus menu) {
    this.menu = menu;
  }

  public Menus getMenu() {
    return menu;
  }

  @Override
  protected void dispatch(SelectedMenuHandler handler) {
    handler.onSelectedMenu(this);
  }

  @Override
  public Type<SelectedMenuHandler> getAssociatedType() {
    return TYPE;
  }

  public static Type<SelectedMenuHandler> getType() {
    return TYPE;
  }

  public static void fire(HasHandlers source, Menus menu) {
    source.fireEvent(new SelectedMenuEvent(menu));
  }
}
