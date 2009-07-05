/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
public abstract class JRHtmlDataView<E> extends JRDataViewBase
{
    public static final Log log = LogFactory.getLog(JRHtmlDataView.class);

    /** Datas to be rendered by JasperReports **/
    private List<E> datas;
    
    /** A type of report to be displayed **/
    transient EmbeddedHtmlReport embeddedHtmlReport;
    
    transient JRHtmlResource htmlResource;

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
	this.datas = new ArrayList<E>();
    }

    
    @Override
    protected final void onPopulate()
    {
	// Get number of items to be displayed
	final int size = getDataViewSize();
	if (size > 0)
	{

	    // reset previously stored data in the collection
	    datas.clear();
	    // Automatically rebuild all ListItems before rendering the list
	    // view
	    removeAll();

	    //boolean hasChildren = size() != 0;
	    ListItem item = null;
	    Iterator<E> data = getDataProvider().iterator(firstIndex, size);
	    while (data.hasNext())
	    {
		datas.add(data.next());
	    }

	    /*if (hasChildren)
	    {
		// If this component does not already exist, populate it
		 //item = (ListItem) get(Integer.toString(index));
	    }*/
	    if (item == null)
	    {
		// Create item for index
		item = newItem(firstIndex);
		// Add list item
		add(item);
	    }
	   
	    displayReport(item);
	}
	else
	{
	    removeAll();
	}

    }
    
    /**
     * @param item - displays the report
     */
    protected void displayReport(ListItem item) 
    {
	embeddedHtmlReport = new EmbeddedHtmlReport("report", getHtmlResource());
	item.add(embeddedHtmlReport);
	log.debug("finish exporting the report...");
    }

    protected JRResource getHtmlResource() 
    {
	htmlResource = new JRHtmlResource(getReportFile());
	htmlResource.setReportParameters(getParameter());
	htmlResource.setReportDataSource(getSource());
	return htmlResource;
    }

    /**
     * @return
     * @uml.property  name="datas"
     */
    public List<E> getDatas()
    {
	return datas;
    }
    
    /**
     * @return JaspertReport DataSource
     */
    protected abstract JRDataSource getSource(); 
    
    /**
     * @return JasperReport File
     */
    protected abstract File getReportFile();
    
    /**
     * @return JasperReport Parameter
     */
    protected abstract Map<String, Object> getParameter();
   
}
