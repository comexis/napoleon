package eu.comexis.napoleon.client.core.expense;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractShortListView;
import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.shared.model.Expense;

public class ExpenseListView extends AbstractShortListView<Expense> implements
  ExpenseListPresenter.MyView {

  // key provider object implementation for Expense object
  private static final ProvidesKey<Expense> KEY_PROVIDER = new ProvidesKey<Expense>() {
    public Object getKey(Expense item) {
      // Always do a null check.
      return (item == null) ? null : item.getId();
    }
  };

  public ExpenseListView() {
  }

  @Override
  protected ProvidesKey<Expense> getKeyProvider() {
    return KEY_PROVIDER;
  }

  @Override
  protected void initTableColumns(SingleSelectionModel<Expense> selectionModel,
      ListHandler<Expense> sortHandler) {

    // Reference.
    Column<Expense, String> nameColumn = new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        return object.getReference();
      }
    };

    nameColumn.setSortable(true);
    sortHandler.setComparator(nameColumn, new SimpleTextComparator<Expense>() {
      public int compare(Expense o1, Expense o2) {
        return compare(o1.getReference(), o2.getReference());
      }
    });

    table.addColumn(nameColumn, "Référence");

  }
  
  @Override
  protected String getButtonNewLabel() {
    return "Nouvelle dépense";
  }
  @Override
  protected String getButtonDeleteLabel() {
    return "Supprimer";
  }
}
