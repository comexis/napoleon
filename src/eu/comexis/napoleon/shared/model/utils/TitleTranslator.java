package eu.comexis.napoleon.shared.model.utils;

import java.util.HashMap;

import eu.comexis.napoleon.shared.model.Title;

public class TitleTranslator {
  public HashMap<Title, String> map2String = new HashMap<Title, String>();
  public HashMap<Title, String> map2ShortString = new HashMap<Title, String>();

  public TitleTranslator() {
    map2String.put(Title.MR, "Monsieur");
    map2String.put(Title.MRS, "Madame");
    map2String.put(Title.MISS, "Mademoiselle");
    map2ShortString.put(Title.MR, "Mrs");
    map2ShortString.put(Title.MRS, "Mme");
    map2ShortString.put(Title.MISS, "Mlle");
  }

  public String fromEnumToShortString(Title value) {
    if (map2ShortString.containsKey(value)) {
      return map2ShortString.get(value);
    } else {
      return "";
    }
  }

  public String fromEnumToString(Title value) {
    if (map2String.containsKey(value)) {
      return map2String.get(value);
    } else {
      return "";
    }
  }
}
