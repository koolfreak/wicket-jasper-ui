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

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 8 - 21:12:39
 */
public abstract class DJPdfLink extends JRResourceExportLink
{

    public DJPdfLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	DJResource pdf = new DJPdfResource();
	pdf.setReportDataSource(getSource());
	pdf.setReportParameters(getParams());
	final String pdfname = getDynamicReportName()+"."+pdf.getExtension();
	pdf.setFileName(pdfname);
	pdf.setLayoutManager(getLayoutManager());
	pdf.setReportBuilder(getReportBuilder());

	pdf.onResourceRequested();
    }

    public File getReportFile()
    {
	return null;
    }
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();
    
    protected abstract DynamicReportBuilder getReportBuilder();
}
