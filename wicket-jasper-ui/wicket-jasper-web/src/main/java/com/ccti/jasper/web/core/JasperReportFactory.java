package com.ccti.jasper.web.core;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccti.jasper.web.core.config.JasperConfigurationHelper;



/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 27, 2009 - 11:21:09 PM
 */
public class JasperReportFactory 
{
   
    private static final Log logger = LogFactory.getLog(JasperReportFactory.class);

    private static final Map<String, String> REPORT_CLASS = JasperConfigurationHelper
            .getConfiguration("jasper-reports.report(", ")[@name]", ")[@type]");

    
    public static String getReportClass(final String reportname)
    {
	
	final String clazz = REPORT_CLASS.get(reportname);
	if(StringUtils.isEmpty(clazz))
	{
	    return null;
	}
	return clazz;
    }
}
