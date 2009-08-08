/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;

/**
 * @author Emmanuel Nollase - emanux 
 * created 2009 8 6 - 13:04:47
 */
public final class DJCsvResource extends DJResource
{

    public DJCsvResource()
    {
	super();
    }

    public DJCsvResource(InputStream report)
    {
	super(report);
    }

    public DJCsvResource(URL report)
    {
	super(report);
    }

    public DJCsvResource(File report)
    {
	super(report);
    }

    public String getContentType()
    {
	return "text/plain";
    }

    public String getExtension()
    {
	return "csv";
    }

    protected JRAbstractExporter newExporter()
    {
	return new JRCsvExporter();
    }

}
