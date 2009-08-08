/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

/**
 * @author Emmanuel Nollase - emanux 
 * created 2009 8 6 - 13:04:47
 */
public final class DJRtfResource extends DJResource
{

    public DJRtfResource()
    {
	super();
    }

    public DJRtfResource(InputStream report)
    {
	super(report);
    }

    public DJRtfResource(URL report)
    {
	super(report);
    }

    public DJRtfResource(File report)
    {
	super(report);
    }

    public String getContentType()
    {
	return "text/rtf";
    }

    public String getExtension()
    {
	return "rtf";
    }

    protected JRAbstractExporter newExporter()
    {
	return new JRRtfExporter();
    }

}
