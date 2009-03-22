/**
 * 
 */
package com.ccti.jasper;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.ccti.jasper.serialized.JasperObject;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 10:58:08 AM
 * 
 */
public class ReportSession extends WebSession
{
    private JasperObject jasperObject;

    
    public JasperObject getJasperObject()
    {
        return jasperObject;
    }

    
    public void setJasperObject(JasperObject jasperObject)
    {
        this.jasperObject = jasperObject;
        dirty();
    }

    public ReportSession(Request request)
    {
	super(request);
    }
    
    public static ReportSession get()
    {
	return (ReportSession) WebSession.get();
    }

}
