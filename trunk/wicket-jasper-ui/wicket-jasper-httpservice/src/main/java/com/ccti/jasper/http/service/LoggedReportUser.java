/**
 * 
 */
package com.ccti.jasper.http.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 11:59:16 AM
 * 
 */
public class LoggedReportUser
{

    private static final Log log = LogFactory.getLog(LoggedReportUser.class);

    private static final List<JasperObject> REPORT_USER = Collections
    .synchronizedList(new ArrayList<JasperObject>());

    /**
     * Add the user in the collection
     * @param jObj - object to be added
     */
    public static void addReportuser(final JasperObject jObj)
    {
	if (null != jObj)
	{
	    log.debug("Adding report user to collection");
	    synchronized (REPORT_USER)
	    {
		REPORT_USER.add(jObj);
	    }
	}
    }
    
    /**
     * @param reportId - id to be remove
     */
    public static void removeUser(final String reportId)
    {
	if (null != reportId)
	{
	    log.debug("Finding user with report id: " + reportId);
	    synchronized (REPORT_USER)
	    {
		for (Iterator<JasperObject> iterator = REPORT_USER.iterator(); iterator.hasNext();)
		{
		    final JasperObject jasperUser = iterator.next();
		    if(StringUtils.equals(jasperUser.getReportId(), reportId))
		    {
			iterator.remove();
			return;
		    }
		}
	    }
	}
    }
    
    public static void removeAll()
    {
	synchronized(REPORT_USER)
	{
	    REPORT_USER.clear();
	}
    }
    
    public static void printLoggedUser()
    {
	log.debug("Currently logged users: ");
	for (JasperObject obj : REPORT_USER)
	{
	    log.debug("username = " + obj.getUsername() +" id = "+obj.getReportId());
	}
    }
    
    /**
     * @param reportId - id to be check
     * @return <code>true</code> if found, <code>false</code> otherwise
     */
    public static boolean contains(final String reportId)
    {
	if (null != reportId)
	{
	    log.debug("Finding user with report id: " + reportId);
	    synchronized (REPORT_USER)
	    {
		for (Iterator<JasperObject> iterator = REPORT_USER.iterator(); iterator.hasNext();)
		{
		    final JasperObject jasperUser = iterator.next();
		    if(StringUtils.equals(jasperUser.getReportId(), reportId))
		    {
			return true;
		    }
		}
	    }
	}
	return false;
    }
    
    public static JasperObject getObject(final String reportId)
    {
	if (null != reportId)
	{
	    log.debug("Finding user with report id: " + reportId);
	    synchronized (REPORT_USER)
	    {
		for (Iterator<JasperObject> iterator = REPORT_USER.iterator(); iterator.hasNext();)
		{
		    final JasperObject jasperUser = iterator.next();
		    if(StringUtils.equals(jasperUser.getReportId(), reportId))
		    {
			return jasperUser;
		    }
		}
	    }
	}
	return null;
    }
}
