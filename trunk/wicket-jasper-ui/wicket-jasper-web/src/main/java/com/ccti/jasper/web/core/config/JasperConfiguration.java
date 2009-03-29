/**
 * 
 */
package com.ccti.jasper.web.core.config;

import java.net.URL;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 5, 2009 - 10:43:09 PM
 * 
 */
public final class JasperConfiguration
{

    protected static final Log logger = LogFactory.getLog(JasperConfiguration.class);

    private static CompositeConfiguration configuration;

    static {
        configuration = new CompositeConfiguration();
        configuration.setThrowExceptionOnMissing(false);
        AbstractConfiguration.setDefaultListDelimiter('%');
    }
    
    public static void addConfigFile(final URL file) {
        try {
            configuration.addConfiguration(new XMLConfiguration(file));
        }
        catch (ConfigurationException e) {
            logger.error("Could not load application configuration:" + e.getMessage(), e);
        }
    }
    
    /**
     * @return the configuration
     */
    public static Configuration getConfiguration()
    {
        return configuration;
    }


    public static String getString(final String paramName, final String defaultValue) {
        return configuration.getString(paramName, defaultValue);
    }

    private JasperConfiguration(){}
}
