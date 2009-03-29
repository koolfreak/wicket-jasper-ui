/**
 * 
 */
package com.ccti.jasper.componet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ccti.jasper.bridge.SimpleServiceReportPage;
import com.ccti.jasper.cxf.webservice.JasperCXFService;
import com.ccti.jasper.cxf.webservice.JasperServiceModel;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 28, 2009 - 8:32:10 PM
 */
public abstract class JasperServiceContainer extends WebMarkupContainer
{

    private static final Log log = LogFactory.getLog(JasperServiceContainer.class);
    
    @SpringBean private JasperCXFService jasperCXFService;
    
    private final String jasperId;
    
    public JasperServiceContainer(String id, String jasperId)
    {
	super(id);
	this.jasperId = jasperId;
    }
    
    @Override
    protected void onComponentTag(ComponentTag tag)
    {
	checkComponentTag(tag, "iframe");
	
	final String reportServerUrl = getReportServerURL();
	if(StringUtils.isEmpty(reportServerUrl))
	{
	    throw new IllegalArgumentException("Report server url must not be empty");
	}
	final String reportClassName = getReportClassName();
	if(StringUtils.isEmpty(reportClassName))
	{
	    throw new IllegalArgumentException("Report name must not be empty");
	}
	if(StringUtils.isEmpty(jasperId))
	{
	    throw new IllegalArgumentException("Report id must not be empty");
	}
	
	log.info("get report id = "+jasperId);
	
	//final JasperModel reportClass = jasperCXFService.getModel(); //.callReportName(reportClassName, jasperId);
	//log.info("model value = "+reportClass.getId());
	/*ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
	factory.setServiceClass(JasperCXFService.class);
	factory.setAddress("http://localhost:8080/jasper/services/jasperservice/");
	JasperCXFService client = (JasperCXFService) factory.create();*/
	
	final String reportClass = jasperCXFService.callReportName("simple", jasperId);
	log.info("report class = "+reportClass);
	
	final StringBuilder build = new StringBuilder();
	build.append(reportServerUrl).append("?wicket:bookmarkablePage=:");
	build.append(reportClass);
	
	tag.put("src", build.toString());
	
	super.onComponentTag(tag);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.Component#onDetach()
     */
    @Override
    protected void onDetach()
    {
	super.onDetach();
    }
    
    public abstract String getReportServerURL();
    
    public abstract String getReportClassName();

}
