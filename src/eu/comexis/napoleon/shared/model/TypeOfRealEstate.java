package eu.comexis.napoleon.shared.model;

public enum TypeOfRealEstate {
  A1,A2,A3,OFFICE,KOT,FLAT,VILLA,COMMERCE,ATELIER;
  public static TypeOfRealEstate fromStringToEnum(String value){
    for(TypeOfRealEstate t:values()){
      if (t.name().equals(value)){
        return t;
      }
    }
    return null;
  }
}
