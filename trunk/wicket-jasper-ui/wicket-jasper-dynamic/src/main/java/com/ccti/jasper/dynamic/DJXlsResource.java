/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 * @author Emmanuel Nollase - emanux 
 * created 2009 8 6 - 13:04:47
 */
public final class DJXlsResource extends DJResource
{

    public DJXlsResource()
    {
	super();
    }

    public DJXlsResource(InputStream report)
    {
	super(report);
    }

    public DJXlsResource(URL report)
    {
	super(report);
    }

    public DJXlsResource(File report)
    {
	super(report);
    }

    public String getContentType()
    {
	return "application/vnd.ms-excel";
    }

    public String getExtension()
    {
	return "xls";
    }

    protected JRAbstractExporter newExporter()
    {
	return new JRXlsExporter();
    }

}
