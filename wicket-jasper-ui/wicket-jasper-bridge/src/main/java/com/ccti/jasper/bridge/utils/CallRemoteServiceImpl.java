/**
 * 
 */
package com.ccti.jasper.bridge.utils;

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
    private synchronized boolean sendToReportServer(final PostMethod method)
    {
	log.info("Sending to remote jasper report server");

	// Provide custom retry handler is necessary
	method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	new DefaultHttpMethodRetryHandler(3, false));

	try
	{
	    // Execute the method.
	    int statusCode = httpClient.executeMethod(method);

	    if (statusCode != HttpStatus.SC_OK)
	    {
		System.err.println("Method failed: " + method.getStatusLine());
		return false;
	    }
	    log.info("Finish sending to remote server");

	    return true;

	}
	catch (HttpException e)
	{
	   log.error("Fatal protocol violation: ",e);
	}
	/*catch(TimeoutException e)
	{
	    
	}*/
	catch (IOException e)
	{
	   log.error("Fatal transport error: ", e);
	}
	finally
	{
	    // Release the connection.
	    method.releaseConnection();
	}
	
	return false;
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
    
}
