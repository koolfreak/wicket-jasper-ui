/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * @author Emmanuel Nollase - emanux 
 * created 2009 8 6 - 13:04:47
 */
public final class DJPdfResource extends DJResource
{

    public DJPdfResource()
    {
	super();
    }

    public DJPdfResource(InputStream report)
    {
	super(report);
    }

    public DJPdfResource(URL report)
    {
	super(report);
    }

    public DJPdfResource(File report)
    {
	super(report);
    }

    public String getContentType()
    {
	return "application/pdf";
    }

    public String getExtension()
    {
	return "pdf";
    }

    protected JRAbstractExporter newExporter()
    {
	return new JRPdfExporter();
    }

}
