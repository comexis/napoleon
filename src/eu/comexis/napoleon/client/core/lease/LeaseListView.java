package eu.comexis.napoleon.client.core.lease;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.comexis.napoleon.client.core.AbstractListView;
import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

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
    
   // Status.
    Column<SimpleLease, String> entityStatusColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return (object.getEntityStatus() != null ? UiHelper.translateEnum(
                "EntityStatus_", object.getEntityStatus()) : "");
      }
    };

    entityStatusColumn.setSortable(true);
    sortHandler.setComparator(entityStatusColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {        	 
        return o1.getEntityStatus().compareTo(o2.getEntityStatus());
      }
    });

    table.addColumn(entityStatusColumn, "Statut");

    // Academic Year.
    Column<SimpleLease, String> academicYearColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return object.getAcademicYear();
      }
    };

    academicYearColumn.setSortable(true);
    sortHandler.setComparator(academicYearColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(o1.getAcademicYear(), o2.getAcademicYear());
      }
    });

    table.addColumn(academicYearColumn, "Année académique");
    
    // Tenant.
    Column<SimpleLease, String> tenantColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return object.getTenantName();
      }
    };

    tenantColumn.setSortable(true);
    sortHandler.setComparator(tenantColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(o1.getTenantName(), o2.getTenantName());
      }
    });

    table.addColumn(tenantColumn, "Locataire");
    
    // Entry Date.
    Column<SimpleLease, String> startDateColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return UiHelper.displayDate(object.getStartDate());
      }
    };

    startDateColumn.setSortable(true);
    sortHandler.setComparator(startDateColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(UiHelper.displayDate(o1.getStartDate()), UiHelper.displayDate(o2.getStartDate()));
      }
    });

    table.addColumn(startDateColumn, "Date d'entrée");
    
    // Date de sortie.
    Column<SimpleLease, String> endDateColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return UiHelper.displayDate(object.getEndDate());
      }
    };

    endDateColumn.setSortable(true);
    sortHandler.setComparator(endDateColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(UiHelper.displayDate(o1.getEndDate()), UiHelper.displayDate(o2.getEndDate()));
      }
    });

    table.addColumn(endDateColumn, "Date de sortie");
    
    // Owner.
    Column<SimpleLease, String> ownerColumn = new Column<SimpleLease, String>(new TextCell()) {
      @Override
      public String getValue(SimpleLease object) {
        return object.getOwnerName();
      }
    };

    ownerColumn.setSortable(true);
    sortHandler.setComparator(ownerColumn, new SimpleTextComparator<SimpleLease>() {
      public int compare(SimpleLease o1, SimpleLease o2) {
        return compare(o1.getOwnerName(), o2.getOwnerName());
      }
    });

    table.addColumn(ownerColumn, "Propriétaire");

  }
  
  @Override
  protected String getButtonNewLabel() {
    return "Nouvelle location";
  }
  @Override
  protected String getButtonBackLabel() {
    return "Retour vers le bien immobilier";
  }
}
