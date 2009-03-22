package com.ccti.jasper.web.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.serialized.JasperObject;
import com.ccti.jasper.web.utils.LoggedReportUser;



/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 10:20:48 AM
 *
 */
public class ReportRemoteCall extends BaseJasperPage
{
    private static final Log log = LogFactory.getLog(JasperIndexPage.class);
    
    public ReportRemoteCall()
    {
	ObjectInputStream ins = null;
	final String reportServerUrl = "http://localhost:8080/bridge";
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

