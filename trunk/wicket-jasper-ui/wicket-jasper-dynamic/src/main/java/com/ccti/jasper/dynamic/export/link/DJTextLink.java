/**
 * 
 */
package com.ccti.jasper.dynamic.export.link;

import java.io.File;

import wicket.contrib.jasperreports.link.JRResourceExportLink;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJPdfResource;
import com.ccti.jasper.dynamic.DJResource;
import com.ccti.jasper.dynamic.DJTextResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 8 - 21:12:39
 */
public abstract class DJTextLink extends JRResourceExportLink
{

    public DJTextLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	final DJResource txt = new DJTextResource();
	txt.setReportDataSource(getSource());
	txt.setReportParameters(getParams());
	final String txtname = getDynamicReportName()+"."+txt.getExtension();
	txt.setFileName(txtname);
	txt.setLayoutManager(getLayoutManager());
	txt.setReportBuilder(getReportBuilder());

	txt.onResourceRequested();
    }

    public File getReportFile()
    {
	return null;
    }
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();
    
    protected abstract DynamicReportBuilder getReportBuilder();
}
