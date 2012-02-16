package eu.comexis.napoleon.client.core.owner;

import java.math.BigDecimal;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import eu.comexis.napoleon.client.core.party.PartyUpdateView;
import eu.comexis.napoleon.client.resources.Resources;
import eu.comexis.napoleon.client.utils.UiHelper;
import eu.comexis.napoleon.shared.model.FeeUnit;
import eu.comexis.napoleon.shared.model.Owner;

public class OwnerUpdateView extends PartyUpdateView<Owner> implements OwnerUpdatePresenter.MyView {

  private static final Logger LOG = Logger.getLogger(OwnerUpdateView.class.getName());

  public interface Templates extends SafeHtmlTemplates {

    Templates INSTANCE = GWT.create(Templates.class);

    @Template("<span class='{0}' style='width:100px'>Honoraires :</span><input id='feePlaceHolder'></input><input id='feeUnitPlaceHolder'></input>")
    SafeHtml feeData(String detailCellInputLabelClass);
  }

 
  private TextBox fee;
  private ListBox unit;
  
  public OwnerUpdateView() {
    super();
    init();
  }

  private void init() {
    HTMLPanel additionnalData =
        new HTMLPanel(Templates.INSTANCE.feeData(Resources.INSTANCE.css().detailCellInputLabel()));
    additionnalData.addStyleName(Resources.INSTANCE.css().detailRow());
    additionnalData.addStyleName(Resources.INSTANCE.css().separationRow());

    unit = UiHelper.createListBoxForEnum(FeeUnit.class, "FeeUnit_", false);
    unit.setName("unit");
    unit.addStyleName(Resources.INSTANCE.css().detailCellInputList());
    additionnalData.addAndReplaceElement(unit, "feeUnitPlaceHolder");
    
    fee = new TextBox();
    fee.setName("fee");
    fee.addStyleName(Resources.INSTANCE.css().detailCellInputText());
    additionnalData.addAndReplaceElement(fee, "feePlaceHolder");
    
    setAdditionalData(additionnalData);
  }

  @Override
  public void setData(Owner o) {
    if (LogConfiguration.loggingIsEnabled()) {
      LOG.info("set owner " + o.getId());
    }

    super.setData(o);

    BigDecimal _fee = o.getFee();
    fee.setText((_fee != null ? _fee.toString() : ""));
    UiHelper.selectTextItemBoxByValue(unit, o.getUnit());

  }

  @Override
  public void updateData(Owner o) {
    super.updateData(o);

    o.setFee(fee.getValue());
    o.setUnit(FeeUnit.valueOf(unit.getValue(unit.getSelectedIndex())));

  }

}