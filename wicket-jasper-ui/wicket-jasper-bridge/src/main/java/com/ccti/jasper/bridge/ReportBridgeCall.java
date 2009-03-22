package com.ccti.jasper.bridge;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.serialized.JasperObject;
import com.ccti.jasper.serialized.JasperSingle;



/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 10:26:04 AM
 *
 */
public class ReportBridgeCall extends JasperBaseBridgePage
{

    private static final Log log = LogFactory.getLog(JasperBridgeIndex.class);
    
    public ReportBridgeCall()
    {
	final JasperSingle jast = JasperSingle.getInstance();
	
	ObjectOutputStream out = null;
	final ByteArrayOutputStream byteObj = new ByteArrayOutputStream();
	final JasperObject jasperObj = jast.getJasperObject();
	
	try
	{
	    log.info("Connecting to remote report servlet...");
            
            //response.set
            out = new ObjectOutputStream(byteObj);
            
            log.info("Serializing the object...");
            // serialize the object
	    out.writeObject(jasperObj);
	    
	    getResponse().setContentType("application/octet-stream");
	    //final ServletOutputStream outstream = response.getOutputStream();
	    
	   getWebRequestCycle().getWebResponse().getOutputStream().write(byteObj.toByteArray());
	   getWebRequestCycle().getWebResponse().getOutputStream().flush();
	   getWebRequestCycle().getWebResponse().getOutputStream().close();
	    
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	catch(Exception e)
	{
	    
	}
    }

}

