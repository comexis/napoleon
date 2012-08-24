package eu.comexis.napoleon.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.String;
import com.google.gwt.event.shared.HasHandlers;

public class SetTitleEvent extends GwtEvent<SetTitleEvent.SetTitleHandler> {

  public static Type<SetTitleHandler> TYPE = new Type<SetTitleHandler>();
  private String title;

  public interface SetTitleHandler extends EventHandler {
    void onSetTitle(SetTitleEvent event);
  }

  public SetTitleEvent(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  @Override
  protected void dispatch(SetTitleHandler handler) {
    handler.onSetTitle(this);
  }

  @Override
  public Type<SetTitleHandler> getAssociatedType() {
    return TYPE;
  }

  public static Type<SetTitleHandler> getType() {
    return TYPE;
  }

  public static void fire(HasHandlers source, String title) {
    source.fireEvent(new SetTitleEvent(title));
  }
}
