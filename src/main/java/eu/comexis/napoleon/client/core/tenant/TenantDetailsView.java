package eu.comexis.napoleon.client.core.tenant;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import eu.comexis.napoleon.client.core.party.PartyDetailsView;
import eu.comexis.napoleon.client.resources.Css;
import eu.comexis.napoleon.client.resources.Resources;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class TenantDetailsView extends PartyDetailsView<Tenant> implements TenantDetailsPresenter.MyView {
  
  public TenantDetailsView() {
   super();
  }
  @Override
  public void setData(Tenant t) {
    super.setData(t);
    displayRentalAddress(t.getRealEstate());
  }
  public interface Templates extends SafeHtmlTemplates {
    Templates INSTANCE = GWT.create(Templates.class);
    @Template("<span class='{2}'>{0} :</span><span class='{3}'>{1}</span>")
    SafeHtml span(String label, String value, String detailCellLabelCssClass, String detailCellValueCssClass);
    @Template("<div class='{1} {2}'>{0}</div>")
    SafeHtml div(SafeHtml span,String detailRowCssClass, String separationRowCssClass);
  }
  private void displayRentalAddress(SimpleRealEstate e) {
    if (e!=null){
      Css css = Resources.INSTANCE.css(); 
      Templates t = Templates.INSTANCE;
      SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder.append(t.span("Adresse de location", e.getAddress(), css.detailCellLabel(), css.detailCellValue()));
      builder.append(t.span("Code Postal", e.getPostalCode(), css.detailCellLabel(), css.detailCellValue()));
      builder.append(t.span("Localité", e.getCity(), css.detailCellLabel(), css.detailCellValue()));
      SafeHtml outer = Templates.INSTANCE.div(builder.toSafeHtml(),css.detailRow(), css.separationRow());
      setAddressAdditionnalData(outer);
    }
  }
  
}