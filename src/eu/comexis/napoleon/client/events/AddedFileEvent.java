package eu.comexis.napoleon.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import eu.comexis.napoleon.shared.model.FileDescriptor;
import eu.comexis.napoleon.shared.model.HasFiles;

public class AddedFileEvent extends GwtEvent<AddedFileEvent.AddedFileHandler> {

  public interface AddedFileHandler extends EventHandler {
    void onAddedFile(AddedFileEvent event);
  }
  public static Type<AddedFileHandler> TYPE = new Type<AddedFileHandler>();
  public static void fire(HasHandlers source, FileDescriptor file, HasFiles entity) {
    source.fireEvent(new AddedFileEvent(file, entity));
  }

  public static Type<AddedFileHandler> getType() {
    return TYPE;
  }

  private HasFiles entity;

  private FileDescriptor file;

  public AddedFileEvent(FileDescriptor file, HasFiles entity) {
    this.file = file;
    this.entity = entity;
  }

  @Override
  public Type<AddedFileHandler> getAssociatedType() {
    return TYPE;
  }

  public HasFiles getEntity() {
    return entity;
  }

  public FileDescriptor getFile() {
    return file;
  }

  @Override
  protected void dispatch(AddedFileHandler handler) {
    handler.onAddedFile(this);
  }
}
