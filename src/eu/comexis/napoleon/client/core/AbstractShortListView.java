package eu.comexis.napoleon.client.core;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public abstract class AbstractShortListView<T> extends ViewImpl implements
    AbstractShortListPresenter.MyView<T> {

  public interface Binder extends UiBinder<Widget, AbstractShortListView<?>> {
  }

  @UiField(provided = true)
  protected CellTable<T> table;

  @UiField(provided = true)
  SimplePager pager;
  @UiField
  Button btnNew;
  @UiField
  Button btnDelete;
  @UiField
  Element parentTitle;

  // list containing the datas to display
  private ListDataProvider<T> dataProvider;

  private ShortListUiHandlers<T> presenter;
  private final Widget widget;

  @Inject
  public AbstractShortListView() {
    preInit();
    Binder binder = GWT.create(Binder.class);
    widget = binder.createAndBindUi(this);

    init();

  }
  public void bind() {
    $(parentTitle).click(new Function() {
      @Override
      public void f() {
        presenter.showParent();
      }
    });
    
  }
  public void unbind(){
    $(parentTitle).unbind(Event.ONCLICK);
  }
  @Override
  public Widget asWidget() {
    return widget;
  }
  @Override
  public void hideButtons(Boolean visible){
    this.btnDelete.setVisible(visible);
    this.btnNew.setVisible(visible);
  }

  @Override
  public void dataIsLoading() {
    GWT.log("DataIsLoading :"+table.getVisibleRange());
    table.setVisibleRangeAndClearData(table.getVisibleRange(), true);
  }

  @UiHandler("btnDelete")
  public void onDeleteClicked(ClickEvent e) {
    presenter.onButtonDeleteClick();
  }

  @UiHandler("btnNew")
  public void onNewClicked(ClickEvent e) {
    presenter.onButtonNewClick();
  }

  @Override
  public void setData(String title,List<T> datas) {
    parentTitle.setInnerText(title);
    dataProvider.getList().clear();
    dataProvider.getList().addAll(datas);
    dataProvider.refresh();

  }
  public void setListTitle(String title){
    parentTitle.setInnerText(title);
  }

  @Override
  public void setPresenter(ShortListUiHandlers<T> handler) {
    this.presenter = handler;

  }

  protected abstract ProvidesKey<T> getKeyProvider();

  protected abstract String getButtonNewLabel();
  protected abstract String getButtonDeleteLabel();

  protected abstract void initTableColumns(SingleSelectionModel<T> selectionModel,
      ListHandler<T> sortHandler);

  private void init() {

    btnNew.setText(getButtonNewLabel());
    btnDelete.setText(getButtonDeleteLabel());

  }

  @SuppressWarnings("unchecked")
  public void resetFocus() {
    // deselect the current selected object
    SingleSelectionModel<T> selectionModel = (SingleSelectionModel<T>) table.getSelectionModel();
    T selectedObject = selectionModel.getSelectedObject();
    selectionModel.setSelected(selectedObject, false);

    // put focus on the table
    // TODO : doesn't work
    table.setFocus(true);

  }

  private void preInit() {

    dataProvider = new ListDataProvider<T>();

    table = new CellTable<T>(25, getKeyProvider());
    // table.setLoadingIndicator(new LoadingDataIndicator());

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

    dataIsLoading();

  }
}
