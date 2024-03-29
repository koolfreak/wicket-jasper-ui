/**
 * 
 */
package com.ccti.jasper.http.service.utils;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 2, 2009 - 11:12:37 AM
 * 
 */
public class CallRemoteServiceImpl implements CallRemoteService
{
    private static final Log log = LogFactory.getLog(CallRemoteServiceImpl.class);

    private String loginReportURL;
    
    private String logoutReportURL;
    
    private HttpClient httpClient;
    
    private String jasperServerURL;
    /*
     * (non-Javadoc)
     * @see com.ccti.jasper.bridge.utils.CallRemoteService#callRemoteLogin()
     */
    public boolean callRemoteLogin()
    {
	PostMethod method = new PostMethod(getLoginReportURL());
	return sendToReportServer(method);
    }

    /*
     * (non-Javadoc)
     * @see com.ccti.jasper.bridge.utils.CallRemoteService#callRemoteLogout(java.lang.String)
     */
    public boolean callRemoteLogout(String id)
    {
	PostMethod method = new PostMethod(getLogoutReportURL());
	method.addParameter("reportid", id);
	return sendToReportServer(method);
    }

    /**
     * 
     * @param method
     * @return
     */
    private boolean sendToReportServer(final PostMethod method)
    {
	boolean _success = false;
	// Provide custom retry handler is necessary
	method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	new DefaultHttpMethodRetryHandler(3, false));

	 long start = System.currentTimeMillis();
	try
	{
	    log.debug("Start executing authencation in remote server: "+start);
	    // Execute the method.
	    int statusCode = httpClient.executeMethod(method);

	    if (statusCode != HttpStatus.SC_OK)
	    {
		log.warn("Method failed: " + method.getStatusLine());
		_success = false;
	    }
	    else
	    {
		_success = true;
	    }
	    log.debug("Finish sending authenticating to remote server");
	}
	catch (HttpException e)
	{
	   log.error("Fatal protocol violation: " + e);
	   _success = false;
	}
	catch (IOException e)
	{
	    _success = false;
	   log.error("Fatal transport error: " + e);
	}
	finally
	{
	    // Release the connection.
	    method.releaseConnection();
	}
	long end = System.currentTimeMillis() - start;
	log.debug("Finish server routines..it takes: "+ end +" milliseconds.");
	return _success;
    }

    
    /**
     * @return the loginReportURL
     */
    public String getLoginReportURL()
    {
        return loginReportURL;
    }
    /**
     * @param loginReportURL the loginReportURL to set
     */
    public void setLoginReportURL(String loginReportURL)
    {
        this.loginReportURL = loginReportURL;
    }
    /**
     * @return the logoutReportURL
     */
    public String getLogoutReportURL()
    {
        return logoutReportURL;
    }
    /**
     * @param logoutReportURL the logoutReportURL to set
     */
    public void setLogoutReportURL(String logoutReportURL)
    {
        this.logoutReportURL = logoutReportURL;
    }
    /**
     * @param httpClient the httpClient to set
     */
    public void setHttpClient(HttpClient httpClient)
    {
        this.httpClient = httpClient;
    }
    
    /**
     * @param jasperServerURL the jasperServerURL to set
     */
    public void setJasperServerURL(String jasperServerURL)
    {
        this.jasperServerURL = jasperServerURL;
    }

    public String jasperServerURL()
    {
	if(null == jasperServerURL || jasperServerURL.equals("") )
	{
	    throw new IllegalArgumentException("Jasper url must not be empty");
	}
	return jasperServerURL;
    }
    
}
