/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

/**
 * @author Emmanuel Nollase - emanux 
 * created 2009 8 6 - 13:04:47
 */
public final class DJTextResource extends DJResource
{
    
    /**
	 * an integer representing the page width in characters.
	 */
	private int pageWidth = 100;

	/**
	 * an integer representing the page height in characters.
	 */
	private int pageHeight = 100;

    public DJTextResource()
    {
	super();
    }

    public DJTextResource(InputStream report)
    {
	super(report);
    }

    public DJTextResource(URL report)
    {
	super(report);
    }

    public DJTextResource(File report)
    {
	super(report);
    }

    public String getContentType()
    {
	return "text/plain";
    }

    public String getExtension()
    {
	return "txt";
    }

    protected JRAbstractExporter newExporter()
    {
	JRTextExporter exporter = new JRTextExporter();
	exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(pageWidth));
	exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT,
			new Integer(pageHeight));
	return exporter;
    }
    
    /**
	 * Gets page height.
	 * @return  an integer representing the page height in characters
	 * @uml.property  name="pageHeight"
	 */
	public int getPageHeight()
	{
		return pageHeight;
	}

	/**
	 * Sets page height.
	 * @param height  an integer representing the page height in characters
	 * @uml.property  name="pageHeight"
	 */
	public void setPageHeight(int height)
	{
		this.pageHeight = height;
	}

	/**
	 * Gets page width.
	 * @return  an integer representing the page width in characters
	 * @uml.property  name="pageWidth"
	 */
	public int getPageWidth()
	{
		return pageWidth;
	}

	/**
	 * Sets page width.
	 * @param width  an integer representing the page width in characters
	 * @uml.property  name="pageWidth"
	 */
	public void setPageWidth(int width)
	{
		this.pageWidth = width;
	}

}
