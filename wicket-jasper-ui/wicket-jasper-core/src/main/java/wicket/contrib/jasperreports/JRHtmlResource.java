/*
 * $Id: JRHtmlResource.java 627 2006-03-20 07:12:13Z eelco12 $
 * $Revision: 627 $ $Date: 2006-03-20 15:12:13 +0800 (Mon, 20 Mar 2006) $
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

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

/**
 * Resource class for jasper reports HTML resources.
 * 
 * @author Eelco Hillenius
 * @author Emmanuel Nollase
 */
public final class JRHtmlResource extends JRResource
{
	/**
	 * Construct without a report. You must provide a report before you can use
	 * this resource.
	 */
	public JRHtmlResource()
	{
		super();
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRHtmlResource(InputStream report)
	{
		super(report);
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRHtmlResource(URL report)
	{
		super(report);
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRHtmlResource(File report)
	{
		super(report);
	}

	/**
	 * @see JRResource#newExporter()
	 */
	public JRAbstractExporter newExporter()
	{
	    final JRHtmlExporter htmlexporter =  new JRHtmlExporter();
	    htmlexporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
	    htmlexporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
	    htmlexporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
	    
	    return htmlexporter;
	}

	/**
	 * @see JRResource#getContentType()
	 */
	public String getContentType()
	{
		return "text/html";
	}

	/**
	 * @see wicket.contrib.jasperreports.JRResource#getExtension()
	 */
	public String getExtension()
	{
		return "html";
	}
}
