package com.ccti.jasper.bridge;

import com.ccti.jasper.session.JasperSession;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 6:34:41 PM
 * 
 */
public class JasperBridgeIndex extends JasperBaseBridgePage
{

    private final String reportId;

    public JasperBridgeIndex()
    {
	add(new JasperMenu("menu"));
	
	reportId = JasperSession.get().getReportId();
	
    }

    
    public String getReportId()
    {
        return reportId;
    }

}
