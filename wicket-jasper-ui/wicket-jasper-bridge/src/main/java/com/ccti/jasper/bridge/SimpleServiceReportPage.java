package com.ccti.jasper.bridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.componet.JasperContainer;
import com.ccti.jasper.componet.JasperServiceContainer;


/**
 * @author emanux
 */
public class SimpleServiceReportPage extends JasperBridgeIndex
{
 private static final Log log = LogFactory.getLog(SimpleServiceReportPage.class);
    
    private static final long serialVersionUID = 1L;

    private static final String reportName = "simple";

    transient JasperServiceContainer cont;
    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *                Page parameters
     */
    public SimpleServiceReportPage()
    {
	final String reportServerUrl = "http://localhost:8080/jasper/";
	//final String reportServerUrl = "http://www.google.com.ph/";
	
	final String jasperId = getReportId();
	log.info("Here in report page "+ jasperId);
	
	cont = new JasperServiceContainer("wmc",jasperId) {

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

