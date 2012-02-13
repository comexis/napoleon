package eu.comexis.napoleon.client.core.tenant;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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

import eu.comexis.napoleon.client.utils.SimpleTextComparator;
import eu.comexis.napoleon.client.widget.LoadingDataIndicator;
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
  public void dataIsLoading() {
    tenantTable.setVisibleRangeAndClearData(tenantTable.getVisibleRange(), true);
  }

  @UiHandler("btnToDashBoard")
  public void onGoHomeClicked(ClickEvent e) {
    presenter.onButtonBackToDashBoardClick();
  }

  @UiHandler("btnNew")
  public void onNewClicked(ClickEvent e) {
    presenter.onButtonNewClick();
  }

  @Override
  public void setData(List<SimpleTenant> tenants) {
    // ok find better...
    if (!dataProvider.getDataDisplays().contains(tenantTable)) {
      dataProvider.addDataDisplay(tenantTable);
    }
    // tenantTable.setRowData(tenants);
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

    // set the table in a loading state
    tenantTable.setLoadingIndicator(new LoadingDataIndicator());

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

  }

  private void initTableColumns(SingleSelectionModel<SimpleTenant> selectionModel,
      ListHandler<SimpleTenant> sortHandler) {

    // Name.
    Column<SimpleTenant, String> nameColumn = new Column<SimpleTenant, String>(new TextCell()) {
      @Override
      public String getValue(SimpleTenant object) {
        return object.getName();
      }
    };

    nameColumn.setSortable(true);
    sortHandler.setComparator(nameColumn, new SimpleTextComparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return compare(o1.getName(), o2.getName());
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
    sortHandler.setComparator(addressColumn, new SimpleTextComparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return compare(o1.getAddress(), o2.getAddress());
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

    sortHandler.setComparator(cpColumn, new SimpleTextComparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return compare(o1.getPostalCode(), o2.getPostalCode());
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
    sortHandler.setComparator(cityColumn, new SimpleTextComparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return compare(o1.getCity(), o2.getCity());
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
    sortHandler.setComparator(telColumn, new SimpleTextComparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return compare(o1.getPhoneNumber(), o2.getPhoneNumber());
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
    sortHandler.setComparator(mobileColumn, new SimpleTextComparator<SimpleTenant>() {
      public int compare(SimpleTenant o1, SimpleTenant o2) {
        return compare(o1.getMobileNumber(), o2.getMobileNumber());
      }
    });
    tenantTable.addColumn(mobileColumn, "Mobile");

  }
}
