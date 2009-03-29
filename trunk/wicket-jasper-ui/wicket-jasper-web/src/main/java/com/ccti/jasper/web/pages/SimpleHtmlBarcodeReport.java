package com.ccti.jasper.web.pages;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.spring.injection.annot.SpringBean;

import wicket.contrib.jasperreports.paging.JRPdfDataView;

import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.service.customer.CustomerSalesService;
import com.ccti.jasper.web.common.JasperIndexPage;
import com.ccti.jasper.web.pages.utils.JasperQueryProvider;


/**
 * @author emanux
 */
public class SimpleHtmlBarcodeReport extends JasperIndexPage
{
    @SpringBean private CustomerSalesService customerSalesService;
    
    public SimpleHtmlBarcodeReport()
    {
	final File reportFile = new File(getContext().getRealPath("/reports/customer_sales_barcode.jasper"));
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
	
	final JRPdfDataView<CustomerSales> jrdv = new JRPdfDataView<CustomerSales>("jrdv", qdp, 10)
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
    }
}

