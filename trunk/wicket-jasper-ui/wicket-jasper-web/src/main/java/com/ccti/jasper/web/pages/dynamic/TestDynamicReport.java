/**
 * 
 */
package com.ccti.jasper.web.pages.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.core.DJReportBase;
import com.ccti.jasper.dynamic.export.link.DJPdfLink;
import com.ccti.jasper.dynamic.paging.DJPdfPageable;
import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.service.customer.CustomerSalesService;
import com.ccti.jasper.web.common.JasperIndexPage;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 8 - 00:22:42
 */
public class TestDynamicReport extends JasperIndexPage
{
    @SpringBean private CustomerSalesService salesService;

    public TestDynamicReport(List<String> prop)
    {
	
	final DJReportBase dyrpt = new DJDynamicReport();
	dyrpt.addReportColumns(prop,CustomerSales.class);
	
	final DJPdfPageable<CustomerSales> pageable = new DJPdfPageable<CustomerSales>("dyrpt",salesService.loadAll(1,500),100)
	{
	    @Override
	    protected JRDataSource getSource()
	    {
		return new JRBeanCollectionDataSource(getDatas());
	    }
	    @Override
	    protected Map<String, Object> getParameter()
	    {
		return new HashMap<String, Object>();
	    }
	    @Override
	    protected DynamicReportBuilder getReportBuilder()
	    {
		return dyrpt.getReportBuilder();
	    }
	    @Override
	    protected String getDynamicReportName()
	    {
		return dyrpt.getDynamicReportName();
	    }

	    @Override
	    protected LayoutManager getLayoutManager()
	    {
		return dyrpt.getReportLayout();
	    }
	};
	
	add(pageable);
	add(new PagingNavigator("nav", pageable));
	
	final DJPdfLink pdflnk = new DJPdfLink("pdf")
	{
	    @Override
	    protected DynamicReportBuilder getReportBuilder()
	    {
		return dyrpt.getReportBuilder();
	    }
	    @Override
	    protected String getDynamicReportName()
	    {
		return dyrpt.getDynamicReportName();
	    }

	    @Override
	    protected LayoutManager getLayoutManager()
	    {
		return dyrpt.getReportLayout();
	    }
	    @Override
	    public Map<String, Object> getParams()
	    {
		return new HashMap<String, Object>();
	    }
	    @Override
	    public JRDataSource getSource()
	    {
		return new JRBeanCollectionDataSource(pageable.getDatas());
	    }
	};
	
	add(pdflnk);
    }
}
