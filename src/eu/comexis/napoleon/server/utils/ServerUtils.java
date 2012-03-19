package eu.comexis.napoleon.server.utils;

import eu.comexis.napoleon.shared.model.Party;

public class ServerUtils {

  private ServerUtils() {
  }

  public static String buildPartyAddress(Party p) {

    StringBuilder builder = new StringBuilder();

    boolean hasStreet = isNotEmpty(p.getStreet());
    boolean hasNumber = isNotEmpty(p.getNumber());
    boolean hasBox = isNotEmpty(p.getBox());

    if (hasStreet) {
      builder.append(p.getStreet());
    }

    if (hasStreet && hasNumber) {
      builder.append(", ");
    }

    if (hasNumber) {
      builder.append(p.getNumber());
    }

    if (hasBox) {
      builder.append(" bte ");
      builder.append(p.getBox());
    }

    return builder.toString();
  }

  private static boolean isNotEmpty(String s) {
    return s != null && s.length() != 0;
  }

}
