package com.ccti.jasper.web.core;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;

import com.ccti.jasper.http.service.LoggedReportUser;
import com.ccti.jasper.web.common.BaseJasperPage;
import com.ccti.jasper.web.pages.error.ReportNotAuthorizedPage;
import com.ccti.jasper.web.pages.error.ReportNotFoundPage;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 12:53:27 PM
 */
public class ReportRemoteClassCall extends BaseJasperPage
{

	private static final Log log = LogFactory
			.getLog(ReportRemoteClassCall.class);

	public ReportRemoteClassCall(final PageParameters param)
	{
		final String reportClass = param.getString("reportname", null);
		final String reportId = param.getString("reportid", null);

		if (StringUtils.isEmpty(reportClass))
		{
			setResponsePage(ReportNotFoundPage.class);
		}
		else if (!LoggedReportUser.contains(reportId))
		{
			setResponsePage(ReportNotAuthorizedPage.class);
		}
		else
		{
			try
			{
				log.debug("Loading report = " + reportClass);
				final String _clazz = JasperReportFactory
						.getReportClass(reportClass);
				final Class<? extends Page> clazz = Class.forName(_clazz)
						.asSubclass(Page.class);
				setResponsePage(clazz);
			}
			catch (ClassNotFoundException e)
			{
				setResponsePage(ReportNotFoundPage.class);
				log.error("Report not found", e);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				log.error(ex);
			}
		}
	}

	public ReportRemoteClassCall()
	{
		setResponsePage(ReportNotFoundPage.class);
	}
}
