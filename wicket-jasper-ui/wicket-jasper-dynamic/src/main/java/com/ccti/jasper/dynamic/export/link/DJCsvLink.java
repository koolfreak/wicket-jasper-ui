/**
 * 
 */
package com.ccti.jasper.dynamic.export.link;

import java.io.File;

import wicket.contrib.jasperreports.link.JRResourceExportLink;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJCsvResource;
import com.ccti.jasper.dynamic.DJPdfResource;
import com.ccti.jasper.dynamic.DJResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 8 - 21:12:39
 */
public abstract class DJCsvLink extends JRResourceExportLink
{

    public DJCsvLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	final DJResource csv = new DJCsvResource();
	csv.setReportDataSource(getSource());
	csv.setReportParameters(getParams());
	final String csvname = getDynamicReportName()+"."+csv.getExtension();
	csv.setFileName(csvname);
	csv.setLayoutManager(getLayoutManager());
	csv.setReportBuilder(getReportBuilder());

	csv.onResourceRequested();
    }

    public File getReportFile()
    {
	return null;
    }
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();
    
    protected abstract DynamicReportBuilder getReportBuilder();
}
