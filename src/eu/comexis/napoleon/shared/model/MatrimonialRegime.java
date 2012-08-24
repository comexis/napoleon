package eu.comexis.napoleon.shared.model;

public enum MatrimonialRegime {
  SEPARATION, COMMUNITY, NONE;
  public static MatrimonialRegime fromStringToEnum(String value) {
    for (MatrimonialRegime t : values()) {
      if (t.name().equals(value)) {
        return t;
      }
    }
    return null;
  }
}
