package eu.comexis.napoleon.client.core;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.resources.Resources;

public class MainLayoutView extends ViewImpl implements MainLayoutPresenter.MyView {

  public interface Binder extends UiBinder<Widget, MainLayoutView> {
  }

  public interface Templates extends SafeHtmlTemplates {

    Templates INSTANCE = GWT.create(Templates.class);

    @Template("<li class='{2}'><a href='{1}'>{0}</a></li>")
    SafeHtml action(String label, SafeUri href, String cssClassName);

    @Template("<ul class='{0}'>")
    SafeHtml actionBegin(String cssClassName);

  }

  private static final double HEADER_SIZE = 30;

  @UiField
  Panel centerPanel;

  @UiField
  StackLayoutPanel leftMenu;

  @UiField
  ImageElement logoElement;

  @UiField
  AnchorElement signOutLink;

  @UiField
  Element userNameElement;

  private final Widget widget;

  @Inject
  public MainLayoutView(final Binder binder) {
    widget = binder.createAndBindUi(this);
    init();
  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == MainLayoutPresenter.MAIN_CONTENT) {
      centerPanel.clear();
      centerPanel.add(content);
    } else {
      super.setInSlot(slot, content);
    }
  }

  @Override
  public void setLogo(String logo) {
    GWT.log("set logo " + logo);
    logoElement.setSrc(logo);

  }

  @Override
  public void setLogoutUrl(String logoutUrl) {
    signOutLink.setHref(logoutUrl);

  }

  @Override
  public void setUserName(String userName) {
    userNameElement.setInnerText(userName);
  }

  private void addAction(String url, String actionName, SafeHtmlBuilder builder) {
    builder.append(Templates.INSTANCE.action(actionName, UriUtils.fromTrustedString(url),
        Resources.INSTANCE.css().leftMenuAction()));
  }

  private void beginActions(SafeHtmlBuilder builder) {
    builder.append(Templates.INSTANCE.actionBegin(Resources.INSTANCE.css().leftMenuActionBegin()));
  }

  private void endActions(SafeHtmlBuilder builder) {
    builder.appendHtmlConstant("</ul>");
  }

  private Widget getLeaseActions() {
    Literals l = Literals.INSTANCE;

    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    beginActions(builder);
    addAction("#" + NameTokens.leaselist, l.menuLeaseList(), builder);
    addAction("#" + NameTokens.updateLease + ";uuid=new", l.menuLeaseNew(), builder);
    endActions(builder);

    return new HTMLPanel(builder.toSafeHtml());
  }

  private Widget getOwnerActions() {
    Literals l = Literals.INSTANCE;

    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    beginActions(builder);
    addAction("#" + NameTokens.ownerlist, l.menuOwnerList(), builder);
    addAction("#" + NameTokens.updateOwner + ";uuid=new", l.menuOwnerNew(), builder);
    endActions(builder);

    return new HTMLPanel(builder.toSafeHtml());
  }

  private Widget getPaymentActions() {
    return new Label("To be defined");
  }

  private Widget getRealEstateActions() {
    Literals l = Literals.INSTANCE;

    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    beginActions(builder);
    addAction("#" + NameTokens.realEstatelist, l.menuRealEstateList(), builder);
    addAction("#" + NameTokens.updateRealEstate + ";uuid=new", l.menuRealEstateNew(), builder);
    endActions(builder);

    return new HTMLPanel(builder.toSafeHtml());
  }

  private Widget getTenantActions() {
    Literals l = Literals.INSTANCE;

    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    beginActions(builder);
    addAction("#" + NameTokens.tenantlist, l.menuTenantList(), builder);
    addAction("#" + NameTokens.updateTenant + ";uuid=new", l.menuTenantNew(), builder);
    endActions(builder);

    return new HTMLPanel(builder.toSafeHtml());
  }

  private void init() {
    Literals l = Literals.INSTANCE;

    leftMenu.add(getOwnerActions(), l.menuOwner(), HEADER_SIZE);
    leftMenu.add(getTenantActions(), l.menuTenant(), HEADER_SIZE);
    leftMenu.add(getRealEstateActions(), l.menuRealEstate(), HEADER_SIZE);
    leftMenu.add(getLeaseActions(), l.menuLease(), HEADER_SIZE);
    leftMenu.add(getPaymentActions(), l.menuPayment(), HEADER_SIZE);
    
  }

  @Override
  public void selectLeftMenu(int id) {
    assert id > 0 || id < leftMenu.getWidgetCount() : "Try to display a menu that doesn't exist "+id;
    
    if (id == leftMenu.getVisibleIndex()){
      return;
    }

    leftMenu.setVisible(true);
    
    String selectedCssClass = Resources.INSTANCE.css().leftMenuSelected();
    
    $("."+selectedCssClass, leftMenu).removeClass(selectedCssClass);
    
    leftMenu.showWidget(id);
    leftMenu.getHeaderWidget(id).addStyleName(selectedCssClass);
    
  }

  @Override
  public void hideLeftMenu() {
    leftMenu.setVisible(false);
    
  }
}
