package com.ccti.jasper.bridge;

import com.ccti.jasper.componet.JasperServiceContainer;


/**
 * @author emanux
 */
public class SimpleServiceReportPage extends JasperBridgeIndex
{

    public SimpleServiceReportPage()
    {
	final String reportServerUrl = "http://localhost:8080/jasper/";
	final String reportName = "simple";
	
	final String jasperId = getReportId();
	
	final JasperServiceContainer cont = new JasperServiceContainer("wmc",jasperId) {

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

