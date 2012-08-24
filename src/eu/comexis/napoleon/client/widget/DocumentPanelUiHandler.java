package eu.comexis.napoleon.client.widget;

import eu.comexis.napoleon.shared.model.FileDescriptor;

public interface DocumentPanelUiHandler {

  void onBeforeUpload(FileDescriptor file);

  void onSubmitError();

  void onUploadDone(String id);

}
