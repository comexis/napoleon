package eu.comexis.napoleon.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import eu.comexis.napoleon.client.resources.Css;
import eu.comexis.napoleon.client.resources.Literals;
import eu.comexis.napoleon.client.resources.Resources;

public class LoadingDataIndicator extends Composite {

	public interface Templates extends SafeHtmlTemplates {

		@Template("<div class=\"{0}\"><div class=\"{1}\">{2}</div></div>")
		SafeHtml loading(String styleOuter, String styleInner, String text);
	}

	public LoadingDataIndicator() {
		Templates templates = GWT.create(Templates.class);
		Css css = Resources.INSTANCE.css();
				
		HTMLPanel panel = new HTMLPanel(templates.loading(css.loadingOuter(), css.loadingInner(), Literals.INSTANCE.dataLoading()));
		
		initWidget(panel);
	}
}
