package eu.comexis.napoleon.client.core.paymentBoard;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractShortListView;
import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public class PaymentBoardListView extends AbstractShortListView<PaymentListItem> implements
  PaymentBoardListPresenter.MyView {

  // key provider object implementation for SimpleLease object
  private static final ProvidesKey<PaymentListItem> KEY_PROVIDER = new ProvidesKey<PaymentListItem>() {
    public Object getKey(PaymentListItem item) {
      // Always do a null check.
      return (item == null) ? null : item.getPaymentTenantId();
    }
  };

  public PaymentBoardListView() {
  }

  @Override
  protected ProvidesKey<PaymentListItem> getKeyProvider() {
    return KEY_PROVIDER;
  }

  @Override
  protected void initTableColumns(SingleSelectionModel<PaymentListItem> selectionModel,
      ListHandler<PaymentListItem> sortHandler) {

    // period du.
    Column<PaymentListItem, String> fromDateColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.displayDate(object.getFromDate());
      }
    };

    fromDateColumn.setSortable(true);
    sortHandler.setComparator(fromDateColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.formatDateForCompare(o1.getFromDate()), UiHelper.formatDateForCompare(o2.getFromDate()));
      }
    });

    table.addColumn(fromDateColumn, "Du");

    // period au.
    Column<PaymentListItem, String> toDateColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.displayDate(object.getToDate());
      }
    };

    /*toDateColumn.setSortable(true);
    sortHandler.setComparator(toDateColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.formatDateForCompare(o1.getToDate()), UiHelper.formatDateForCompare(o2.getToDate()));
      }
    });*/

    table.addColumn(toDateColumn, "Au");
    
    // Loyer.
    Column<PaymentListItem, String> amountColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.FloatToString(object.getRent());
      }
    };

    /*amountColumn.setSortable(true);
    sortHandler.setComparator(amountColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.FloatToString(o1.getRent()),UiHelper.FloatToString(o2.getRent()));
      }
    });*/

    table.addColumn(amountColumn, "Loyer");
    
    // Date.
    Column<PaymentListItem, String> dateColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.displayDate(object.getPaymentTenantDate());
      }
    };

    /*dateColumn.setSortable(true);
    sortHandler.setComparator(dateColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.formatDateForCompare(o1.getPaymentTenantDate()), UiHelper.formatDateForCompare(o2.getPaymentTenantDate()));
      }
    });*/

    table.addColumn(dateColumn, "Date paiement");
    
    // Honoraire.
    Column<PaymentListItem, String> feeColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.FloatToString(object.getFee());
      }
    };

    /*feeColumn.setSortable(true);
    sortHandler.setComparator(feeColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.FloatToString(o1.getFee()),UiHelper.FloatToString(o2.getFee()));
      }
    });*/
    
    table.addColumn(feeColumn, "Honoraires");
    
    // solde.
    Column<PaymentListItem, String> balanceColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.FloatToString(object.getBalance());
      }
    };

    /*balanceColumn.setSortable(true);
    sortHandler.setComparator(balanceColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.FloatToString(o1.getBalance()),UiHelper.FloatToString(o2.getBalance()));
      }
    });*/
    
    table.addColumn(balanceColumn, "Solde");
    
    // expense.
    Column<PaymentListItem, String> expenseColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.FloatToString(object.getExpenses());
      }
    };

    /*expenseColumn.setSortable(true);
    sortHandler.setComparator(expenseColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.FloatToString(o1.getExpenses()),UiHelper.FloatToString(o2.getExpenses()));
      }
    });*/
    
    table.addColumn(expenseColumn, "Dépenses");
    
    // Du au proprio.
    Column<PaymentListItem, String> dueToOnwerColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.FloatToString(object.getToBePaidToOwner());
      }
    };

    /*dueToOnwerColumn.setSortable(true);
    sortHandler.setComparator(dueToOnwerColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.FloatToString(o1.getToBePaidToOwner()),UiHelper.FloatToString(o2.getToBePaidToOwner()));
      }
    });*/
    
    table.addColumn(dueToOnwerColumn, "Dû au propriétaire");
    
    // Payé au proprio.
    Column<PaymentListItem, String> givenToOnwerColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.FloatToString(object.getPaidToOwner());
      }
    };

    /*givenToOnwerColumn.setSortable(true);
    sortHandler.setComparator(givenToOnwerColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.FloatToString(o1.getPaidToOwner()),UiHelper.FloatToString(o2.getPaidToOwner()));
      }
    });*/
    
    table.addColumn(givenToOnwerColumn, "Versé au propriétaire");
    
 // Date.
    Column<PaymentListItem, String> dateVersColumn = new Column<PaymentListItem, String>(new TextCell()) {
      @Override
      public String getValue(PaymentListItem object) {
        return UiHelper.displayDate(object.getPaymentOwnerDate());
      }
    };

    /*dateVersColumn.setSortable(true);
    sortHandler.setComparator(dateVersColumn, new SimpleTextComparator<PaymentListItem>() {
      public int compare(PaymentListItem o1, PaymentListItem o2) {
        return compare(UiHelper.formatDateForCompare(o1.getPaymentOwnerDate()), UiHelper.formatDateForCompare(o2.getPaymentOwnerDate()));
      }
    });*/

    table.addColumn(dateVersColumn, "Date de versement");
  }
  
  @Override
  protected String getButtonNewLabel() {
    return "Nouveau paiement";
  }
  @Override
  protected String getButtonDeleteLabel() {
    return "Supprimer le paiement sélectionné";
  }
}
