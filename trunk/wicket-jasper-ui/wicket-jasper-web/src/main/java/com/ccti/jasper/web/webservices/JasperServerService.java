/**
 * 
 */
package com.ccti.jasper.web.webservices;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.cxf.webservice.JasperCXFService;
import com.ccti.jasper.cxf.webservice.JasperServiceModel;
import com.ccti.jasper.cxf.webservice.LoggedServiceUser;
import com.ccti.jasper.web.core.JasperReportFactory;
import com.ccti.jasper.web.pages.error.ReportNotAuthorizedPage;
import com.ccti.jasper.web.pages.error.ReportNotFoundPage;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 27, 2009 - 10:48:40 PM
 */
public class JasperServerService implements JasperCXFService
{
    
    private static final Log log = LogFactory.getLog(JasperServerService.class);

    public String callReportName(String reportName, String reportId)
    {
	String _clazz = StringUtils.EMPTY;
	
	final boolean _user_exist = verifyReportUser(reportId);

	if (_user_exist)
	{
	    try
	    {
		log.debug("Loading report = " + reportName);
		_clazz = JasperReportFactory.getReportClass(reportName);
		Class.forName(_clazz);
	    }
	    catch (ClassNotFoundException e)
	    {
		_clazz = ReportNotFoundPage.class.getName();
	    }
	}
	else
	{
	   _clazz = ReportNotAuthorizedPage.class.getName();
	}

	return _clazz;
    }

    public boolean removeUserCredentials(String reportId)
    {
	boolean _success = false;
	if ( verifyReportUser(reportId)  )
	{
	    LoggedServiceUser.removeUser(reportId);
	    log.debug("remove reportid = "+reportId);
	    _success = true;
	}
	LoggedServiceUser.printLoggedUser();
	
	return _success;
    }

    public boolean setUserCredentials(JasperServiceModel model)
    {
	boolean _succezz = false;
	try
	{
	    LoggedServiceUser.addReportuser(model);
	    LoggedServiceUser.printLoggedUser();
	    _succezz = true;
	}
	catch (RuntimeException e)
	{
	    e.printStackTrace();
	    _succezz = false;
	}
	
	return _succezz;
    }

    public boolean verifyReportUser(String reportId)
    {
	return LoggedServiceUser.contains(reportId);
    }

}
