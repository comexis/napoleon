package eu.comexis.napoleon.shared.model;

public enum EntityStatus {
  ACTIVE, NONACTIVE /*, DELETED*/;
  
  public static EntityStatus fromStringToEnum(String value) {
    for (EntityStatus t : values()) {
      if (t.name().equals(value)) {
        return t;
      }
    }
    //if status unknow -> ACTIVE
    return ACTIVE;
  }  
  
  public static boolean isActif(EntityStatus status){
    //for historical reason, status can be null -> considered as active
    return status == null || ACTIVE == status;
  }
  
}
