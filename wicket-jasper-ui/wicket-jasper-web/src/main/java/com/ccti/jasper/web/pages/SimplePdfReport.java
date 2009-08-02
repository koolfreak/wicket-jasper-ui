package com.ccti.jasper.web.pages;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.spring.injection.annot.SpringBean;

import wicket.contrib.jasperreports.JRPdfResource;
import wicket.contrib.jasperreports.view.EmbeddedPdfReport;

import com.ccti.jasper.service.customer.CustomerSalesService;
import com.ccti.jasper.web.common.JasperIndexPage;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 21, 2009 - 5:44:38 PM
 */
public class SimplePdfReport extends JasperIndexPage
{
    private static final Log log = LogFactory.getLog(SimplePdfReport.class);
    
    @SpringBean private CustomerSalesService customerSalesService;
    
    public SimplePdfReport()
    {
	final File reportFile = new File(getContext().getRealPath("/reports/customer_sales_report.jasper"));
    	
	final String cache = "c:\\jaspercache";
	
	/*log.info("jasper cache = "+cache);
	final JRFileVirtualizer fileVirtualizer =
	    new JRFileVirtualizer(3, cache);*/

	
	final Map<String, Object> parameters = new HashMap<String, Object>();
    	//parameters.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
    	
    	
    	final JRPdfResource res = new JRPdfResource(reportFile);
    	res.setReportDataSource(new JRBeanCollectionDataSource(customerSalesService.loadAll()));
    	res.setReportParameters(parameters);
    	
    	add(new EmbeddedPdfReport("report", res)); 
    	
    	//fileVirtualizer.cleanup();
    }
}

