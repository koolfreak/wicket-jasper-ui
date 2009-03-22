/**
 * 
 */
package com.ccti.jasper.web.common;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.WebPage;

import com.ccti.jasper.JasperApplication;
import com.ccti.jasper.ReportSession;
import com.ccti.jasper.serialized.JasperObject;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:46:02 PM
 * 
 */
public abstract class BaseJasperPage extends WebPage
{
 
    private static final Log log = LogFactory.getLog(BaseJasperPage.class);
    
    public BaseJasperPage()
    {
	
    }

    public ServletContext getContext()
    {
        return JasperApplication.get().getServletContext();
    }
    
    public void addToSession(JasperObject object)
    {
	log.info("adding user to report session");
	ReportSession.get().setJasperObject(object);
    }
}
