package eu.comexis.napoleon.client.core.lease;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractListView;
import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

public class LeaseListView extends AbstractListView<SimpleLease> implements
  LeaseListPresenter.MyView {

  // key provider object implementation for SimpleLease object
  private static final ProvidesKey<SimpleLease> KEY_PROVIDER = new ProvidesKey<SimpleLease>() {
    public Object getKey(SimpleLease item) {
      // Always do a null check.
      return (item == null) ? null : item.getId();
    }
  };

  public LeaseListView() {
  }

  @Override
  protected ProvidesKey<SimpleLease> getKeyProvider() {
    return KEY_PROVIDER;
  }

  @Override
  protected void initTableColumns(SingleSelectionModel<SimpleLease> selectionModel,
      ListHandler<SimpleLease> sortHandler) {

    // Reference.
    Column<SimpleLease, String> nameColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return object.getRealEstateRef();
      }
    };

    nameColumn.setSortable(true);
    sortHandler.setComparator(nameColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(o1.getRealEstateRef(), o2.getRealEstateRef());
      }
    });

    table.addColumn(nameColumn, "Référence");

    // Academic Year.
    Column<SimpleLease, String> addressColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return object.getAcademicYear();
      }
    };

    addressColumn.setSortable(true);
    sortHandler.setComparator(addressColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(o1.getAcademicYear(), o2.getAcademicYear());
      }
    });

    table.addColumn(addressColumn, "Année académique");

  }
  
  @Override
  protected String getButtonNewLabel() {
    return "Nouvelle location";
  }
}
