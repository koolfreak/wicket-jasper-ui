/**
 * 
 */
package com.ccti.jasper.dynamic.paging;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import wicket.contrib.jasperreports.paging.JRDataView;
import wicket.contrib.jasperreports.view.EmbeddedHtmlReport;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJHtmlResource;

/**
 * @author Emmanuel Nollase - emanux 
 * screated 2009 8 20 - 12:14:06
 */
public abstract class DJHtmlDataView<E> extends JRDataView<E>
{

    /**
     * 
     * @param id
     * @param dataProvider
     * @param itemsPerPage
     */
    public DJHtmlDataView(String id, IDataProvider dataProvider,
	    int itemsPerPage)
    {
	super(id, dataProvider, itemsPerPage);
    }

    @Override
    protected void displayReport(ListItem item)
    {
	item.add(new EmbeddedHtmlReport("report", getHtmlResource()));
	log.info("Finish exporting [HTML] report...");
    }

    protected DJHtmlResource getHtmlResource()
    {
	final DJHtmlResource dynamicJRHtmlResource = new DJHtmlResource();
	dynamicJRHtmlResource.setReportParameters(getParameter());
	dynamicJRHtmlResource.setReportDataSource(getSource());
	dynamicJRHtmlResource.setReportBuilder(getReportBuilder());
	final String filename = getDynamicReportName() +"."+ dynamicJRHtmlResource.getExtension();
	dynamicJRHtmlResource.setFileName(filename);
	dynamicJRHtmlResource.setLayoutManager(getLayoutManager());
	return dynamicJRHtmlResource;
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
