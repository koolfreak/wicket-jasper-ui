/**
 * 
 */
package com.ccti.jasper.web.pages.dynamic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	final Form<Void> form = new Form<Void>("djform");
	add(form);
	
	final CheckGroup<String> fieldgrp = new CheckGroup<String>("chkflds",new ArrayList<String>());
	
	final ListView<String> fieldview = new ListView<String>("fielddj",field)
	{
	    @Override
	    protected void populateItem(ListItem<String> item)
	    {
		item.add(new Check("sel",item.getDefaultModel()));
		item.add(new Label("prop",StringUtils.capitalize(item.getDefaultModelObjectAsString())) );
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
