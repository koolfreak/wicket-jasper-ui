package com.ccti.jasper.bridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ccti.jasper.componet.JasperContainer;
import com.ccti.jasper.http.service.utils.CallRemoteService;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 1:11:44 PM
 */
public class SimpleReportPage extends JasperBridgeIndex
{
    private static final Log log = LogFactory.getLog(SimpleReportPage.class);
    
    private static final long serialVersionUID = 1L;

    @SpringBean CallRemoteService remoteService;

    public SimpleReportPage()
    {
	final String reportServerUrl = remoteService.jasperServerURL();// "http://localhost:8282/jasper/reports";
	final String reportName = "simple";
	
	final String jasperId = getReportId();
	
	final JasperContainer cont = new JasperContainer("wmc",jasperId) {

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
