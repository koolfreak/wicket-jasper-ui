package com.ccti.jasper.bridge.base;

import org.apache.wicket.markup.html.link.Link;

import com.ccti.jasper.bridge.JasperBaseBridgePage;
import com.ccti.jasper.bridge.login.JasperLoginPage;
import com.ccti.jasper.bridge.login.JasperServiceLogin;


/**
 * @author emanux
 */
public class JasperBridgeIndexPage extends JasperBaseBridgePage
{
    
    public JasperBridgeIndexPage()
    {
	add(new Link("http"){
	    @Override
	    public void onClick()
	    {
		setResponsePage(JasperLoginPage.class);
	    }
	});
	
	add(new Link("webservice"){
	    @Override
	    public void onClick()
	    {
		setResponsePage(JasperServiceLogin.class);
	    }
	});
    }
}

