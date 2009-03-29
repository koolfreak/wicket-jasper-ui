package com.ccti.jasper.http.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 10:20:48 AM
 *
 */
public class JasperLogInServlet extends HttpServlet
{
    private static final Log log = LogFactory.getLog(JasperLogInServlet.class);
    
    public JasperLogInServlet()
    {
	
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
    {
	
	
	ObjectInputStream ins = null;
	
	final String reportServerUrl = getInitParameter("bridge-url");//JasperConfiguration.getString("carnelian-bridge-url", null);//"http://localhost:8070/bridge";
	if(StringUtils.isEmpty(reportServerUrl))
	{
	    throw new IllegalArgumentException("Bridge Server Url must not be empty ");
	}
	try
	{
	    URL fromMain = new URL(reportServerUrl);
	    log.info("Connecting to application bridge servlet...");
	    URLConnection connect = fromMain.openConnection();
	    connect.setDoOutput(true);
	    connect.setDoInput(true);
	    // Don't used a cached version of URL connection.
            connect.setUseCaches(false);
            connect.setDefaultUseCaches(false);
            // Specify the content type that we will send binary data
            connect.setRequestProperty("Content-Type", "application/octet-stream");
            
            ins = new ObjectInputStream(connect.getInputStream());
            
            JasperObject obj = (JasperObject) ins.readObject();
            ins.close();
            
            LoggedReportUser.addReportuser(obj); 
            LoggedReportUser.printLoggedUser();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
    }
    
}

