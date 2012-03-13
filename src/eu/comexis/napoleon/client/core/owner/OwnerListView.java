package eu.comexis.napoleon.client.core.owner;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractListView;
import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerListView extends AbstractListView<SimpleOwner> implements
    OwnerListPresenter.MyView {

  // key provider object implementation for SimpleOwner object
  private static final ProvidesKey<SimpleOwner> KEY_PROVIDER = new ProvidesKey<SimpleOwner>() {
    public Object getKey(SimpleOwner item) {
      // Always do a null check.
      return (item == null) ? null : item.getId();
    }
  };

  public OwnerListView() {
  }

  @Override
  protected ProvidesKey<SimpleOwner> getKeyProvider() {
    return KEY_PROVIDER;
  }

  @Override
  protected void initTableColumns(SingleSelectionModel<SimpleOwner> selectionModel,
      ListHandler<SimpleOwner> sortHandler) {

    // Name.
    Column<SimpleOwner, String> nameColumn = new Column<SimpleOwner, String>(new TextCell()) {
      @Override
      public String getValue(SimpleOwner object) {
        return object.getName();
      }
    };

    nameColumn.setSortable(true);
    sortHandler.setComparator(nameColumn, new SimpleTextComparator<SimpleOwner>() {
      public int compare(SimpleOwner o1, SimpleOwner o2) {
        return compare(o1.getName(), o2.getName());
      }
    });

    table.addColumn(nameColumn, "Nom");

    // address.
    Column<SimpleOwner, String> addressColumn = new Column<SimpleOwner, String>(new TextCell()) {
      @Override
      public String getValue(SimpleOwner object) {
        return object.getAddress();
      }
    };

    addressColumn.setSortable(true);
    sortHandler.setComparator(addressColumn, new SimpleTextComparator<SimpleOwner>() {
      public int compare(SimpleOwner o1, SimpleOwner o2) {
        return compare(o1.getAddress(), o2.getAddress());
      }
    });

    table.addColumn(addressColumn, "Adresse");

    // Postal Code.
    Column<SimpleOwner, String> cpColumn = new Column<SimpleOwner, String>(new TextCell()) {
      @Override
      public String getValue(SimpleOwner object) {
        return object.getPostalCode();
      }
    };

    cpColumn.setSortable(true);

    sortHandler.setComparator(cpColumn, new SimpleTextComparator<SimpleOwner>() {
      public int compare(SimpleOwner o1, SimpleOwner o2) {
        return compare(o1.getPostalCode(), o2.getPostalCode());
      }
    });
    table.addColumn(cpColumn, "Code Postal");
    table.setColumnWidth(cpColumn, "20%");

    // City
    Column<SimpleOwner, String> cityColumn = new Column<SimpleOwner, String>(new TextCell()) {
      @Override
      public String getValue(SimpleOwner object) {
        return object.getCity();
      }
    };

    cityColumn.setSortable(true);
    sortHandler.setComparator(cityColumn, new SimpleTextComparator<SimpleOwner>() {
      public int compare(SimpleOwner o1, SimpleOwner o2) {
        return compare(o1.getCity(), o2.getCity());
      }
    });
    table.addColumn(cityColumn, "Localité");

    // tel
    Column<SimpleOwner, String> telColumn = new Column<SimpleOwner, String>(new TextCell()) {
      @Override
      public String getValue(SimpleOwner object) {
        return object.getPhoneNumber();
      }
    };

    telColumn.setSortable(true);
    sortHandler.setComparator(telColumn, new SimpleTextComparator<SimpleOwner>() {
      public int compare(SimpleOwner o1, SimpleOwner o2) {
        return compare(o1.getPhoneNumber(), o2.getPhoneNumber());
      }
    });
    table.addColumn(telColumn, "Téléphone");

    // Mobile
    Column<SimpleOwner, String> mobileColumn = new Column<SimpleOwner, String>(new TextCell()) {
      @Override
      public String getValue(SimpleOwner object) {
        return object.getMobileNumber();
      }
    };

    mobileColumn.setSortable(true);
    sortHandler.setComparator(mobileColumn, new SimpleTextComparator<SimpleOwner>() {
      public int compare(SimpleOwner o1, SimpleOwner o2) {
        return compare(o1.getMobileNumber(), o2.getMobileNumber());
      }
    });
    table.addColumn(mobileColumn, "Mobile");

  }
  
  @Override
  protected String getButtonNewLabel() {
    return "Nouveau propriétaire";
  }
  @Override
  protected String getButtonBackLabel() {
    return "Retour vers le dashboard";
  }
}
