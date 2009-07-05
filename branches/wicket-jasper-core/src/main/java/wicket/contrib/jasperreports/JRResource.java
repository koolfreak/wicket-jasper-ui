/*
 * $Id: JRResource.java 3360 2007-12-13 08:57:29Z lm61 $ $Revision:
 * 1.4 $ $Date: 2007-12-13 16:57:29 +0800 (Thu, 13 Dec 2007) $
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRVirtualPrintPage;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.protocol.http.WebResponse;

/**
 * Base class for jasper reports resources.
 * @author  Eelco Hillenius
 * @author  Matej Knopp
 * @author  Luciano Montebove
 */
public abstract class JRResource extends DynamicWebResource
{
	/**
	 * logger.
	 */
	private static Log log = LogFactory.getLog(JRResource.class);

	/**
	 * Provides JDBC connection.
	 */
	public static interface IDatabaseConnectionProvider extends Serializable
	{
		/**
		 * Gets a JDBC connection to use when filling the report.
		 * 
		 * @return a JDBC connection
		 */
		Connection get();

		/**
		 * Called when the report is generated and the connection can be
		 * released again.
		 */
		void release();
	}

	/**
	 * Factory class for lazy initialization of the jasper report.
	 */
	private static interface JasperReportFactory extends Serializable
	{
		/**
		 * Create a jasper report instance.
		 * 
		 * @return the new jasper report instance.
		 * 
		 * @throws JRException
		 */
		JasperReport newJasperReport() throws JRException;
	}

	;

	/**
	 * the connection provider if any for filling this report.
	 * @uml.property  name="connectionProvider"
	 * @uml.associationEnd  
	 */
	private IDatabaseConnectionProvider connectionProvider;

	/**
	 * factory for delayed report creation.
	 * @uml.property  name="jasperReportFactory"
	 * @uml.associationEnd  
	 */
	private JasperReportFactory jasperReportFactory;

	/**
	 * The compiled report this resource references. Made transient as we don't
	 * want our report to be serialized while we can recreate it at other
	 * servers at will using the factory.
	 */
	private transient JasperReport jasperReport;
	
	/**
	 * The report cache used.
	 */
	private JRFileVirtualizer fileVirtualizer;
	
	/**
	 * The compiled jasperPrint this resource references. Made transient as we don't
	 * want our report to be serialized while we can recreate it.
	 */
	private transient JasperPrint jasperPrint;
	/**
	 * the report parameters.
	 */
	private Map<String, Object> reportParameters;

	/**
	 * the datasource if any for filling this report.
	 */
	private JRDataSource reportDataSource;

	/**
	 * When set, a header 'Content-Disposition: attachment;
	 * filename="${fileName}"' will be added to the response, resulting in a
	 * download dialog. No magical extensions are added, so you should make sure
	 * the file has the extension you want yourself.
	 */
	private String fileName;

