package eu.comexis.napoleon.client.core;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public abstract class AbstractListView<T> extends ViewImpl implements
    AbstractListPresenter.MyView<T> {

  public interface Binder extends UiBinder<Widget, AbstractListView<?>> {
  }

  @UiField(provided = true)
  protected CellTable<T> table;

  @UiField
  InputElement filter;
  @UiField(provided = true)
  SimplePager pager;

  // list containing the datas to display
  private ListDataProvider<T> dataProvider;

  private ListUiHandlers<T> presenter;
  private final Widget widget;

  @Inject
  public AbstractListView() {
    init();

    Binder binder = GWT.create(Binder.class);
    widget = binder.createAndBindUi(this);
    
    bind();

  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @Override
  public void dataIsLoading() {
    table.setVisibleRangeAndClearData(table.getVisibleRange(), true);
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
  public void setData(List<T> datas) {
    dataProvider.getList().clear();
    dataProvider.getList().addAll(datas);
    dataProvider.refresh();

  }

  @Override
  public void setPresenter(ListUiHandlers<T> handler) {
    this.presenter = handler;

  }

  protected abstract ProvidesKey<T> getKeyProvider();

  protected abstract void initTableColumns(SingleSelectionModel<T> selectionModel,
      ListHandler<T> sortHandler);

  private void bind() {
    $(filter).keyup(new Function(){
      @Override
      public void f() { 
        String filterString = $(filter).val();
GWT.log("Filter with "+filterString);
        presenter.filter(filterString);
      }
    });
    
  }
  
  @UiHandler("reset")
  public void onResetFilter(ClickEvent e){
    presenter.filter(null);
  }
  
  private void init() {

    dataProvider = new ListDataProvider<T>();

    table = new CellTable<T>(40, getKeyProvider());

    table.setWidth("100%");

    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, true, 50, true);

    // link the pager to the table
    pager.setDisplay(table);

    // allow user to navigate in the table with arrows key, selection on the keyboard is done with
    // space key
    table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    // Add a selection model so we can select cells.
    final SingleSelectionModel<T> selectionModel = new SingleSelectionModel<T>(getKeyProvider());
    table.setSelectionModel(selectionModel);

    // call the presenter when user select an realEstate on the list
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        presenter.onSelect(selectionModel.getSelectedObject());
      }
    });

    // Attach a column sort handler to the ListDataProvider to sort the
    // list.
    ListHandler<T> sortHandler = new ListHandler<T>(dataProvider.getList());
    table.addColumnSortHandler(sortHandler);

    // Initialize the columns.
    initTableColumns(selectionModel, sortHandler);

    // Connect the table to the data provider.
    dataProvider.addDataDisplay(table);

  }
}
