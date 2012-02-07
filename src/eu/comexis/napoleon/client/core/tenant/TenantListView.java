package eu.comexis.napoleon.client.core.tenant;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public class TenantListView extends ViewImpl implements TenantListPresenter.MyView {

  public interface Binder extends UiBinder<Widget, TenantListView> {
  }

  // key provider object implementation for SimpleTenant object
  private static final ProvidesKey<SimpleTenant> KEY_PROVIDER = new ProvidesKey<SimpleTenant>() {
    public Object getKey(SimpleTenant item) {
      // Always do a null check.
      return (item == null) ? null : item.getId();
    }
  };
  // list containing the tenants to display
  private ListDataProvider<SimpleTenant> dataProvider;

  @UiField(provided = true)
  CellTable<SimpleTenant> tenantTable;

  @UiField(provided = true)
  SimplePager pager;

  private TenantListUiHandlers presenter;
  private final Widget widget;

  @Inject
  public TenantListView(final Binder binder) {

    init();
    widget = binder.createAndBindUi(this);

  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @Override
  public void setData(List<SimpleTenant> tenants) {
    dataProvider.getList().clear();
    dataProvider.getList().addAll(tenants);
    dataProvider.refresh();

  }

  @Override
  public void setTenantListUiHandler(TenantListUiHandlers handler) {
    this.presenter = handler;

  }

  private void init() {

    dataProvider = new ListDataProvider<SimpleTenant>();

    tenantTable = new CellTable<SimpleTenant>(40, KEY_PROVIDER);

    tenantTable.setWidth("100%");

    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, true, 50, true);

    // link the pager to the table
    pager.setDisplay(tenantTable);

    // allow user to navigate in the table with arrows key, selection on the keyboard is done with
    // space key
    tenantTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    // Add a selection model so we can select cells.
    final SingleSelectionModel<SimpleTenant> selectionModel =
        new SingleSelectionModel<SimpleTenant>(KEY_PROVIDER);
    tenantTable.setSelectionModel(selectionModel);

    // call the presenter when user select an tenant on the list
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        presenter.onSelect(selectionModel.getSelectedObject());
      }
    });

    // Attach a column sort handler to the ListDataProvider to sort the
    // list.
    ListHandler<SimpleTenant> sortHandler = new ListHandler<SimpleTenant>(dataProvider.getList());
    tenantTable.addColumnSortHandler(sortHandler);

    // Initialize the columns.
    initTableColumns(selectionModel, sortHandler);

    // Connect the table to the data provider.
    dataProvider.addDataDisplay(tenantTable);

  }

  private void initTableColumns(SingleSelectionModel<SimpleTenant> selectionModel,
      ListHandler<SimpleTenant> sortHandler) {

    // Client id
    /*
     * Column<SimpleTenant, String> clientIdColumn = new Column<SimpleTenant, String>( new
     * TextCell()) {
     * 
     * @Override public String getValue(SimpleTenant object) { return object.getClientId(); } };
     * 
     * clientIdColumn.setSortable(true); sortHandler.setComparator(clientIdColumn, new
     * Comparator<SimpleTenant>() { public int compare(SimpleTenant o1, SimpleTenant o2) { return
     * o1.getClientId().compareTo(o2.getClientId()); } });
     * 
     * tenantTable.addColumn(clientIdColumn, "ID");
     */

    // Name.
    Column<SimpleTenant, String> nameColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getName();
      }
    };

    nameColumn.setSortable(true);
    sortHandler.setComparator(nameColumn, new Comparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });

    tenantTable.addColumn(nameColumn, "Nom");

    // address.
    Column<SimpleTenant, String> addressColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getAddress();
      }
    };

    addressColumn.setSortable(true);
    sortHandler.setComparator(addressColumn, new Comparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return o1.getAddress().compareTo(o2.getAddress());
      }
    });

    tenantTable.addColumn(addressColumn, "Adresse");

    // Postal Code.
    Column<SimpleTenant, String> cpColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getPostalCode();
      }
    };

    cpColumn.setSortable(true);

    sortHandler.setComparator(cpColumn, new Comparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return o1.getPostalCode().compareTo(o2.getPostalCode());
      }
    });
    tenantTable.addColumn(cpColumn, "Code Postal");
    tenantTable.setColumnWidth(cpColumn, "20%");

    // City
    Column<SimpleTenant, String> cityColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getCity();
      }
    };

    cityColumn.setSortable(true);
    sortHandler.setComparator(cityColumn, new Comparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return o1.getCity().compareTo(o2.getCity());
      }
    });
    tenantTable.addColumn(cityColumn, "Localité");

    // tel
    Column<SimpleTenant, String> telColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getPhoneNumber();
      }
    };

    telColumn.setSortable(true);
    sortHandler.setComparator(telColumn, new Comparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
      }
    });
    tenantTable.addColumn(telColumn, "Téléphone");

    // Mobile
    Column<SimpleTenant, String> mobileColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getMobileNumber();
      }
    };

    mobileColumn.setSortable(true);
    sortHandler.setComparator(mobileColumn, new Comparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return o1.getMobileNumber().compareTo(o2.getMobileNumber());
      }
    });
    tenantTable.addColumn(mobileColumn, "Mobile");

  }
}
