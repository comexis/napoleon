package eu.comexis.napoleon.client.utils;

import java.util.Comparator;

public abstract class SimpleTextComparator<T> implements Comparator<T> {

  public int compare(String s1, String s2) {
    if (s1 != null) {
      return s1.compareTo(s2);
    } else if (s2 != null) {
      // s1 is null and s2 not
      return -1;
    }
    // s1 null and s2 too
    return 0;
  };
}