	/**
	 * Construct without a report. You must provide a report before you can use
	 * this resource.
	 */
	public JRResource()
	{
		super();
		setCacheable(false);
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRResource(final InputStream report)
	{
		this(new JasperReportFactory()
		{
			public JasperReport newJasperReport() throws JRException
			{
				return (JasperReport) JRLoader.loadObject(report);
			}

			;
		});
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRResource(final URL report)
	{
		this(new JasperReportFactory()
		{
			public JasperReport newJasperReport() throws JRException
			{
				return (JasperReport) JRLoader.loadObject(report);
			}

			;
		});
	}

	/**
	 * Construct.
	 * 
	 * @param report
	 *            the report input stream
	 */
	public JRResource(final File report)
	{
		this(new JasperReportFactory()
		{
			public JasperReport newJasperReport() throws JRException
			{
				return (JasperReport) JRLoader.loadObject(report);
			}

			;
		});
	}

	/**
	 * Construct.
	 * 
	 * @param factory
	 *            report factory for lazy initialization
	 */
	private JRResource(JasperReportFactory factory)
	{
		super();
		setCacheable(false);
		this.jasperReportFactory = factory;
	}

	/**
	 * Gets jasperReport. This implementation uses an internal factory to lazily create the report. After creation the report is cached (set as the jasperReport property). Override this method in case you want to provide some alternative creation/ caching scheme.
	 * @return  jasperReport
	 * @uml.property  name="jasperReport"
	 */
	public JasperReport getJasperReport()
	{
		// if the report has not yet been initialized and can be, initialize it
		if (jasperReport == null && jasperReportFactory != null)
		{
			try
			{
				setJasperReport(jasperReportFactory.newJasperReport());
			}
			catch (JRException e)
			{
				throw new WicketRuntimeException(e);
			}
		}
		return jasperReport;
	}

	/**
	 * Sets {bjasperReport.
	 * @param report  report
	 * @uml.property  name="jasperReport"
	 */
	public final void setJasperReport(JasperReport report)
	{
		this.jasperReport = report;
	}

	/**
	 * Gets the report parameters. Returns a new copy of the reportParameters Map as JasperReports modifies it with not serializable objects
	 * @return  report parameters
	 * @uml.property  name="reportParameters"
	 */
	public Map getReportParameters()
	{
                return new HashMap(reportParameters);
	}

	/**
	 * Sets the report parameters.
	 * @param params  report parameters
	 * @return  This
	 * @uml.property  name="reportParameters"
	 */
	public final JRResource setReportParameters(Map params)
	{
		this.reportParameters = params;
		return this;
	}

	/**
	 * Gets the datasource if any for filling this report.
	 * @return  the datasource if any for filling this report
	 * @uml.property  name="reportDataSource"
	 */
	public JRDataSource getReportDataSource()
	{
		return reportDataSource;
	}

	/**
	 * Sets the datasource if any for filling this report.
	 * @param dataSource  the datasource if any for filling this report
	 * @return  This
	 * @uml.property  name="reportDataSource"
	 */
	public JRResource setReportDataSource(JRDataSource dataSource)
	{
		this.reportDataSource = dataSource;
		return this;
	}

	/**
	 * Gets the connection provider if any for filling this report.
	 * @return  the connection provider if any for filling this report
	 * @uml.property  name="connectionProvider"
	 */
	public IDatabaseConnectionProvider getConnectionProvider()
	{
		return connectionProvider;
	}

	/**
	 * Sets the connection provider if any for filling this report.
	 * @param provider  the connection provider if any for filling this report
	 * @return  This
	 * @uml.property  name="connectionProvider"
	 */
	public final JRResource setConnectionProvider(IDatabaseConnectionProvider provider)
	{
		this.connectionProvider = provider;
		return this;
	}

	/**
	 * Gets the file name. When set, a header 'Content-Disposition: attachment; filename="${fileName}"' will be added to the response, resulting in a download dialog. No magical extensions are added, so you should make sure the file has the extension you want yourself.
	 * @return  the file name
	 * @uml.property  name="fileName"
	 */
	public String getFileName()
	{
		if (fileName == null)
		{
			fileName = getJasperReport().getName() + "." + getExtension();
		}
		return fileName;
	}

	/**
	 * Sets the file name. When set, a header 'Content-Disposition: attachment; filename="${name}"' will be added to the response, resulting in a download dialog. No magical extensions are added, so you should make sure the file has the extension you want yourself.
	 * @param name  the file name
	 * @return  This
	 * @uml.property  name="fileName"
	 */
	public final JRResource setFileName(String name)
	{
		this.fileName = name;
		return this;
	}

	/**
	 * Called by getData to obtain an exporter instance.
	 * 
	 * @return an exporter instance
	 */
	protected abstract JRAbstractExporter newExporter();

	/**
	 * Gets the binary data by getting a new instance of JasperPrint and an
	 * exporter for generating the output.
	 * 
	 * @return the binary data
	 * 
	 * @see DynamicWebResource#getResourceState()
	 */
	protected ResourceState getResourceState()
	{
		try
		{
			long t1 = System.currentTimeMillis();
			// get a print instance for exporting
			jasperPrint = newJasperPrint();

			// get a fresh instance of an exporter for this report
			JRAbstractExporter exporter = newExporter();

			// prepare a stream to trap the exporter's output
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

			// execute the export and return the trapped result
			try 
			{
			    exporter.exportReport();
			}
			finally
			{
			    	// no matter what happens perform cleanup of used virtualize files
        			final Iterator<JRVirtualPrintPage> jprint = jasperPrint.getPages().iterator();
        			while(jprint.hasNext())
        			{
        			    fileVirtualizer.deregisterObject(jprint.next());
        			}
			}
			
			final byte[] data = baos.toByteArray();
			if (log.isDebugEnabled())
			{
				long t2 = System.currentTimeMillis();
				log.debug("loaded report data; bytes: "
						+ data.length + " in " + (t2 - t1) + " miliseconds");
			}
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
					return JRResource.this.getContentType();
				}
			};
		}
		catch (JRException e)
		{
			throw new WicketRuntimeException(e);
		}
	}

	/**
	 * @return The content type of the reports
	 */
	public abstract String getContentType();

	/**
	 * Returns the extension for the resource's file. This string should not
	 * contain the leading "."
	 * 
	 * @return The extension for the resource's file.
	 */
	public abstract String getExtension();

	/**
	 * Creates a new {@link JasperPrint} instance. This instance is specific for
	 * this render, but it not yet designated for one output format only.
	 * 
	 * @return a new {@link JasperPrint} instance.
	 * 
	 * @throws JRException
	 */
	protected JasperPrint newJasperPrint() throws JRException
	{
	    	final String cache = getCahceDir();
		
		fileVirtualizer =   new JRFileVirtualizer(3, cache);
		JasperReport report = getJasperReport();
		Map params = getReportParameters();
		JRDataSource dataSource = getReportDataSource();
		params.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		
		if (dataSource != null)
		{
			jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
		}
		else
		{
			IDatabaseConnectionProvider provider = null;
			try
			{
				provider = getConnectionProvider();
				if (provider == null)
				{
					throw new IllegalStateException(
							"JasperReportsResources must either have a JRDataSource, "
									+ "or a JDBC Connection provided");
				}
				jasperPrint = JasperFillManager
						.fillReport(report, params, provider.get());
			}
			finally
			{
				if (provider != null)
				{
					provider.release();
				}
			}
		}
		return jasperPrint;
	}

	/**
	 * @see WebResource#setHeaders(WebResponse)
	 */
	protected void setHeaders(WebResponse response)
	{
	    super.setHeaders(response);
	    
	    response.setDateHeader("Expires", -1);
	    response.setHeader("Cache-Control",	"must-revalidate, post-check=0, pre-check=0");
	    response.setHeader("Pragma", "public");

		String name = getFileName();
		if (name != null)
		{
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ name + "\"");
		}
	}
	
	/**
	 * Return directory where to store the cache files.
	 * if no value set for property "org.jasper.cache.dir", it will create
	 * at "user.home" + OS separator + jasperCache.
	 * Provided it has write permission on that directory.
	 * @return - cache directory to store tmp files
	 */
	protected String getCahceDir()
	{
	    String dirCache = System.getProperty("org.jasper.cache.dir");
	    if( dirCache.equals("") || dirCache == null)
	    {
		dirCache = System.getenv("user.home") + File.pathSeparator + "jasperCache";
		final File dircache = new File(dirCache);
		if( !dircache.exists() )
		{
		    dircache.mkdir();
		}
	    }
	    return dirCache;
	}
}