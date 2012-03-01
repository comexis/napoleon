package eu.comexis.napoleon.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.String;
import com.google.gwt.event.shared.HasHandlers;

public class DisplayMessageEvent extends GwtEvent<DisplayMessageEvent.DisplayMessageHandler> {

  public static Type<DisplayMessageHandler> TYPE = new Type<DisplayMessageHandler>();
  private String message;

  public interface DisplayMessageHandler extends EventHandler {
    void onDisplayMessage(DisplayMessageEvent event);
  }

  public DisplayMessageEvent(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  protected void dispatch(DisplayMessageHandler handler) {
    handler.onDisplayMessage(this);
  }

  @Override
  public Type<DisplayMessageHandler> getAssociatedType() {
    return TYPE;
  }

  public static Type<DisplayMessageHandler> getType() {
    return TYPE;
  }

  public static void fire(HasHandlers source, String message) {
    source.fireEvent(new DisplayMessageEvent(message));
  }
}
