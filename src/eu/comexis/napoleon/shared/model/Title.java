package eu.comexis.napoleon.shared.model;

public enum Title {
  MR, MRS, MISS;
  public static Title fromStringToEnum(String value){
    for(Title t:values()){
      if (t.name().equals(value)){
        return t;
      }
    }
    return null;
  }
}
