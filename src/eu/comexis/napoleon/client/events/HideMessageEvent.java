package eu.comexis.napoleon.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class HideMessageEvent extends GwtEvent<HideMessageEvent.HideMessageHandler> {

  public static Type<HideMessageHandler> TYPE = new Type<HideMessageHandler>();

  public interface HideMessageHandler extends EventHandler {
    void onHideMessage(HideMessageEvent event);
  }

  public HideMessageEvent() {
  }

  @Override
  protected void dispatch(HideMessageHandler handler) {
    handler.onHideMessage(this);
  }

  @Override
  public Type<HideMessageHandler> getAssociatedType() {
    return TYPE;
  }

  public static Type<HideMessageHandler> getType() {
    return TYPE;
  }

  public static void fire(HasHandlers source) {
    source.fireEvent(new HideMessageEvent());
  }
}
