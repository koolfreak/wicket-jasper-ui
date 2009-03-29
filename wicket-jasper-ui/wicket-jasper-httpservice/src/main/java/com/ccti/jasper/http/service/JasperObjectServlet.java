/**
 * 
 */
package com.ccti.jasper.http.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 5:58:13 PM
 * 
 */
public class JasperObjectServlet extends HttpServlet
{

    private static final Log log = LogFactory.getLog(JasperObjectServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
    {
	
	ObjectOutputStream out = null;
	final ByteArrayOutputStream byteObj = new ByteArrayOutputStream();
	final JasperObject jasperObj = JasperSingle.getInstance().getJasperObject();
	
	try
	{
	  
            out = new ObjectOutputStream(byteObj);
            
	    out.writeObject(jasperObj);
	    
	    resp.setHeader("Cache-Control", "no-store");
	    resp.setHeader("Pragma", "no-cache");
	    resp.setContentType("application/octet-stream");
	    final ServletOutputStream outstream = resp.getOutputStream();
	    
	    outstream.write(byteObj.toByteArray());
	    outstream.flush();
	    outstream.close();
	    
	}
	catch (MalformedURLException e)
	{
	  log.error(e);
	}
	catch (IOException e)
	{
	  log.error(e);
	}
    }

    
}
