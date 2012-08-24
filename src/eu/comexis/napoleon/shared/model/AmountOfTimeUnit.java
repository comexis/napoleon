package eu.comexis.napoleon.shared.model;

public enum AmountOfTimeUnit {
  MONTH,DAY,NONE;
  public static AmountOfTimeUnit fromStringToEnum(String value) {
    for (AmountOfTimeUnit t : values()) {
      if (t.name().equals(value)) {
        return t;
      }
    }
    return null;
  }
}
