/**
 * 
 */
package com.ccti.jasper.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.web.utils.LoggedReportUser;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 2, 2009 - 10:57:29 AM
 * 
 */
public class JasperLogOutServlet extends HttpServlet
{

    private static final Log log = LogFactory.getLog(JasperLogOutServlet.class);
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
    {
	final String reportId = req.getParameter("reportid");
	if(StringUtils.isEmpty(reportId))
	{
	   log.debug("ID must not be empty");
	}
	else
	{
	    log.debug("removing user...");
	    LoggedReportUser.removeUser(reportId);
	}
	LoggedReportUser.printLoggedUser();
    }

    
}
