package eu.comexis.napoleon.client.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.resources.Css;
import eu.comexis.napoleon.client.resources.Resources;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.validation.ValidationMessage;

public class DocumentPanelView extends ViewImpl implements DocumentPanelPresenter.MyView {

  public interface Binder extends UiBinder<Widget, DocumentPanelView> {
  }
  public interface Templates extends SafeHtmlTemplates {

    Templates INSTANCE = GWT.create(Templates.class);

    @Template("<li class='{2}'><a href='{1}'>{0}</a></li>")
    SafeHtml file(String label, SafeUri href, String cssClassName);

    @Template("<ul class='{0}'>")
    SafeHtml fileListBegin(String cssClassName);

  }

  private static final String DOWNLOAD_URL = "/napoleon/download";

  @UiField
  TextBox descriptionBox;

  @UiField
  Element fileList;

  @UiField
  FormPanel formPanel;

  @UiField
  FileUpload uploadBox;

  private DocumentPanelUiHandler presenter;

  private final Widget widget;

  @Inject
  public DocumentPanelView(final Binder binder) {
    widget = binder.createAndBindUi(this);
  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @Override
  public void displayErrorMessage(List<ValidationMessage> msgs) {
    UiHelper.displayValidationMessage(msgs, asWidget());
  }

  @UiHandler("formPanel")
  public void onFormSubmitted(SubmitCompleteEvent e) {
    String result = e.getResults();

    if (result == null || result.length() == 0) {
      presenter.onSubmitError();
    } else {
      presenter.onUploadDone(result);
    }
  }

  @UiHandler("uploadButton")
  public void onUploadClicked(ClickEvent e) {
    FileDescriptor file = new FileDescriptor(uploadBox.getFilename(), descriptionBox.getText());
    presenter.onBeforeUpload(file);
  }

  @Override
  public void reset() {
    formPanel.reset();

  }

  @Override
  public void setFiles(List<FileDescriptor> files, String parentId) {
    Templates t = Templates.INSTANCE;
    Css css = Resources.INSTANCE.css();

    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    builder.append(t.fileListBegin(css.outerRealEstateList()));

    if (files != null && !files.isEmpty()) {
      for (FileDescriptor file : files) {
        String label = file.getDescription() != null ? file.getDescription() : file.getFileName();
        SafeUri href = getFileUri(file.getId(), parentId);
        builder.append(t.file(label, href, css.innerRealEstateList()));
      }
    }

    builder.appendHtmlConstant("</ul>");
    fileList.setInnerHTML(builder.toSafeHtml().asString());

  }

  @Override
  public void setPresenter(DocumentPanelUiHandler presenter) {
    this.presenter = presenter;
  }

  @Override
  public void uploadFile(String url) {
    formPanel.setAction(url);
    formPanel.submit();

  }

  private SafeUri getFileUri(String fileId, String parentId) {
    StringBuilder uriBuilder = new StringBuilder();
    uriBuilder.append(DOWNLOAD_URL);
    uriBuilder.append("?k=").append(fileId);
    uriBuilder.append("&p=").append(parentId);

    return UriUtils.fromString(uriBuilder.toString());
  }
}
