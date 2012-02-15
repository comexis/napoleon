package eu.comexis.napoleon.client.widget;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

import eu.comexis.napoleon.client.resources.Css;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.resources.Resources;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class InformationDialog {

  private static DialogUiBinder uiBinder = GWT.create(DialogUiBinder.class);
  private static InformationDialog INSTANCE;

  public interface Templates extends SafeHtmlTemplates {

    Templates INSTANCE = GWT.create(Templates.class);

    @Template("<div class='{2}'><span>{0}</span><div class='{3}'>{1}</div></div>")
    SafeHtml validationMessageOuter(String intro, SafeHtml messages, String outerClass,
        String innerClass);

    @Template("<div class='{1}'>{0}</div>")
    SafeHtml validationMessageInner(String message, String cssClass);
  }

  interface DialogUiBinder extends UiBinder<DialogBox, InformationDialog> {
  }

  public static InformationDialog get() {
    if (INSTANCE == null) {
      INSTANCE = new InformationDialog();
    }

    return INSTANCE;
  }

  @UiField
  Element msg;

  @UiField
  Button okButton;

  private DialogBox dialog;

  private InformationDialog() {
    dialog = uiBinder.createAndBindUi(this);
  }

  public boolean show(String html) {
    return show(html, Literals.INSTANCE.informationDialogDefaultTitle());
  }
  
  public boolean show(String html, String title) {
    return show(html, title, true);
  }

  public boolean show(String html, String title, boolean displayButton) {
    if (displayButton) {
      $(okButton).show();
    } else {
      $(okButton).hide();
    }

    msg.setInnerHTML(html);
    
    dialog.getCaption().setHTML(title);

    if (!dialog.isShowing()) {
      dialog.center();
    }

    return true;
  }

  public void showValidationMessages(List<ValidationMessage> messages) {
    if (messages == null || messages.isEmpty()) {
      return;
    }

    Templates t = Templates.INSTANCE;
    Css css = Resources.INSTANCE.css();

    SafeHtmlBuilder messagesBuilder = new SafeHtmlBuilder();

    for (ValidationMessage msg : messages) {
      messagesBuilder.append(t.validationMessageInner(msg.getMessage(), css.validationMessage()));
    }

    SafeHtml html =
        t.validationMessageOuter(Literals.INSTANCE.validationMessageDisplayIntro(), messagesBuilder
            .toSafeHtml(), css.validationMessageOuter(), css.validationMessageInner());

    show(html.asString(), Literals.INSTANCE.validationMessageDialogTitle());
  }

  @UiHandler("okButton")
  public void onOk(ClickEvent e) {
    hide();
  }

  public void hide() {
    dialog.hide();
  }

}
