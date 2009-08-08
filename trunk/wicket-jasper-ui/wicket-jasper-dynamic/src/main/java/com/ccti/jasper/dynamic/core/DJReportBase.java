/**
 * 
 */
package com.ccti.jasper.dynamic.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;


/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 6 - 18:31:51
 */
public abstract class DJReportBase implements Serializable
{

    protected DynamicReportBuilder reportBuilder;
    
    public DJReportBase()
    {
	initilizeBuilder();
    }
    
    public abstract void initilizeBuilder();
    
    public abstract String getDynamicReportName();
    
    public abstract LayoutManager getReportLayout();

    public DynamicReportBuilder getReportBuilder()
    {
        return reportBuilder;
    }

    /**
     * 
     * @param props - List of property selected by user
     * @param clazz - corresponding class for the report
     */
    public void addReportColumns(List<String> props, Class<?> clazz)
    {
	final Field[] fields = clazz.getDeclaredFields();
	

	for (String selprop : props)
	{
	    final ColumnBuilder djcolumn = ColumnBuilder.getInstance();

	    for (int i = 0; i < fields.length; i++)
	    {
		if ( StringUtils.equals(selprop, fields[i].getName()))
		{
		    final String propertyName = fields[i].getName();
		    final Class<?> fieldtype = fields[i].getType();
		    final String valueClassName = fieldtype.getName();

		    djcolumn.setColumnProperty(propertyName, valueClassName);
		    djcolumn.setTitle(StringUtils.capitalize(propertyName));
		    djcolumn.setWidth(20);

		    try
		    {
			getReportBuilder().addColumn(djcolumn.build());
		    } 
		    catch (ColumnBuilderException e)
		    {
			e.printStackTrace();
		    }
		}
	    }
	}

    }
}
