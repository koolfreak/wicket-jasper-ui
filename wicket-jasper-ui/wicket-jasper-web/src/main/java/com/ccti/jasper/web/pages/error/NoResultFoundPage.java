package com.ccti.jasper.web.pages.error;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;


/**
 * @author eman
 */
public class NoResultFoundPage extends WebPage
{

    
    public NoResultFoundPage()
    {
	add(new Label("noresult","Sorry, result is empty."));
    }
}

