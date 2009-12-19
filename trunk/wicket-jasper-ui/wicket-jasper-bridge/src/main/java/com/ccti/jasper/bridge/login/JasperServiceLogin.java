package com.ccti.jasper.bridge.login;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ccti.jasper.bridge.JasperBridgeIndex;
import com.ccti.jasper.cxf.webservice.JasperCXFService;
import com.ccti.jasper.cxf.webservice.JasperServiceModel;
import com.ccti.jasper.session.JasperSession;

/**
 * @author Emmanuel Nollase - emanux created: May 24, 2009 - 7:29:29 PM
 */
public class JasperServiceLogin extends WebPage
{

	@SpringBean
	private JasperCXFService jasperCXFService;

	private static final Log log = LogFactory.getLog(JasperServiceLogin.class);

	public JasperServiceLogin()
	{
		final Form form = new Form("login", new CompoundPropertyModel(
				new JasperServiceModel()));
		add(form);

		final FeedbackPanel feed = new FeedbackPanel("feedback");
		form.add(feed.setOutputMarkupId(true));

		form.add(new RequiredTextField("userName"));

		final IndicatingAjaxButton login = new IndicatingAjaxButton("butt",
				form)
		{

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form)
			{
				final JasperServiceModel obj = (JasperServiceModel) form
						.getModelObject();
				// create a report id w/ 50 characters
				obj.setReportId(RandomStringUtils.randomAlphanumeric(50));

				boolean _success = jasperCXFService.setUserCredentials(obj);

				if (_success)
				{
					JasperSession.get().setReportId(obj.getReportId());
					setResponsePage(JasperBridgeIndex.class);
				}
				else
				{
					log.info("Failed to set user credentials to report server");
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
