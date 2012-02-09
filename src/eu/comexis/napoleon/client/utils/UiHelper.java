package eu.comexis.napoleon.client.utils;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import eu.comexis.napoleon.client.resources.Literals;

public class UiHelper {

  //static class
  private UiHelper(){}
  
  
  public static ListBox createListBoxForEnum(Class<? extends Enum<?>> enumClass, String suffix, boolean mutiple){
    ListBox box = new ListBox(mutiple);
    
    for (Enum<?> e : enumClass.getEnumConstants()){
      String literalKey = suffix +e.name();
      box.addItem(Literals.INSTANCE.getString(literalKey), e.name());
    }
    
    return box;
  }
  public static String translateEnum(String suffix, Enum<?> e){
      String literalKey = suffix +e.name();
      return Literals.INSTANCE.getString(literalKey);
  }
  public static void selectTextItemBoxByValue(ListBox tb,String value){
    for (int i = 0; i < tb.getItemCount(); i++) {
      if (tb.getValue(i).equals(value)) {
        tb.setSelectedIndex(i);
        break;
      }
    }
  }
}
