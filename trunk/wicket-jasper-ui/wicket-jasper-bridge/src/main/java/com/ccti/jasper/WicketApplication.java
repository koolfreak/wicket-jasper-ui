package com.ccti.jasper;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.ccti.jasper.bridge.base.JasperBridgeIndexPage;
import com.ccti.jasper.session.JasperSession;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.myproject.StartJasperBridge#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class getHomePage()
	{
		return JasperBridgeIndexPage.class;
	}
	
	

	@Override
	protected void init()
	{
	    // TODO Auto-generated method stub
	    super.init();
	    
	    addComponentInstantiationListener(new SpringComponentInjector(this));
	    
	    getPageSettings().setAutomaticMultiWindowSupport(true);
	}

	@Override
	public Session newSession(Request request, Response response)
	{
	    return new JasperSession(request);
	}

	
}
