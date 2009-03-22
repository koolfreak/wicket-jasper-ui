package com.ccti.jasper.web.common;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.ccti.jasper.web.pages.SimpleHtmlBarcodeReport;
import com.ccti.jasper.web.pages.SimpleHtmlReport;
import com.ccti.jasper.web.pages.SimplePdfReport;


/**
 * 
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 21, 2009 - 4:21:02 PM
 *
 */
public class JasperMenu extends Panel
{

    
    public JasperMenu(String id)
    {
	super(id);
	
	add(new Link("simple-barcode"){
	    @Override
	    public void onClick()
	    {
		setResponsePage(SimpleHtmlBarcodeReport.class);
	    }
	});
	
	add(new Link("simple-html"){
	    @Override
	    public void onClick()
	    {
		setResponsePage(SimpleHtmlReport.class);
	    }
	});
	
	add(new Link("simple-pdf"){
	    @Override
	    public void onClick()
	    {
		setResponsePage(SimplePdfReport.class);
	    }
	});
	
    }
}

