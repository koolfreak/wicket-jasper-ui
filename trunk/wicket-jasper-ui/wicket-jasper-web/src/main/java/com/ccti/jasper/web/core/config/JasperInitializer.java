/**
 * 
 */
package com.ccti.jasper.web.core.config;

import java.net.MalformedURLException;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;



/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 5, 2009 - 10:28:08 PM
 * 
 */
public final class JasperInitializer implements ServletContextListener
{
    private static final Log logger = LogFactory.getLog(JasperInitializer.class);
    
    @SuppressWarnings(value = {"unchecked"})
    private static void setUpConfiguration(final ServletContext servletContext) {
        final Set<String> configFiles = (Set<String>)
                servletContext.getResourcePaths("/WEB-INF/reports-config/");
        if (null != configFiles) {
            try {
                for (String file : configFiles) {
                    if (file.endsWith(".xml")) {
                        logger.debug(new StringBuilder(64).append("Loading config file: ")
                                .append(file).toString());
                        JasperConfiguration.addConfigFile(servletContext.getResource(file));
                    }
                }
            }
            catch (MalformedURLException e) {
                logger.error("MalformedURLException => " + e.getMessage(), e);
            }
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)
    {

	
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)
    {
	final ServletContext servletContext = sce.getServletContext();
        setUpConfiguration(servletContext);
        logger.debug("Application is starting...");
    }

    public ApplicationContext loadContext(String... arg0) throws Exception
    {
	// TODO Auto-generated method stub
	return null;
    }

    public String[] processLocations(Class<?> arg0, String... arg1)
    {
	// TODO Auto-generated method stub
	return null;
    }

}
