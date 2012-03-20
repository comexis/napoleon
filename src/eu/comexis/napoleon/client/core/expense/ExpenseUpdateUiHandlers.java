package eu.comexis.napoleon.client.core.expense;

public interface ExpenseUpdateUiHandlers {
  public interface HasExpenseUpdateUiHandler {
    public void setExpenseUpdateUiHandler(ExpenseUpdateUiHandlers handler);
  }
  public void onButtonCancelClick();

  public void onButtonSaveClick();
  

}
