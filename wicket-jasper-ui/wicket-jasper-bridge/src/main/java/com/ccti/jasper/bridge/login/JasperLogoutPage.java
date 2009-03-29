package com.ccti.jasper.bridge.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ccti.jasper.bridge.JasperBridgeIndex;
import com.ccti.jasper.http.service.utils.CallRemoteService;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 2, 2009 - 10:45:37 AM
 *
 */
public class JasperLogoutPage extends JasperBridgeIndex
{
    private static final Log log = LogFactory.getLog(JasperLogoutPage.class);
    
    @SpringBean CallRemoteService remoteService;
    
    public JasperLogoutPage()
    {
	// remove user from remote report server
	final String id = getReportId();
	log.debug("Removing report id = "+id);
	remoteService.callRemoteLogout(id);
    }
}

