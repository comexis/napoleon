package eu.comexis.napoleon.client.core.estate;

import java.util.Comparator;
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

import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateListView extends ViewImpl implements RealEstateListPresenter.MyView {

  public interface Binder extends UiBinder<Widget, RealEstateListView> {
  }

  // key provider object implementation for SimpleRealEstate object
  private static final ProvidesKey<SimpleRealEstate> KEY_PROVIDER =
      new ProvidesKey<SimpleRealEstate>() {
        public Object getKey(SimpleRealEstate item) {
          // Always do a null check.
          return (item == null) ? null : item.getId();
        }
      };
  // list containing the realEstates to display
  private ListDataProvider<SimpleRealEstate> dataProvider;

  @UiField(provided = true)
  CellTable<SimpleRealEstate> realEstateTable;

  @UiField(provided = true)
  SimplePager pager;

  private RealEstateListUiHandlers presenter;
  private final Widget widget;

  @Inject
  public RealEstateListView(final Binder binder) {

    init();
    widget = binder.createAndBindUi(this);

  }

  @Override
  public Widget asWidget() {
    return widget;
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
  public void setData(List<SimpleRealEstate> realEstates) {
    dataProvider.getList().clear();
    dataProvider.getList().addAll(realEstates);
    dataProvider.refresh();

  }

  @Override
  public void setRealEstateListUiHandler(RealEstateListUiHandlers handler) {
    this.presenter = handler;

  }

  private void init() {

    dataProvider = new ListDataProvider<SimpleRealEstate>();

    realEstateTable = new CellTable<SimpleRealEstate>(40, KEY_PROVIDER);

    realEstateTable.setWidth("100%");

    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, true, 50, true);

    // link the pager to the table
    pager.setDisplay(realEstateTable);

    // allow user to navigate in the table with arrows key, selection on the keyboard is done with
    // space key
    realEstateTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    // Add a selection model so we can select cells.
    final SingleSelectionModel<SimpleRealEstate> selectionModel =
        new SingleSelectionModel<SimpleRealEstate>(KEY_PROVIDER);
    realEstateTable.setSelectionModel(selectionModel);

    // call the presenter when user select an realEstate on the list
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        presenter.onSelect(selectionModel.getSelectedObject());
      }
    });

    // Attach a column sort handler to the ListDataProvider to sort the
    // list.
    ListHandler<SimpleRealEstate> sortHandler =
        new ListHandler<SimpleRealEstate>(dataProvider.getList());
    realEstateTable.addColumnSortHandler(sortHandler);

    // Initialize the columns.
    initTableColumns(selectionModel, sortHandler);

    // Connect the table to the data provider.
    dataProvider.addDataDisplay(realEstateTable);

  }

  private void initTableColumns(SingleSelectionModel<SimpleRealEstate> selectionModel,
      ListHandler<SimpleRealEstate> sortHandler) {

    // Client id
    /*
     * Column<SimpleRealEstate, String> clientIdColumn = new Column<SimpleRealEstate, String>( new
     * TextCell()) {
     * 
     * @Override public String getValue(SimpleRealEstate object) { return object.getClientId(); } };
     * 
     * clientIdColumn.setSortable(true); sortHandler.setComparator(clientIdColumn, new
     * Comparator<SimpleRealEstate>() { public int compare(SimpleRealEstate o1, SimpleRealEstate o2)
     * { return o1.getClientId().compareTo(o2.getClientId()); } });
     * 
     * realEstateTable.addColumn(clientIdColumn, "ID");
     */

    // Name.
    Column<SimpleRealEstate, String> referenceColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getReference();
          }
        };

    referenceColumn.setSortable(true);
    sortHandler.setComparator(referenceColumn, new Comparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return o1.getReference().compareTo(o2.getReference());
      }
    });

    realEstateTable.addColumn(referenceColumn, "Référence");

    // address.
    Column<SimpleRealEstate, String> addressColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getAddress();
          }
        };

    addressColumn.setSortable(true);
    sortHandler.setComparator(addressColumn, new Comparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return o1.getAddress().compareTo(o2.getAddress());
      }
    });

    realEstateTable.addColumn(addressColumn, "Adresse");

    // City
    Column<SimpleRealEstate, String> cityColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getCity();
          }
        };

    cityColumn.setSortable(true);
    sortHandler.setComparator(cityColumn, new Comparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return o1.getCity().compareTo(o2.getCity());
      }
    });
    realEstateTable.addColumn(cityColumn, "Localité");

    // tel
    Column<SimpleRealEstate, String> telColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getPhoneNumber();
          }
        };

    telColumn.setSortable(true);
    sortHandler.setComparator(telColumn, new Comparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
      }
    });
    realEstateTable.addColumn(telColumn, "Téléphone");

    // Mobile
    Column<SimpleRealEstate, String> mobileColumn =
        new Column<SimpleRealEstate, String>(new TextCell()) {
          @Override
          public String getValue(SimpleRealEstate object) {
            return object.getMobile();
          }
        };

    mobileColumn.setSortable(true);
    sortHandler.setComparator(mobileColumn, new Comparator<SimpleRealEstate>() {
      public int compare(SimpleRealEstate o1, SimpleRealEstate o2) {
        return o1.getMobile().compareTo(o2.getMobile());
      }
    });
    realEstateTable.addColumn(mobileColumn, "Mobile");

  }
}
