/**
 * 
 */
package com.ccti.jasper.web.core.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 5, 2009 - 11:23:19 PM
 * 
 */
public final class JasperConfigurationHelper
{

    private static final Log LOGGER = LogFactory.getLog(JasperConfigurationHelper.class);

    public static Map<String, String> getConfiguration(final String prefix, final String suffix1,
    final String suffix2)
    {
	final Map<String, String> loadedConfiguration = new HashMap<String, String>(20);
	final Configuration configuration = JasperConfiguration.getConfiguration();
	int number = 0;
	String suffix2Value;
	do
	{
	    final String suffix1String = new StringBuilder(64).append(prefix).append(number)
	    .append(suffix1).toString();
	    final String suffix2String = new StringBuilder(64).append(prefix).append(number)
	    .append(suffix2).toString();

	    suffix2Value = configuration.getString(suffix2String);
	    final String suffix1Value = configuration.getString(suffix1String);
	    number++;
	    if (null != suffix2Value)
	    {
		loadedConfiguration.put(suffix1Value, suffix2Value);
	    }
	} while (StringUtils.isNotEmpty(suffix2Value));
	LOGGER.debug("Loaded configuration: " + loadedConfiguration);
	return loadedConfiguration;
    }

    private JasperConfigurationHelper()
    {
    }
}
