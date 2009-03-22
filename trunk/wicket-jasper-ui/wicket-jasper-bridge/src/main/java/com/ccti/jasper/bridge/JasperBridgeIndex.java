package com.ccti.jasper.bridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 6:34:41 PM
 * 
 */
public class JasperBridgeIndex extends JasperBaseBridgePage
{

    private static final Log log = LogFactory.getLog(JasperBridgeIndex.class);

    private String reportId;

    public JasperBridgeIndex()
    {
	add(new JasperMenu("menu"));
	//log.debug("session id = " + JasperSession.get().getId());
	//reportId = JasperSession.get().getJasperObject().getReportId();
	
	log.info("pagemap entry numeric id = "+getPageMapEntry().getNumericId()+" version: "+getVersions());
	int map = getPageMapEntry().getNumericId();
	if(map == 0)
	{
	    final Page page = getPageMap().get(2, 1);
	    throw new RestartResponseAtInterceptPageException(page);
	}
	//log.info("pagemap entry numeric version = "+getCurrentVersionNumber());
    }

    
    public String getReportId()
    {
        return reportId;
    }

}
