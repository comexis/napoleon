package eu.comexis.napoleon.client.core.owner;

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

import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerListView extends ViewImpl implements
		OwnerListPresenter.MyView {

	public interface Binder extends UiBinder<Widget, OwnerListView> {
	}

	// key provider object implementation for SimpleOwner object
	private static final ProvidesKey<SimpleOwner> KEY_PROVIDER = new ProvidesKey<SimpleOwner>() {
		public Object getKey(SimpleOwner item) {
			// Always do a null check.
			return (item == null) ? null : item.getId();
		}
	};
	// list containing the owners to display
	private ListDataProvider<SimpleOwner> dataProvider;

	@UiField(provided = true)
	CellTable<SimpleOwner> ownerTable;

	@UiField(provided = true)
	SimplePager pager;

	private OwnerListUiHandlers presenter;
	private final Widget widget;

	@Inject
	public OwnerListView(final Binder binder) {

		init();
		widget = binder.createAndBindUi(this);

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	private void init() {

		dataProvider = new ListDataProvider<SimpleOwner>();

		ownerTable = new CellTable<SimpleOwner>(40, KEY_PROVIDER);

		ownerTable.setWidth("100%");

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, true, 50,
				true);
		
		//link the pager to the table
		pager.setDisplay(ownerTable);

		//allow user to navigate in the table with arrows key, selection on the keyboard is done with space key
		ownerTable
				.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<SimpleOwner> selectionModel = new SingleSelectionModel<SimpleOwner>(
				KEY_PROVIDER);
		ownerTable.setSelectionModel(selectionModel);

		//call the presenter when user select an owner on the list
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						presenter.onSelect(selectionModel.getSelectedObject());
					}
				});

		// Attach a column sort handler to the ListDataProvider to sort the
		// list.
		ListHandler<SimpleOwner> sortHandler = new ListHandler<SimpleOwner>(
				dataProvider.getList());
		ownerTable.addColumnSortHandler(sortHandler);

		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(ownerTable);

	}

	private void initTableColumns(
			SingleSelectionModel<SimpleOwner> selectionModel,
			ListHandler<SimpleOwner> sortHandler) {

		// Client id
		Column<SimpleOwner, String> clientIdColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getClientId();
			}
		};

		clientIdColumn.setSortable(true);
		sortHandler.setComparator(clientIdColumn,
				new Comparator<SimpleOwner>() {
					public int compare(SimpleOwner o1, SimpleOwner o2) {
						return o1.getClientId().compareTo(o2.getClientId());
					}
				});

		ownerTable.addColumn(clientIdColumn, "ID");

		// Name.
		Column<SimpleOwner, String> nameColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getName();
			}
		};

		nameColumn.setSortable(true);
		sortHandler.setComparator(nameColumn, new Comparator<SimpleOwner>() {
			public int compare(SimpleOwner o1, SimpleOwner o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		ownerTable.addColumn(nameColumn, "Nom");

		// address.
		Column<SimpleOwner, String> addressColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getAddress();
			}
		};

		addressColumn.setSortable(true);
		sortHandler.setComparator(addressColumn, new Comparator<SimpleOwner>() {
			public int compare(SimpleOwner o1, SimpleOwner o2) {
				return o1.getAddress().compareTo(o2.getAddress());
			}
		});

		ownerTable.addColumn(addressColumn, "Adresse");

		// Postal Code.
		Column<SimpleOwner, String> cpColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getPostalCode();
			}
		};

		cpColumn.setSortable(true);
		
		sortHandler.setComparator(cpColumn, new Comparator<SimpleOwner>() {
			public int compare(SimpleOwner o1, SimpleOwner o2) {
				return o1.getPostalCode().compareTo(o2.getPostalCode());
			}
		});
		ownerTable.addColumn(cpColumn, "Code Postal");
		ownerTable.setColumnWidth(cpColumn, "20%");

		// City
		Column<SimpleOwner, String> cityColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getCity();
			}
		};

		cityColumn.setSortable(true);
		sortHandler.setComparator(cityColumn, new Comparator<SimpleOwner>() {
			public int compare(SimpleOwner o1, SimpleOwner o2) {
				return o1.getCity().compareTo(o2.getCity());
			}
		});
		ownerTable.addColumn(cityColumn, "Localité");

		// tel
		Column<SimpleOwner, String> telColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getTelephone();
			}
		};

		telColumn.setSortable(true);
		sortHandler.setComparator(telColumn, new Comparator<SimpleOwner>() {
			public int compare(SimpleOwner o1, SimpleOwner o2) {
				return o1.getTelephone().compareTo(o2.getTelephone());
			}
		});
		ownerTable.addColumn(telColumn, "Téléphone");

		// Mobile
		Column<SimpleOwner, String> mobileColumn = new Column<SimpleOwner, String>(
				new TextCell()) {
			@Override
			public String getValue(SimpleOwner object) {
				return object.getMobile();
			}
		};

		mobileColumn.setSortable(true);
		sortHandler.setComparator(mobileColumn, new Comparator<SimpleOwner>() {
			public int compare(SimpleOwner o1, SimpleOwner o2) {
				return o1.getMobile().compareTo(o2.getMobile());
			}
		});
		ownerTable.addColumn(mobileColumn, "Mobile");

	}

	@Override
	public void setData(List<SimpleOwner> owners) {
		dataProvider.getList().addAll(owners);
		dataProvider.refresh();

	}

	@Override
	public void setOwnerListUiHandler(OwnerListUiHandlers handler) {
		this.presenter = handler;

	}
}
