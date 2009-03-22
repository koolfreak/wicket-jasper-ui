/**
 * 
 */
package com.ccti.jasper.session;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import com.ccti.jasper.serialized.JasperObject;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 3:01:53 PM
 * 
 */
public class JasperSession extends WebSession
{

    private JasperObject jasperObject;
    
    public JasperSession(Request request)
    {
	super(request);
    }

    public static JasperSession get()
    {
	return (JasperSession) Session.get();
    }
    
    public synchronized JasperObject getJasperObject()
    {
        return jasperObject;
    }

    
    public synchronized void setJasperObject(JasperObject jasperObject)
    {
        this.jasperObject = jasperObject;
        dirty();
    }

}
