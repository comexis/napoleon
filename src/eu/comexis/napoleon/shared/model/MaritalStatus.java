package eu.comexis.napoleon.shared.model;

public enum MaritalStatus {
  SINGLE, MARRIED, COHABITATION , NONE, DIVORCE, SEPARED, WIDOW;
  public static MaritalStatus fromStringToEnum(String value) {
    for (MaritalStatus t : values()) {
      if (t.name().equals(value)) {
        return t;
      }
    }
    return null;
  }
}
