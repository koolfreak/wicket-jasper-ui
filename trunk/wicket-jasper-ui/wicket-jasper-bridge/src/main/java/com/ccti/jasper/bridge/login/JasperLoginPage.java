/**
 * 
 */
package com.ccti.jasper.bridge.login;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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
	
	final IndicatingAjaxLink alink = new IndicatingAjaxLink("alink"){
	    @Override
	    public void onClick(AjaxRequestTarget target)
	    {
		// TODO [eman] - 
		System.out.println("here eman");
	    }
	};
	add(alink);
	
	final FeedbackPanel feed = new FeedbackPanel("feedback");
	form.add(feed.setOutputMarkupId(true));
	
	form.add(new RequiredTextField("username"));

	final IndicatingAjaxButton login = new IndicatingAjaxButton("butt", form) {

	    @Override
	    protected void onSubmit(AjaxRequestTarget target, Form form)
	    {
		final JasperObject obj = (JasperObject) form.getModelObject();
		obj.setReportId(RandomStringUtils.randomAlphanumeric(50));

		JasperSingle jast = JasperSingle.getInstance();
		jast.setJasperObject(obj);
		JasperSession.get().setReportId(obj.getReportId());
		
		if (remoteService.callRemoteLogin())
		{
		    setResponsePage(JasperBridgeIndex.class);
		}
		else
		{
		    log.debug("Failed to send to report server");
		}
	    }
	    @Override
	    protected void onError(AjaxRequestTarget target, Form form)
	    {
		target.addComponent(feed);
		super.onError(target, form);
	    }
	};

	form.add(login);
    }

}
