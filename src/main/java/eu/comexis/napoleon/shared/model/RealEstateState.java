package eu.comexis.napoleon.shared.model;

public enum RealEstateState {
  GOOD, EXCELLENT, NEW, RENEWED, RENEW, REFRESH, NONE;
  public static RealEstateState fromStringToEnum(String value) {
    for (RealEstateState t : values()) {
      if (t.name().equals(value)) {
        return t;
      }
    }
    return null;
  }
}
