/**
 * 
 */
package com.ccti.jasper.serialized;

import java.io.Serializable;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 3:07:15 PM
 * 
 */
public class JasperObject implements Serializable
{

    private String username;
    
    private String password;
    
    private String reportId;
    
    public JasperObject()
    {
	// TODO Auto-generated constructor stub
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getReportId()
    {
        return reportId;
    }

    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }
    
}
