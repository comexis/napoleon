package eu.comexis.napoleon.client.widget;

import static eu.comexis.napoleon.client.resources.ValidationMessages.VALIDATION_MESSAGES;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import eu.comexis.napoleon.client.core.HasPresenter;
import eu.comexis.napoleon.client.events.AddedFileEvent;
import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.HasFiles;
import eu.comexis.napoleon.shared.validation.AbstractValidator;
import eu.comexis.napoleon.shared.validation.ValidationMessage;
import eu.comexis.napoleon.shared.validation.Validator;

public class DocumentPanelPresenter extends PresenterWidget<DocumentPanelPresenter.MyView>
    implements DocumentPanelUiHandler {

  public interface MyView extends View, HasPresenter<DocumentPanelUiHandler> {
    public void displayErrorMessage(List<ValidationMessage> msgs);

    public void reset();

    public void setFiles(List<FileDescriptor> files, String parentId);

    public void uploadFile(String url);
  }

  public class NapoleonFileValidator extends AbstractValidator<FileDescriptor> {
    @Override
    public List<ValidationMessage> validate(FileDescriptor toValidate) {
      List<ValidationMessage> msgs = new ArrayList<ValidationMessage>();

      if (isEmpty(toValidate.getFileName())) {
        msgs.add(new ValidationMessage(VALIDATION_MESSAGES.fieldIsMandatory("Fichier"), "file"));
      }
      return msgs;
    }
  }

  private HasFiles entity;

  // file being submitted
  private FileDescriptor uploadingFile;
  private Validator<FileDescriptor> validator;
  @Inject
  public DocumentPanelPresenter(final EventBus eventBus, final MyView view) {
    super(eventBus, view);
    validator = new NapoleonFileValidator();
  }

  @Override
  public void onBeforeUpload(final FileDescriptor file) {

    uploadingFile = null;

    List<ValidationMessage> msgs = validator.validate(file);

    if (msgs != null && !msgs.isEmpty()) {
      getView().displayErrorMessage(msgs);
    } else {

      GQuery.get("/napoleon/upload", null, new Function() {
        @Override
        public void f() {
          // TODO manage errors

          uploadingFile = file;
          String url = (String) getData()[0];

          url = url.replace("Admins-MacBook-Pro.local", "127.0.0.1");

          getView().uploadFile(url);
        }
      });

    }

  }

  @Override
  public void onSubmitError() {
    uploadingFile = null;
    // TODO call the view to display a nice message
    Window.alert("Impossible d'uploader le fichier. Veuillez réessayer.");

  }

  @Override
  public void onUploadDone(String fileId) {
    uploadingFile.setId(fileId);

    entity.addFile(uploadingFile);

    getView().setFiles(entity.getFiles(), entity.getId());
    getView().reset();

    AddedFileEvent.fire(getEventBus(), uploadingFile, entity);

    uploadingFile = null;

  }

  public void setDocumentHolder(HasFiles entity) {
    this.entity = entity;
    getView().setFiles(entity.getFiles(), entity.getId());
  }

  @Override
  protected void onBind() {
    super.onBind();
    getView().setPresenter(this);
  }
}
