/**
 * 
 */
package com.ccti.jasper.cxf.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 27, 2009 - 10:18:43 PM
 */
@WebService
public interface JasperCXFService
{

    @WebMethod
    void setUserCredentials(JasperServiceModel model);

    @WebMethod
    boolean removeUserCredentials(String reportId);

    @WebMethod
    String callReportName(String reportName, String reportId);

    @WebMethod
    boolean verifyReportUser(String reportId);

}
