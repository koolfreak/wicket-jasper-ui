package com.ccti.jasper.bridge.servlet;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RestartResponseException;

import com.ccti.jasper.bridge.JasperBridgeIndex;

/**
 * @author emanux
 */
public class PageExpiredPage extends JasperBridgeIndex
{

    public PageExpiredPage()
    {
	

	    final Page page = getPageMap().get(1, 0);
	    throw new RestartResponseAtInterceptPageException(page);

    }

    
}
