/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import wicket.contrib.jasperreports.JRHtmlResource;
import wicket.contrib.jasperreports.JRResource;
import wicket.contrib.jasperreports.view.EmbeddedHtmlReport;


/**
 * @author Emmannuel A. Nollase
 * Created: Jan 22, 2009 - 12:44:37 PM
 */
public abstract class JRHtmlDataView<E> extends JRDataView<E>
{
    public static final Log log = LogFactory.getLog(JRHtmlDataView.class);

    private static final long serialVersionUID = 1L;

    /**
     * @param id
     *            component id
     * @param dataProvider
     *            data provider
     * @param itemsPerPage
     *            items per page
     */
    public JRHtmlDataView(String id, IDataProvider dataProvider, int itemsPerPage)
    {
	super(id, dataProvider, itemsPerPage);
    }

    
    /**
     * @param item - displays the report
     */
    protected void displayReport(ListItem item) 
    {
	item.add(new EmbeddedHtmlReport("report", getHtmlResource()));
	log.debug("finish exporting the report...");
    }

    protected JRResource getHtmlResource() 
    {
	final JRHtmlResource htmlResource = new JRHtmlResource(getReportFile());
	htmlResource.setReportParameters(getParameter());
	htmlResource.setReportDataSource(getSource());
	return htmlResource;
    }

    
    protected abstract JRDataSource getSource(); 
    
    protected abstract File getReportFile();
    
    protected abstract Map<String, Object> getParameter();
   
}
