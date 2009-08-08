/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * @author Emmanuel A. Nollase
 * @created Sep 22, 2008 - 2:18:03 PM
 */
public abstract class JRDataView<E> extends JRDataViewBase
{

    public static final Log log = LogFactory.getLog(JRDataView.class);

    /** datas to rendered by JasperReports **/
    protected List<E> datas;

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

	    // boolean hasChildren = size() != 0;
	    ListItem item = null;
	    Iterator<E> data = getDataProvider().iterator(firstIndex, size);
	    while (data.hasNext())
	    {
		datas.add(data.next());
	    }

	    /*
	     * if (hasChildren) { // If this component does not already exist,
	     * populate it // item = (ListItem) get(Integer.toString(index)); }
	     */
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

    protected abstract void displayReport(ListItem item);

    /**
     * @return
     * @uml.property name="datas"
     */
    public List<E> getDatas()
    {
	return datas;
    }

}
