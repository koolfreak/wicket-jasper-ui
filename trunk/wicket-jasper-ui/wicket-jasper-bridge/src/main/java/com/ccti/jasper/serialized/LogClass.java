/**
 * 
 */
package com.ccti.jasper.serialized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 6, 2009 - 3:59:00 PM
 * 
 */
public class LogClass
{

    private static final Log log = LogFactory.getLog(LogClass.class);
    /**
     * @param args
     */
    public static void main(String[] args)
    {
	log.trace("Trace");
	log.debug("Debug");
	log.info("Info");
	log.warn("Warn");
	log.error("Error");
	log.fatal("Fatal");


    }

}
