package eu.comexis.napoleon.shared.model;

public enum EntityStatus {
  ACTIVE, NONACTIVE /*, DELETED*/;
  public static EntityStatus fromStringToEnum(String value) {
    for (EntityStatus t : values()) {
      if (t.name().equals(value)) {
        return t;
      }
    }
    return null;
  }  
  
}
