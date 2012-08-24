package eu.comexis.napoleon.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FileDescriptor implements IsSerializable, Identifiable {

  private String description;
  private String fileName;
  private String id;

  public FileDescriptor() {
  }

  public FileDescriptor(String fileName, String description) {
    this.fileName = fileName;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getFileName() {
    return fileName;
  }

  public String getId() {
    return id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setId(String id) {
    this.id = id;
  }

}