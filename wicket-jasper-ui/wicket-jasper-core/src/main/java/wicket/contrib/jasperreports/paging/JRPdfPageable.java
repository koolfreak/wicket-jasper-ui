/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import wicket.contrib.jasperreports.JRPdfResource;
import wicket.contrib.jasperreports.JRResource;
import wicket.contrib.jasperreports.view.EmbeddedPdfReport;

/**
 * @author Emmanuel Nollase - emanux created: Jul 9, 2009 - 4:03:20 PM
 */
public abstract class JRPdfPageable<E> extends JRPageable<E>
{

    private static final Log log = LogFactory.getLog(JRPdfPageable.class);

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
    public JRPdfPageable(final String id, final IModel model,
	    final int rowsPerPage)
    {
	super(id, model, rowsPerPage);
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
    public JRPdfPageable(final String id, final List<E> list,
	    final int rowsPerPage)
    {
	super(id, list, rowsPerPage);
    }

    @Override
    protected void displayReport(ListItem item)
    {
	item.add(new EmbeddedPdfReport("report", getPdfResource()));
	log.info("Finish exporting [PDF] report...");
    }

    protected JRResource getPdfResource()
    {
	final JRPdfResource pdfResource = new JRPdfResource(getReportFile());
	pdfResource.setReportParameters(getParameter());
	pdfResource.setReportDataSource(getSource());
	return pdfResource;
    }

}
