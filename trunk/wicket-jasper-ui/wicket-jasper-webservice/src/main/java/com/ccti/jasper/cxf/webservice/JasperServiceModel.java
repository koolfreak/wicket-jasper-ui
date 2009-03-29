/**
 * 
 */
package com.ccti.jasper.cxf.webservice;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 29, 2009 - 2:49:57 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JasperModel", propOrder = {
    "reportId",
    "userName"
})
public class JasperServiceModel implements Serializable
{
    @XmlElement(name="reportId",type=String.class)
    protected String reportId;
    
    @XmlElement(name="userName",type=String.class)
    protected String userName;

    public String getReportId()
    {
        return reportId;
    }
    
    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }

    
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
}
