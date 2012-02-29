package eu.comexis.napoleon.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import eu.comexis.napoleon.client.events.DisplayMessageEvent;
import eu.comexis.napoleon.client.events.DisplayMessageEvent.DisplayMessageHandler;
import eu.comexis.napoleon.client.events.HideMessageEvent;
import eu.comexis.napoleon.client.events.HideMessageEvent.HideMessageHandler;
import eu.comexis.napoleon.client.resources.Resources;

public enum MessagePanel implements DisplayMessageHandler, HideMessageHandler {
  
  INSTANCE;

  public interface Templates  extends SafeHtmlTemplates {
    
    public Templates INSTANCE = GWT.create(Templates.class);
    
    @Template("<div class='{1}'>{0}</div>")
    public SafeHtml innerPopup(String msg, String className);
    
  }
  
  private final DecoratedPopupPanel messagePopup;
  private final HTML inner;
  
  private MessagePanel() {
    
    messagePopup = new DecoratedPopupPanel(false);
    messagePopup.setAnimationEnabled(true);
    messagePopup.setModal(false);
    messagePopup.addStyleName(Resources.INSTANCE.css().messagePanelOuter());
    inner = new HTML();
    messagePopup.add(inner);
  }

  public Widget asWidget() {
    return messagePopup;
  }
  
  public void bind(EventBus bus){
    bus.addHandler(DisplayMessageEvent.getType(), this);
    bus.addHandler(HideMessageEvent.getType(), this);
  }

  @Override
  public void onHideMessage(HideMessageEvent event) {
    messagePopup.hide();
  }

  @Override
  public void onDisplayMessage(DisplayMessageEvent event) {
    
    String message  = event.getMessage();
    SafeHtml innerHtml = Templates.INSTANCE.innerPopup(message, Resources.INSTANCE.css().messagePanelInner());
    inner.setHTML(innerHtml);
    
    messagePopup.setPopupPositionAndShow(new PositionCallback() {
      
      @Override
      public void setPosition(int offsetWidth, int offsetHeight) {
        messagePopup.setPopupPosition(Window.getClientWidth()/2 - offsetWidth/2, 15);
      }
    });
    
    
  }
  
}
