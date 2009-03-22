package com.ccti.jasper.bridge;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.ccti.jasper.bridge.login.JasperLogoutPage;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 20, 2009 - 12:42:37 PM
 *
 */
public class JasperMenu extends Panel
{
    
    public JasperMenu(String id)
    {
	super(id);
	
	add((new Link("simple-html"){

	    @Override
	    public void onClick()
	    {
		setResponsePage(SimpleReportPage.class);
	    }
	    
	}));
	
	add(new BookmarkablePageLink("logout",JasperLogoutPage.class));
	/*{

	    @Override
	    public void onClick()
	    {
		setResponsePage(JasperLogoutPage.class);
	    }
	    
	}));*/
	
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.Component#getStatelessHint()
     */
    @Override
    protected boolean getStatelessHint()
    {
	return false;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.Component#isVersioned()
     */
    @Override
    public boolean isVersioned()
    {
	return false;
    }
    
    
}

