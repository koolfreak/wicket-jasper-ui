package com.ccti.jasper.web.pages;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.spring.injection.annot.SpringBean;

import wicket.contrib.jasperreports.JRPdfResource;
import wicket.contrib.jasperreports.link.JRResourceLink;
import wicket.contrib.jasperreports.paging.JRHtmlDataView;

import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.service.customer.CustomerSalesService;
import com.ccti.jasper.web.common.JasperIndexPage;
import com.ccti.jasper.web.pages.utils.JasperQueryProvider;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 11:08:59 AM
 *
 */
public class SimpleHtmlReport extends JasperIndexPage
{
    
    @SpringBean private CustomerSalesService customerSalesService;
    
    public SimpleHtmlReport()
    {
	final File reportFile = new File(getContext().getRealPath("/reports/customer_sales_report.jasper"));
    	final Map<String, Object> parameters = new HashMap<String, Object>();
    	
    	final JasperQueryProvider qdp = new JasperQueryProvider(customerSalesService) {

	    public Iterator<CustomerSales> iterator(int first, int count)
	    {
		return customerSalesService.loadAll(first, count).iterator();
	    }

	    public int size()
	    {
		return customerSalesService.countAll();
	    }
	};
	
	final JRHtmlDataView<CustomerSales> jrdv = new JRHtmlDataView<CustomerSales>("jrdv", qdp, 10)
    	{
    	    @Override
    	    protected Map<String, Object> getParameter()
    	    {
    		return parameters;
    	    }
    	    @Override
    	    protected File getReportFile()
    	    {
    		return reportFile;
    	    }
	    @Override
	    protected JRDataSource getSource()
	    {
		return new JRBeanCollectionDataSource(getDatas());
	    }
    	    
    	};
    	
	  add(jrdv);
	  add(new PagingNavigator("navigation", jrdv));
	  
	  JRPdfResource pdf = new JRPdfResource(reportFile);
	  pdf.setReportDataSource(new JRBeanCollectionDataSource(customerSalesService.loadAll()));
	  pdf.setReportParameters(new HashMap<String, Object>());
	  
	  add(new JRResourceLink("pdf", pdf));
    }

    @Override
    public boolean isVersioned()
    {
	return false;
    }
    
    
}

