/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * @author Emmanuel Nollase - emanux created 2009 8 6 - 13:04:47
 */
public final class DJHtmlResource extends DJResource
{

    public DJHtmlResource()
    {
	super();
    }

    public DJHtmlResource(InputStream report)
    {
	super(report);
    }

    public DJHtmlResource(URL report)
    {
	super(report);
    }

    public DJHtmlResource(File report)
    {
	super(report);
    }

    public String getContentType()
    {
	return "text/html";
    }

    public String getExtension()
    {
	return "html";
    }

    protected JRAbstractExporter newExporter()
    {
	final JRHtmlExporter htmlexporter = new JRHtmlExporter();
	htmlexporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	htmlexporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
	htmlexporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
	return htmlexporter;
    }

}
