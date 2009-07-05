/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.File;
import org.apache.wicket.version.undo.Change;

import wicket.contrib.jasperreports.JRHtmlResource;
import wicket.contrib.jasperreports.JRResource;
import wicket.contrib.jasperreports.view.EmbeddedHtmlReport;


/**
 * A implementation of JasperReport pagination.
 * @author  Emmanuel A. Nollase
 * @created  Sep 19, 2008 - 3:33:43 PM
 */
public abstract class JRPageable<E> extends JRRepeater implements IPageable
{

    private static final Log log = LogFactory.getLog(JRPageable.class);

    /** The page to show. */
    private int currentPage;

    /** Number of rows per page of the list view. */
    private int rowsPerPage;

    /** The datas to be displayed in the report **/
    protected final Collection<E> datas;

    /**
     * Constructor
     * 
     * @param id
     *            See Component
     * @param model
     *            See Component
     * @param rowsPerPage
     *            Number of rows to show on a page
     */
    public JRPageable(final String id, final IModel model, int rowsPerPage)
    {
	super(id, model);
	this.rowsPerPage = rowsPerPage;
	this.datas = new ArrayList<E>();
    }

    /**
     * Creates a pageable list view having the given number of rows per page
     * that uses the provided object as a simple model.
     * 
     * @param id
     *            See Component
     * @param list
     *            See Component
     * @param rowsPerPage
     *            Number of rows to show on a page
     * @see ListView#ListView(String, List)
     */
    public JRPageable(final String id, final List list, final int rowsPerPage)
    {
	super(id, list);
	this.rowsPerPage = rowsPerPage;
	this.datas = new ArrayList<E>();
    }

    /**
	 * Gets the index of the current page being displayed by this list view.
	 * @return  Returns the currentPage.
	 * @uml.property  name="currentPage"
	 */
    public final int getCurrentPage()
    {
	// If first cell is out of range, bring page back into range
	while ((currentPage * rowsPerPage) > getList().size())
	{
	    currentPage--;
	}

	return currentPage;
    }

    /**
     * Gets the number of pages in this list view.
     * 
     * @return The number of pages in this list view
     */
    public final int getPageCount()
    {
	return ((getList().size() + rowsPerPage) - 1) / rowsPerPage;
    }

    /**
	 * Gets the maximum number of rows on each page.
	 * @return  the maximum number of rows on each page.
	 * @uml.property  name="rowsPerPage"
	 */
    public final int getRowsPerPage()
    {
	return rowsPerPage;
    }

    /**
	 * Sets the maximum number of rows on each page.
	 * @param rowsPerPage  the maximum number of rows on each page.
	 * @uml.property  name="rowsPerPage"
	 */
    public final void setRowsPerPage(int rowsPerPage)
    {
	if (rowsPerPage < 0)
	{
	    rowsPerPage = 0;
	}

	addStateChange(new RowsPerPageChange(this.rowsPerPage));
	this.rowsPerPage = rowsPerPage;
    }

    /**
     * @see org.apache.wicket.markup.html.list.ListView#getViewSize()
     */
    public int getViewSize()
    {
	if (getModelObject() != null)
	{
	    super.setStartIndex(getCurrentPage() * getRowsPerPage());
	    super.setViewSize(getRowsPerPage());
	}

	return super.getViewSize();
    }

    /**
	 * Sets the current page that this list view should show.
	 * @param currentPage  The currentPage to set.
	 * @uml.property  name="currentPage"
	 */
    public final void setCurrentPage(int currentPage)
    {
	if (currentPage < 0)
	{
	    currentPage = 0;
	}

	int pageCount = getPageCount();
	if ((currentPage > 0) && (currentPage >= pageCount))
	{
	    currentPage = pageCount - 1;
	}

	addStateChange(new CurrentPageChange(this.currentPage));
	this.currentPage = currentPage;
    }

   
    /**
     * Records the changing of the current page.
     */
    private class CurrentPageChange extends Change
    {

	private static final long serialVersionUID = 1L;

	/** the former 'current' page. */
	private final int currentPage;

	/**
	 * Construct.
	 * 
	 * @param currentPage
	 *            the former 'current' page
	 */
	CurrentPageChange(int currentPage)
	{
	    this.currentPage = currentPage;
	}

	/**
	 * @see org.apache.wicket.version.undo.Change#undo()
	 */
	public void undo()
	{
	    setCurrentPage(currentPage);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return "CurrentPageChange[currentPage: " + currentPage + "]";
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onPopulate()
    {
	// Get number of items to be displayed
	final int size = getViewSize();
	log.debug("View Size = "+size);
	if (size > 0)
	{

	    // reset previously stored data in the collection
	    datas.clear();
	    // Automatically rebuild all ListItems before rendering the list
	    // view
	    removeAll();

	    boolean hasChildren = size() != 0;
	    ListItem item = null;
	    // Loop through the markup in this container for each item
	    for (int i = 0; i < size; i++)
	    {
		// Get index
		
		final int index = firstIndex + i;
		
		log.debug("Index = "+index+" firstIndex = "+firstIndex+" i = "+i);

		if (hasChildren)
		{
		    // If this component does not already exist, populate it
		    item = (ListItem) get(Integer.toString(index));
		}
		if (item == null)
		{
		    // Create item for index
		    item = newItem(index);
		    // Add list item
		    add(item);
		}

		datas.add((E) getListItemModel(getModel(), index).getObject());

	    }
	    displayReport(item);
	}
	else
	{
	    removeAll();
	}

    }

    /**
     * Records the changing of the number of rows per page.
     */
    private class RowsPerPageChange extends Change
    {

	private static final long serialVersionUID = 1L;

	/** the former number of rows per page. */
	private final int rowsPerPage;

	/**
	 * Construct.
	 * 
	 * @param rowsPerPage
	 *            the former number of rows per page
	 */
	RowsPerPageChange(int rowsPerPage)
	{
	    this.rowsPerPage = rowsPerPage;
	}

	/**
	 * @see org.apache.wicket.version.undo.Change#undo()
	 */
	public void undo()
	{
	    setRowsPerPage(rowsPerPage);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return "RowsPerPageChange[component: " + getPath() + ", prefix: " + rowsPerPage + "]";
	}
    }

    protected void displayReport(ListItem item)
    {
	final JRResource pdfResource = getPdfResource();
	item.add(new EmbeddedHtmlReport("report", pdfResource));
	log.debug("finish exporting the report...");
    }

    private JRDataSource getSource()
    {
	try
	{
	    return new JRSerializeBeanDataSource(getDatas());
	}
	catch (InvalidClassException e)
	{
	    e.printStackTrace();
	}
	return null;//throw new WicketRuntimeException("Oppsss there is an error...please refresh the page");
    }

    protected JRResource getPdfResource()
    {
	return new JRHtmlResource(getReportFile()).setReportParameters(getParameter())
	.setReportDataSource(getSource());
    }

    /**
	 * @return
	 * @uml.property  name="datas"
	 */
    public Collection getDatas()
    {
	return datas;
    }

    protected abstract File getReportFile();

    protected abstract Map<String, Object> getParameter();

}
