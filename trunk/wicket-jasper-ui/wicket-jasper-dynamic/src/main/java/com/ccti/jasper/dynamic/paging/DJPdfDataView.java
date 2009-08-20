/**
 * 
 */
package com.ccti.jasper.dynamic.paging;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import wicket.contrib.jasperreports.paging.JRDataView;
import wicket.contrib.jasperreports.view.EmbeddedPdfReport;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJPdfResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 20 - 12:05:29
 */
public abstract class DJPdfDataView<E> extends JRDataView<E>
{

    private static final Log log = LogFactory.getLog(DJPdfDataView.class);
    
    /**
     * 
     */
    public DJPdfDataView(String id, IDataProvider dataProvider, int itemsPerPage)
    {
	super(id, dataProvider, itemsPerPage);
    }

    /* (non-Javadoc)
     * @see wicket.contrib.jasperreports.paging.JRDataView#displayReport(org.apache.wicket.markup.html.list.ListItem)
     */
    @Override
    protected void displayReport(ListItem item)
    {
	item.add(new EmbeddedPdfReport("report", getPdfResource()));
	log.info("Finish exporting [PDF] report...");
    }
    
    protected DJPdfResource getPdfResource()
    {
	final DJPdfResource dynamicJRPdfResource = new DJPdfResource();
	dynamicJRPdfResource.setReportParameters(getParameter());
	dynamicJRPdfResource.setReportDataSource(getSource());
	dynamicJRPdfResource.setReportBuilder(getReportBuilder());
	final String filename = getDynamicReportName() +"."+ dynamicJRPdfResource.getExtension();
	dynamicJRPdfResource.setFileName(filename);
	dynamicJRPdfResource.setLayoutManager(getLayoutManager());
	return dynamicJRPdfResource;
    }
    
    protected File getReportFile()
    {
        return null;
    }
    
    protected Map<String, Object> getParameter()
    {
	return new HashMap<String, Object>();
    }
    
    protected abstract JRDataSource getSource();
    
    protected abstract DynamicReportBuilder getReportBuilder();
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();

}
