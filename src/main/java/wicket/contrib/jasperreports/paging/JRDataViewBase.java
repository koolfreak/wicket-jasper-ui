/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.util.Collections;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.version.undo.Change;

/**
 * @author Emmanuel A. Nollase
 * @created Sep 23, 2008 - 5:58:49 PM
 */
public abstract class JRDataViewBase extends JRRepeater implements IPageable
{

    private final IDataProvider dataProvider;

    /** Max number (not index) of items to show */
    private int viewSize = Integer.MAX_VALUE;

    /** The page to show. */
    private int currentPage;

    /** Number of rows per page of the list view. */
    private int rowsPerPage;

    /** Total size of data get ** */
    private int dataSize;

    /**
     * 
     * @param id
     * @param provider
     * @param rowsPerPage
     */
    public JRDataViewBase(String id, IDataProvider dataProvider, int rowsPerPage)
    {
	super(id, Collections.EMPTY_LIST);
	if (dataProvider == null) 
	{ 
	    throw new IllegalArgumentException("argument [dataProvider] cannot be null"); 
	}
	this.dataProvider = dataProvider;
	this.rowsPerPage = rowsPerPage;
	setRowsPerPage(rowsPerPage);
	this.dataSize = dataProvider.size();
    }

    /**
     * Gets the index of the current page being displayed by this list view.
     * 
     * @return Returns the currentPage.
     * @uml.property name="currentPage"
     */
    public final int getCurrentPage()
    {
	// If first cell is out of range, bring page back into range
	while ((currentPage * rowsPerPage) > getDataSize())
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
	return ((getDataSize() + rowsPerPage) - 1) / rowsPerPage;
    }

    public final int getDataSize()
    {
	// return dataProvider.size();
	return dataSize;
    }

    /**
     * Gets the maximum number of rows on each page.
     * 
     * @return the maximum number of rows on each page.
     * @uml.property name="rowsPerPage"
     */
    public final int getRowsPerPage()
    {
	return rowsPerPage;
    }

    /**
     * Sets the maximum number of rows on each page.
     * 
     * @param rowsPerPage
     *                the maximum number of rows on each page.
     * @uml.property name="rowsPerPage"
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

    public int getDataViewSize()
    {

	setDataStartIndex(getCurrentPage() * getRowsPerPage());
	setDataViewSize(getRowsPerPage());

	return getPageJRSize();
    }

    public int getPageJRSize()
    {
	int size = viewSize;

	// Adjust view size to model object's list size
	final int modelSize = getDataSize();
	if (firstIndex > modelSize) { return 0; }

	if ((size == Integer.MAX_VALUE) || ((firstIndex + size) > modelSize))
	{
	    size = modelSize - firstIndex;
	}

	// firstIndex + size must be smaller than Integer.MAX_VALUE
	if ((Integer.MAX_VALUE - size) < firstIndex) { throw new IllegalStateException(
	"firstIndex + size must be smaller than Integer.MAX_VALUE"); }

	return size;
    }

    public void setDataStartIndex(final int startIndex)
    {
	firstIndex = startIndex;
	if (firstIndex < 0)
	{
	    firstIndex = 0;
	}
	else if (firstIndex > getDataSize())
	{
	    firstIndex = 0;
	}

    }

    public void setDataViewSize(final int size)
    {
	viewSize = size;

	if (viewSize < 0)
	{
	    viewSize = Integer.MAX_VALUE;
	}

    }

    /**
     * Sets the current page that this list view should show.
     * 
     * @param currentPage
     *                The currentPage to set.
     * @uml.property name="currentPage"
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
	 *                the former 'current' page
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
	 *                the former number of rows per page
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

    /**
     * @see org.apache.wicket.markup.repeater.AbstractPageableView#onDetach()
     */
    protected void onDetach()
    {
	dataProvider.detach();
	super.onDetach();
    }

    /**
     * @return
     * @uml.property name="dataProvider"
     */
    public IDataProvider getDataProvider()
    {
	return dataProvider;
    }

}
