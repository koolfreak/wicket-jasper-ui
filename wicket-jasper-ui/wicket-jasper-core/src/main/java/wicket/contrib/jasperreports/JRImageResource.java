/*
 * $Id: JRImageResource.java 1959 2007-04-19 04:11:08Z eelco12 $
 * $Revision: 1959 $ $Date: 2007-04-19 12:11:08 +0800 (Thu, 19 Apr 2007) $
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.jasperreports;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.DynamicWebResource;

/**
 * Resource class for jasper reports PDF resources.
 * @author  Eelco Hillenius
 */
public final class JRImageResource extends JRResource
{
	/**
	 * log.
	 */
	private static Log log = LogFactory.getLog(JRImageResource.class);

	/**
	 * Type of image (one of BufferedImage.TYPE_*).
	 */
	private int type = BufferedImage.TYPE_INT_RGB;

	/**
	 * The zoom ratio used for the export. The default value is 1.
	 */
	private float zoomRatio = 1;

	/**
	 * the image type. The default value is 'png'.
	 */
	private String format = "png";

	/**
	 * Construct without a report. You must provide a report before you can use
	 * this resource.
	 */
	public JRImageResource()
	{
		super();
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRImageResource(InputStream report)
	{
		super(report);
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRImageResource(URL report)
	{
		super(report);
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRImageResource(File report)
	{
		super(report);
	}

	/**
	 * @see JRResource#newExporter()
	 */
	public final JRAbstractExporter newExporter()
	{
		throw new UnsupportedOperationException(
				"this method is not used in this implementation");
	}

	/**
	 * @see DynamicWebResource#getResourceState()
	 */
	protected ResourceState getResourceState()
	{
		try
		{
			long t1 = System.currentTimeMillis();
			// get a print instance for exporting
			JasperPrint print = newJasperPrint();

			// get a fresh instance of an exporter for this report
			JRGraphics2DExporter exporter = new JRGraphics2DExporter();

			// prepare a stream to trap the exporter's output
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

			// create an image object
			int width = (int) ((float) print.getPageWidth() * getZoomRatio());
			int height = (int) ((float) print.getPageHeight() * getZoomRatio());
			BufferedImage image = new BufferedImage(width, height, type);
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, image
					.getGraphics());
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(
					zoomRatio));

			// execute the export and return the trapped result
			exporter.exportReport();
			final byte[] data = toImageData(image);
			// if (log.isDebugEnabled())
			// {
			long t2 = System.currentTimeMillis();
			log.info("loaded report data; bytes: "
					+ data.length + " in " + (t2 - t1) + " miliseconds");
			// }
			return new ResourceState()
			{
				public int getLength()
				{
					return data.length;
				}

				public byte[] getData()
				{
					return data;
				}

				public String getContentType()
				{
					return "image/" + format;
				}
			};
		}
		catch (JRException e)
		{
			throw new WicketRuntimeException(e);
		}
	}

	/**
	 * @param image
	 *            The image to turn into data
	 * 
	 * @return The image data for this dynamic image
	 */
	protected byte[] toImageData(final BufferedImage image)
	{
		try
		{
			// Create output stream
			final ByteArrayOutputStream out = new ByteArrayOutputStream();

			// Get image writer for format
			final ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName(
					format).next();

			// Write out image
			writer.setOutput(ImageIO.createImageOutputStream(out));
			writer.write(image);

			// Return the image data
			return out.toByteArray();
		}
		catch (IOException e)
		{
			throw new WicketRuntimeException("Unable to convert dynamic image to stream",
					e);
		}
	}

	/**
	 * @see JRResource#getContentType()
	 */
	public String getContentType()
	{
		return "image/" + format;
	}

	/**
	 * Gets the zoom ratio.
	 * @return  the zoom ratio used for the export. The default value is 1
	 * @uml.property  name="zoomRatio"
	 */
	public float getZoomRatio()
	{
		return zoomRatio;
	}

	/**
	 * Sets the zoom ratio.
	 * @param ratio  the zoom ratio used for the export. The default value is 1
	 * @uml.property  name="zoomRatio"
	 */
	public void setZoomRatio(float ratio)
	{
		this.zoomRatio = ratio;
	}

	/**
	 * Gets the image type.
	 * @return  the image type. The default value is 'png'
	 * @uml.property  name="format"
	 */
	public String getFormat()
	{
		return format;
	}

	/**
	 * Sets the image type.
	 * @param format  the image type. The default value is 'png'
	 * @uml.property  name="format"
	 */
	public void setFormat(String format)
	{
		this.format = format;
	}

	/**
	 * Gets type of image (one of BufferedImage.TYPE_*).
	 * @return  type of image
	 * @uml.property  name="type"
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Sets type of image (one of BufferedImage.TYPE_*).
	 * @param type  type of image
	 * @uml.property  name="type"
	 */
	public void setType(int type)
	{
		this.type = type;
	}

	/**
	 * @see wicket.contrib.jasperreports.JRResource#getExtension()
	 */
	public String getExtension()
	{
		return getFormat();
	}
}