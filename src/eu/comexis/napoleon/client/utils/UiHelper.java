package eu.comexis.napoleon.client.utils;

import com.google.gwt.user.client.ui.ListBox;

import eu.comexis.napoleon.client.resources.Literals;

public class UiHelper {

  // static class
  private UiHelper() {
  }

  public static ListBox createListBoxForEnum(Class<? extends Enum<?>> enumClass, String prefix,
      boolean mutiple) {
    ListBox box = new ListBox(mutiple);

    for (Enum<?> e : enumClass.getEnumConstants()) {
      box.addItem(translateEnum(prefix, e), e.name());
    }

    return box;
  }

  public static String translateEnum(String prefix, Enum<?> e) {
    if (e == null) {
      return "";
    }
    return translateEnum(prefix, e, "");
  }

  public static String translateEnum(String prefix, Enum<?> e, String suffix) {
    String literalKey = new StringBuilder(prefix).append(e.name()).append(suffix).toString();
    return Literals.INSTANCE.getString(literalKey);
  }

  public static void selectTextItemBoxByValue(ListBox tb, String value) {
    for (int i = 0; i < tb.getItemCount(); i++) {
      if (tb.getValue(i).equals(value)) {
        tb.setSelectedIndex(i);
        break;
      }
    }
  }

}
