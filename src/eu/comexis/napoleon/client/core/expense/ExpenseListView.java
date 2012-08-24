package eu.comexis.napoleon.client.core.expense;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractShortListView;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.utils.SimpleTextComparator;


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
     
    
    // Amount.
    Column<Expense, String> amountColumn = new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        return UiHelper.FloatToString(object.getAmount());
      }
    };

    amountColumn.setSortable(true);
    sortHandler.setComparator(amountColumn, new SimpleTextComparator<Expense>() {
      public int compare(Expense o1, Expense o2) {
        return compare(UiHelper.FloatToString(o1.getAmount()), UiHelper.FloatToString(o2.getAmount()));
      }
    });

    table.addColumn(amountColumn, "Montant");
    
    // DateFacture.
    Column<Expense, String> dateInvoiceColumn = new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        return UiHelper.displayDate(object.getDateInvoice());
      }
    };

    dateInvoiceColumn.setSortable(true);
    sortHandler.setComparator(dateInvoiceColumn, new SimpleTextComparator<Expense>() {
      public int compare(Expense o1, Expense o2) {
        return compare(UiHelper.formatDateForCompare(o1.getDateInvoice()), UiHelper.formatDateForCompare(o2.getDateInvoice()));
      }
    });

    table.addColumn(dateInvoiceColumn, "Date facture");
    
    // Date imputé au proprio.
    Column<Expense, String> dateChargedToOwnerColumn = new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        if (object.getToBePaidByOwner()!=0){
          return UiHelper.displayDate(object.getChargedToOwnerPeriod());
        }else{
          return "n/a";
        }
      }
    };

    dateChargedToOwnerColumn.setSortable(true);
    sortHandler.setComparator(dateChargedToOwnerColumn, new SimpleTextComparator<Expense>() {
      public int compare(Expense o1, Expense o2) {
        return compare(UiHelper.formatDateForCompare(o1.getChargedToOwnerPeriod()), UiHelper.formatDateForCompare(o2.getChargedToOwnerPeriod()));
      }
    });

    table.addColumn(dateChargedToOwnerColumn, "Déduit le");

  
  //Date payé par le locataire
  Column<Expense, String> datePaidByTenantColumn = new Column<Expense, String>(new TextCell()) {
    @Override
    public String getValue(Expense object) {
      if (object.getToBePaidByTenant()!=0){
        return UiHelper.displayDate(object.getDatePaymentTenant());
      }else{
        return "n/a";
      }
    }
  };

  datePaidByTenantColumn.setSortable(true);
  sortHandler.setComparator(datePaidByTenantColumn, new SimpleTextComparator<Expense>() {
    public int compare(Expense o1, Expense o2) {
      return compare(UiHelper.formatDateForCompare(o1.getDatePaymentTenant()), UiHelper.formatDateForCompare(o2.getDatePaymentTenant()));
    }
  });

  table.addColumn(datePaidByTenantColumn, "Soldé le");

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
