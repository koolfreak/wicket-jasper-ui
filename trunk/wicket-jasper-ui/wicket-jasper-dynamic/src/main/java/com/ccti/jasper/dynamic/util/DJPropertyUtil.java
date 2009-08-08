/**
 * 
 */
package com.ccti.jasper.dynamic.util;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 7 - 22:38:39
 */
public final class DJPropertyUtil
{

    public static boolean checkClassProperty(final List<String> prop,final Class<?> clazz)
    {
	boolean success = false;
	
	final Field[] fields = clazz.getDeclaredFields();
	
	for(String selprop : prop )
	{
	    for (int i = 0; i < fields.length; i++)
	    {
		if( !StringUtils.equals(selprop, fields[i].getName()) )
		{
		    success = false;
		    break;
		}
	    }
	}
	
	return success;
    }
    
    
}
