package eu.comexis.napoleon.shared.model;

import java.util.List;

/**
 * Object having a list of documents associated to it
 * 
 * @author jDramaix
 * 
 */
public interface HasFiles extends Identifiable {

  public void addFile(FileDescriptor file);

  public List<FileDescriptor> getFiles();

  public void removeFile(FileDescriptor file);

}
