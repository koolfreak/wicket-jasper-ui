/**
 * 
 */
package com.ccti.jasper.bridge.login;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ccti.jasper.bridge.JasperBridgeIndex;
import com.ccti.jasper.http.service.JasperObject;
import com.ccti.jasper.http.service.JasperSingle;
import com.ccti.jasper.http.service.utils.CallRemoteService;
import com.ccti.jasper.session.JasperSession;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 2:33:31 PM
 * 
 */
public class JasperLoginPage extends WebPage
{
    @SpringBean private CallRemoteService remoteService;
    
    private static final Log log = LogFactory.getLog(JasperLoginPage.class);
    
    
    public JasperLoginPage()
    {
	final Form form = new Form("login", new CompoundPropertyModel(new JasperObject()));
	add(form);

	form.add(new RequiredTextField("username"));
	form.add(new RequiredTextField("password"));

	final IndicatingAjaxButton login = new IndicatingAjaxButton("butt", form) {

	    @Override
	    protected void onSubmit(AjaxRequestTarget target, Form form)
	    {
		final JasperObject obj = (JasperObject) form.getModelObject();
		// create a report id w/ 50 characters
		obj.setReportId(RandomStringUtils.randomAlphanumeric(50));

		JasperSingle jast = JasperSingle.getInstance();
		jast.setJasperObject(obj);
		JasperSession.get().setJasperObject(obj);
		
		if (remoteService.callRemoteLogin())
		{
		    setResponsePage(JasperBridgeIndex.class);
		}
		else
		{
		    log.debug("Failed to send to report server");
		}

	    }
	};

	form.add(login);
    }

}
