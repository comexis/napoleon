package eu.comexis.napoleon.shared.validation;

public class ValidationMessage {

  private String message;
  private String componentId;

  public ValidationMessage() {
  }

  public ValidationMessage(String message, String componentId) {
    this.message = message;
    this.componentId = componentId;
  }

  public String getComponentId() {
    return componentId;
  }

  public String getMessage() {
    return message;
  }

  public void setComponentId(String componentId) {
    this.componentId = componentId;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
