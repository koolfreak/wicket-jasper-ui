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
import com.ccti.jasper.dynamic.DJXlsResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 8 - 21:12:39
 */
public abstract class DJXlsLink extends JRResourceExportLink
{

    public DJXlsLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	final DJResource xls = new DJXlsResource();
	xls.setReportDataSource(getSource());
	xls.setReportParameters(getParams());
	final String xlsname = getDynamicReportName()+"."+xls.getExtension();
	xls.setFileName(xlsname);
	xls.setLayoutManager(getLayoutManager());
	xls.setReportBuilder(getReportBuilder());

	xls.onResourceRequested();
    }

    public File getReportFile()
    {
	return null;
    }
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();
    
    protected abstract DynamicReportBuilder getReportBuilder();
}
