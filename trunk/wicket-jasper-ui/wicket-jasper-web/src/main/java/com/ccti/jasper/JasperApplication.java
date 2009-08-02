package com.ccti.jasper;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.QueryStringUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;

import com.ccti.jasper.web.common.JasperIndexPage;
import com.ccti.jasper.web.core.ReportRemoteClassCall;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see wicket.StartJasper.Start#main(String[])
 */
public class JasperApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public JasperApplication()
	{
	}
	
	/**
	 * @see wicket.protocol.http.WebApplication#init()
	 */
	@Override
	protected void init()
	{
	    super.init();
	    
	    	addComponentInstantiationListener(new SpringComponentInjector(this));
	    	
		//getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		getRequestCycleSettings().setGatherExtendedBrowserInfo(true);
		
		mount(new QueryStringUrlCodingStrategy("reports", ReportRemoteClassCall.class));
	}
	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class getHomePage()
	{
		return JasperIndexPage.class;
	}
	
	public static final JasperApplication get()
	{
	    return (JasperApplication) Application.get();
	}

	public Session newSession(Request request, Response response)
	{
	    return new ReportSession(request);
	}
	
	
}
