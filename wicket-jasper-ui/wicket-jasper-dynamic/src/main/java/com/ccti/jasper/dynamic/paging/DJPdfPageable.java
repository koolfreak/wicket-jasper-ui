/**
 * 
 */
package com.ccti.jasper.dynamic.paging;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;

import wicket.contrib.jasperreports.paging.JRPageable;
import wicket.contrib.jasperreports.view.EmbeddedPdfReport;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJPdfResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 6 - 18:18:42
 */
public abstract class DJPdfPageable <E> extends JRPageable<E>
{

    private static final Log log = LogFactory.getLog(DJPdfPageable.class);
    
    /**
     * 
     * @param id
     * @param model
     * @param rowsPerPage
     */
    public DJPdfPageable(String id, IModel model, int rowsPerPage)
    {
	super(id, model, rowsPerPage);
    }
    
    /**
     * 
     * @param id
     * @param list
     * @param rowsPerPage
     */
    public DJPdfPageable(final String id, final List<E> list, final int rowsPerPage)
    {
    	super(id, list,rowsPerPage);
    }

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
    
    protected abstract DynamicReportBuilder getReportBuilder();
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();
   
}
