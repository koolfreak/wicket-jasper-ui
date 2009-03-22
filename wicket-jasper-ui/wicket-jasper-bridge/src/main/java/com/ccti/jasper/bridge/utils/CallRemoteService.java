/**
 * 
 */
package com.ccti.jasper.bridge.utils;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 2, 2009 - 11:12:17 AM
 * 
 */
public interface CallRemoteService
{
    /**
     * @return -  <code>true</code> if authenticated from remote report server, <code>false</code> otherwise
     */
    boolean callRemoteLogin();
    
    /**
     * Logout user from remote report server
     * @param id - assigned user report id
     * @return 
     */
    boolean callRemoteLogout(final String id);
    
}
