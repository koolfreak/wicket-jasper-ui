/**
 * 
 */
package com.ccti.jasper.web.pages.dynamic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.web.common.JasperIndexPage;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 7 - 23:44:12
 */
public class TestDynamicFilter extends JasperIndexPage
{
    
    public TestDynamicFilter()
    {
	final List<String> field = new ArrayList<String>();
	
	final Field[] fields = CustomerSales.class.getDeclaredFields();
	
	for (int i = 0; i < fields.length; i++)
	{
	    field.add(fields[i].getName());
	}
	final Form form = new Form("djform");
	add(form);
	
	final CheckGroup fieldgrp = new CheckGroup("chkflds",new ArrayList<String>());
	
	final ListView fieldview = new ListView("fielddj",field)
	{
	    @Override
	    protected void populateItem(ListItem item)
	    {
		item.add(new Check("sel",item.getModel()));
		item.add(new Label("prop",item.getModelObjectAsString()));
	    }
	};
	
	fieldgrp.add(fieldview);
	form.add(fieldgrp);
	
	final IndicatingAjaxButton submit = new IndicatingAjaxButton("submit", form)
	{
	    
	    @SuppressWarnings("unchecked")
	    @Override
	    protected void onSubmit(AjaxRequestTarget target, Form form)
	    {
		final List<String> selprops = (List<String>) fieldgrp.getModelObject();
		setResponsePage(new TestDynamicReport(selprops));
	    }
	};
	
	form.add(submit);
    }

}
