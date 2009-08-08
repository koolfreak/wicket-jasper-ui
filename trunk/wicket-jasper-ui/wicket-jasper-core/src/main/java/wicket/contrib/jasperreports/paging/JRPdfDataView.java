/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.io.File;
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
public abstract class JRPdfDataView<E> extends JRDataView<E>
{

    public static final Log log = LogFactory.getLog(JRPdfDataView.class);

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
    }

    
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

    protected abstract JRDataSource getSource(); 

    protected abstract File getReportFile();

    protected abstract Map<String, Object> getParameter();

}
