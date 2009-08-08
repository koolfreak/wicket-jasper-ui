/**
 * 
 */
package com.ccti.jasper.dynamic.export.link;

import java.io.File;

import wicket.contrib.jasperreports.link.JRResourceExportLink;

import com.ccti.jasper.dynamic.DJPdfResource;

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
	DJPdfResource pdf = new DJPdfResource();
	pdf.setReportDataSource(getSource());
	pdf.setReportParameters(getParams());
	final String pdfname = getDynamicReportName()+"."+pdf.getExtension();
	pdf.setFileName(pdfname);

	pdf.onResourceRequested();
    }

    public File getReportFile()
    {
	return null;
    }
    
    protected abstract String getDynamicReportName();
}
