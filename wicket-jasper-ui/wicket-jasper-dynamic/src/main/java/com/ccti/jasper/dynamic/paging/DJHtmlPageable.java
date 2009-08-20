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
import wicket.contrib.jasperreports.view.EmbeddedHtmlReport;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.DJHtmlResource;

/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 20 - 13:15:53
 */
public abstract class DJHtmlPageable<E> extends JRPageable<E>
{

    private static final Log log = LogFactory.getLog(DJHtmlPageable.class);
    
    public DJHtmlPageable(String id, IModel model, int rowsPerPage)
    {
	super(id, model, rowsPerPage);
    }

    public DJHtmlPageable(final String id,final List<E> list,final int rowsPerPage)
    {
	super(id, list, rowsPerPage);
    }

    /* (non-Javadoc)
     * @see wicket.contrib.jasperreports.paging.JRPageable#displayReport(org.apache.wicket.markup.html.list.ListItem)
     */
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
    
    protected abstract DynamicReportBuilder getReportBuilder();
    
    protected abstract String getDynamicReportName();
    
    protected abstract LayoutManager getLayoutManager();

}
