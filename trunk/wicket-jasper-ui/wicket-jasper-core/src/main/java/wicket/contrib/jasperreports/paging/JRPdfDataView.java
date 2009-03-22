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

import wicket.contrib.jasperreports.JRPdfResource;
import wicket.contrib.jasperreports.JRResource;
import wicket.contrib.jasperreports.view.EmbeddedPdfReport;


/**
 * @author Emmannuel A. Nollase
 * Created: Jan 28, 2009 - 1:40:28 PM
 */
public abstract class JRPdfDataView<E> extends JRDataViewBase
{

    public static final Log log = LogFactory.getLog(JRPdfDataView.class);

    /** datas to rendered by JasperReports **/
    private List<E> datas;
    transient EmbeddedPdfReport embeddedPdfReport;
    transient JRPdfResource pdfResource;

    private static final long serialVersionUID = 1L;

    /**
     * @param id
     *            component id
     * @param dataProvider
     *            data provider
     * @param itemsPerPage
     *            items per page
     */
    public JRPdfDataView(String id, IDataProvider dataProvider, int itemsPerPage)
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
	embeddedPdfReport = new EmbeddedPdfReport("report", getPdfResource());
	item.add(embeddedPdfReport);
	log.debug("Finish exporting [PDF] report...");
    }

    protected JRResource getPdfResource() 
    {
	pdfResource = new JRPdfResource(getReportFile());
	pdfResource.setReportParameters(getParameter());
	pdfResource.setReportDataSource(getSource());
	return pdfResource;
    }

    /**
	 * @return
	 * @uml.property  name="datas"
	 */
    public List<E> getDatas()
    {
	return datas;
    }
    
    protected abstract JRDataSource getSource(); 

    protected abstract File getReportFile();

    protected abstract Map<String, Object> getParameter();

}
