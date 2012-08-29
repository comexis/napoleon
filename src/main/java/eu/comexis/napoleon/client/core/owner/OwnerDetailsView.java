package eu.comexis.napoleon.client.core.owner;

import java.math.BigDecimal;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.inject.Inject;

import eu.comexis.napoleon.client.core.party.PartyDetailsView;
import eu.comexis.napoleon.client.place.NameTokens;
import eu.comexis.napoleon.client.resources.Css;
import eu.comexis.napoleon.client.resources.Resources;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;


public class OwnerDetailsView extends PartyDetailsView<Owner> implements OwnerDetailsPresenter.MyView {

  
  public interface Templates extends SafeHtmlTemplates {

    Templates INSTANCE = GWT.create(Templates.class);
    
    @Template("<ul class='{0}'>")
    SafeHtml estateOuterBegin(String cssClassName);
    
    @Template("<li class='{2}'><a href='{1}'>{0}</a></li>")
    SafeHtml estateInner(String label, SafeUri href, String cssClassName);
    
    @Template("<div class='{2} {3}'><span class='{4}'>{1}</span><span id='fee' class='{5}'>{0}</span></div>")
    SafeHtml row(SafeHtml fee, String label, String detailRowCssClass, String separationRowCssClass, String detailCellLabelCssClass, String detailCellValueCssClass);
  }

  @Inject
  public OwnerDetailsView() {
   super();
   init();

  }

  private void init() {
      }

  @Override
  public void setData(Owner o) {
    super.setData(o);
    
    displayFee(o);
    displayEstate(o);
   

  }

  private void displayFee(Owner o) {
    
    BigDecimal feeBd = o.getFee();
    String fee = "";
    
    
    if (feeBd != null){
      fee = feeBd + " " + UiHelper.translateEnum("FeeUnit_", o.getUnit());
    }
    
    Css css = Resources.INSTANCE.css();   
    setTopAdditionnalData(Templates.INSTANCE.row(SafeHtmlUtils.fromSafeConstant(fee), "Honoraires :", css.detailRow(), css.separationRow(), css.detailCellLabel(), css.detailCellValue()));

    

   
  }
  
  private void displayEstate(Owner o) {
    
    Css css = Resources.INSTANCE.css(); 
    Templates t = Templates.INSTANCE;
    
    List<SimpleRealEstate> realEstates = o.getEstates();
    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    
    if (realEstates != null && !realEstates.isEmpty()){
      
      builder.append(t.estateOuterBegin(css.outerRealEstateList()));
      
      for(SimpleRealEstate e:realEstates){
        String href = "#"+NameTokens.realEstate+";uuid="+e.getId();
        builder.append(t.estateInner(e.getReference(), UriUtils.fromString(href), css.innerRealEstateList()));
      }
      
      builder.appendHtmlConstant("</ul>");
    }

    SafeHtml outer = Templates.INSTANCE.row(builder.toSafeHtml(), "Biens immobiliers :", css.detailRow(), css.separationRow(), css.detailCellLabel(), css.detailCellValue());

    setBottomAdditionnalData(outer);
    
  }
}