package com.ccti.jasper.bridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;

import com.ccti.jasper.session.JasperSession;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 6:34:41 PM
 * 
 */
public class JasperBridgeIndex extends JasperBaseBridgePage
{

    private static final Log log = LogFactory.getLog(JasperBridgeIndex.class);

    private final String reportId;

    public JasperBridgeIndex()
    {
	add(new JasperMenu("menu"));
	
	reportId = JasperSession.get().getJasperObject().getReportId();
	
    }

    
    public String getReportId()
    {
        return reportId;
    }

}
