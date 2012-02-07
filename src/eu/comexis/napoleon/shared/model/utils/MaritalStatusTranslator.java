package eu.comexis.napoleon.shared.model.utils;

import java.util.HashMap;

import eu.comexis.napoleon.shared.model.MaritalStatus;

public class MaritalStatusTranslator {
  public HashMap<MaritalStatus, String> map2String = new HashMap<MaritalStatus, String>();

  public MaritalStatusTranslator() {
    map2String.put(MaritalStatus.SINGLE, "Célibataire");
    map2String.put(MaritalStatus.MARRIED, "Marié");
    map2String.put(MaritalStatus.COHABITATION, "Cohabitant");
  }

  public String fromEnumToString(MaritalStatus value) {
    if (map2String.containsKey(value)) {
      return map2String.get(value);
    } else {
      return "";
    }
  }
}
