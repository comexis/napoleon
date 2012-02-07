package eu.comexis.napoleon.shared.model.utils;

import java.util.HashMap;

import eu.comexis.napoleon.shared.model.MatrimonialRegime;

public class MatrimonialRegimeTranslator {
  public HashMap<MatrimonialRegime, String> map2String = new HashMap<MatrimonialRegime, String>();

  public MatrimonialRegimeTranslator() {
    map2String.put(MatrimonialRegime.NONE, "aucun");
    map2String.put(MatrimonialRegime.COMMUNITY, "Communauté des biens");
    map2String.put(MatrimonialRegime.SEPARATION, "Séparation des biens");
  }

  public String fromEnumToString(MatrimonialRegime value) {
    if (map2String.containsKey(value)) {
      return map2String.get(value);
    } else {
      return "";
    }
  }
}
