package eu.comexis.napoleon.client.core.estate;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractListView;
import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateListView extends AbstractListView<SimpleRealEstate> implements
    RealEstateListPresenter.MyView {

  // key provider object implementation for SimpleRealEstate object
  private static final ProvidesKey<SimpleRealEstate> KEY_PROVIDER =
      new ProvidesKey<SimpleRealEstate>() {
        public Object getKey(SimpleRealEstate item) {
          // Always do a null check.
          return (item == null) ? null : item.getId();
        }
      };

  public RealEstateListView() {

  }

  @Override
  protected ProvidesKey<SimpleRealEstate> getKeyProvider() {
    return KEY_PROVIDER;
  }

  protected void initTableColumns(SingleSelectionModel<SimpleRealEstate> selectionModel,
      ListHandler<SimpleRealEstate> sortHandler) {

    // Name.
    Column<SimpleRealEstate, String> referenceColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getReference();
          }
        };

    referenceColumn.setSortable(true);
    sortHandler.setComparator(referenceColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getReference(), o2.getReference());
      }
    });

    table.addColumn(referenceColumn, "Référence");
    
    // Proprio
    Column<SimpleRealEstate, String> ownerColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getOwner();
          }
        };

    ownerColumn.setSortable(true);
    sortHandler.setComparator(ownerColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getOwner(), o2.getOwner());
      }
    });
    table.addColumn(ownerColumn, "Propriétaire");

    // address.
    Column<SimpleRealEstate, String> addressColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getAddress();
          }
        };

    addressColumn.setSortable(true);
    sortHandler.setComparator(addressColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getAddress(), o2.getAddress());
      }
    });

    table.addColumn(addressColumn, "Adresse");

    
    // Code postal
    Column<SimpleRealEstate, String> postalCodeColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getPostalCode();
          }
        };

    postalCodeColumn.setSortable(true);
    sortHandler.setComparator(postalCodeColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getPostalCode(), o2.getPostalCode());
      }
    });
    table.addColumn(postalCodeColumn, "Code Postal");
    
    // City
    Column<SimpleRealEstate, String> cityColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getCity();
          }
        };

    cityColumn.setSortable(true);
    sortHandler.setComparator(cityColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getCity(), o2.getCity());
      }
    });
    table.addColumn(cityColumn, "Localité");

    // tel
    Column<SimpleRealEstate, String> telColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getPhoneNumber();
          }
        };

    telColumn.setSortable(true);
    sortHandler.setComparator(telColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getPhoneNumber(), o2.getPhoneNumber());
      }
    });
    table.addColumn(telColumn, "Téléphone");

    // Mobile
    Column<SimpleRealEstate, String> mobileColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getMobile();
          }
        };

    mobileColumn.setSortable(true);
    sortHandler.setComparator(mobileColumn, new SimpleTextComparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return compare(o1.getMobile(), o2.getMobile());
      }
    });
    table.addColumn(mobileColumn, "Mobile");

  }
  
  
  @Override
  protected String getButtonNewLabel() {
    return "Nouveau bien immobilier";
  }
  @Override
  protected String getButtonBackLabel() {
    return "Retour vers le dashboard";
  }
}
