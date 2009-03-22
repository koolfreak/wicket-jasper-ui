package com.ccti.jasper.bridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.componet.JasperContainer;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 1:11:44 PM
 */
public class SimpleReportPage extends JasperBridgeIndex
{
    private static final Log log = LogFactory.getLog(SimpleReportPage.class);
    
    private static final long serialVersionUID = 1L;

    private static final String reportName = "com.ccti.jasper.web.pages.SimpleHtmlReport";

    transient JasperContainer cont;
    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *                Page parameters
     */
    public SimpleReportPage()
    {
	final String reportServerUrl = "http://localhost:8282/jasper/reports";
	//final String reportServerUrl = "http://www.google.com.ph/";
	
	//final String jasperId = getReportId();
	log.info("Here in report page");
	
	cont = new JasperContainer("wmc","") {

	    @Override
	    public String getReportClassName()
	    {
		return reportName;
	    }
	    @Override
	    public String getReportServerURL()
	    {
		return reportServerUrl;
	    }
	};
	add(cont);
    }

    
}
