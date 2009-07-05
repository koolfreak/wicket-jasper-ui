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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import wicket.contrib.jasperreports.JRHtmlResource;
import wicket.contrib.jasperreports.JRResource;
import wicket.contrib.jasperreports.view.EmbeddedHtmlReport;

/**
 * @author  Emmanuel A. Nollase
 * @created  Sep 22, 2008 - 2:18:03 PM
 */
public abstract class JRDataView<E> extends JRDataViewBase
{

    public static final Log log = LogFactory.getLog(JRDataView.class);

    /** datas to rendered by JasperReports **/
    transient List<E> datas;
    transient EmbeddedHtmlReport embeddedHtmlReport;
    transient JRBeanCollectionDataSource dataSource;
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
    public JRDataView(String id, IDataProvider dataProvider, int itemsPerPage)
    {
	super(id, dataProvider, itemsPerPage);
	this.datas = new ArrayList<E>();
    }

    
    @Override
    protected void onPopulate()
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

	   /* if (hasChildren)
	    {
		// If this component does not already exist, populate it
		// item = (ListItem) get(Integer.toString(index));
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

    protected void displayReport(ListItem item) 
    {
	//final JRResource htmlResource = getHtmlResource();
	embeddedHtmlReport = new EmbeddedHtmlReport("report", getHtmlResource());
	item.add(embeddedHtmlReport);
	log.debug("finish exporting the report...");
    }

    private JRDataSource getSource() 
    {
	try
	{
	    dataSource = new JRBeanCollectionDataSource(getDatas());
	    return dataSource;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	throw new WicketRuntimeException("Oppsss there is an error...please refresh the page");
    }

   /* protected JRResource getHtmlResource() 
    {
	htmlResource = new JRHtmlResource(getReportFile());
	htmlResource.setReportParameters(getParameter());
	htmlResource.setReportDataSource(getSource());
	return htmlResource;
    }*/

    /**
	 * @return
	 * @uml.property  name="datas"
	 */
    public List<E> getDatas()
    {
	return datas;
    }
    protected abstract JRResource getHtmlResource();

    protected abstract File getReportFile();

    protected abstract Map<String, Object> getParameter();

}
