/**
 * 
 */
package com.ccti.jasper.dynamic.export.link;

import java.io.File;

import wicket.contrib.jasperreports.link.JRResourceExportLink;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJResource;
import com.ccti.jasper.dynamic.DJRtfResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 8 - 21:12:39
 */
public abstract class DJRtfLink extends JRResourceExportLink
{

    public DJRtfLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	final DJResource rtf = new DJRtfResource();
	rtf.setReportDataSource(getSource());
	rtf.setReportParameters(getParams());
	final String rtfname = getDynamicReportName()+"."+rtf.getExtension();
	rtf.setFileName(rtfname);
	rtf.setLayoutManager(getLayoutManager());
	rtf.setReportBuilder(getReportBuilder());

	rtf.onResourceRequested();
    }

    public File getReportFile()
    {
	return null;
    }
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();
    
    protected abstract DynamicReportBuilder getReportBuilder();
}
