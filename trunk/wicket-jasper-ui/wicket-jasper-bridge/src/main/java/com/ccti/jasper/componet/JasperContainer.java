/**
 * 
 */
package com.ccti.jasper.componet;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 3:11:17 PM
 * 
 */
public abstract class JasperContainer extends WebMarkupContainer
{
    private static final String REPORT_PARAM_NAME = "?reportname=";
    private static final String REPORT_PARAM_ID = "&reportid=";
    
    private final String jasperId;
    
    public JasperContainer(String id,String jasperId)
    {
	super(id);
	this.jasperId = jasperId;
    }
    
    /* (non-Javadoc)
     * @see org.apache.wicket.Component#getStatelessHint()
     */
    @Override
    protected boolean getStatelessHint()
    {
	return false;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.Component#isVersioned()
     */
    @Override
    public boolean isVersioned()
    {
	return false;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
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
	
	final StringBuilder build = new StringBuilder();
	build.append(reportServerUrl).append(REPORT_PARAM_NAME).append(reportClassName);
	build.append(REPORT_PARAM_ID).append(jasperId);
	
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
