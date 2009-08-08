/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;

import wicket.contrib.jasperreports.JRHtmlResource;
import wicket.contrib.jasperreports.JRResource;
import wicket.contrib.jasperreports.view.EmbeddedHtmlReport;

/**
 * @author Emmanuel Nollase - emanux created 2009 8 8 - 23:58:32
 */
public abstract class JRHtmlPageable<E> extends JRPageable<E>
{
    private static final Log log = LogFactory.getLog(JRHtmlPageable.class);

    private transient JRHtmlResource htmlResource;

    public JRHtmlPageable(String id, IModel model, int rowsPerPage)
    {
	super(id, model, rowsPerPage);
    }

    public JRHtmlPageable(final String id, final List<E> list,
	    final int rowsPerPage)
    {
	super(id, list, rowsPerPage);
    }

    @Override
    protected void displayReport(ListItem item)
    {
	item.add(new EmbeddedHtmlReport("report", getHtmlResource()));
	log.debug("finish exporting the report...");
    }

    protected JRResource getHtmlResource()
    {
	htmlResource = new JRHtmlResource(getReportFile());
	htmlResource.setReportParameters(getParameter());
	htmlResource.setReportDataSource(getSource());
	return htmlResource;
    }

}
