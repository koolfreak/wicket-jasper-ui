/**
 * 
 */
package wicket.contrib.jasperreports.link;

import org.apache.wicket.ajax.AjaxRequestTarget;

import wicket.contrib.jasperreports.JRCsvResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 4:15:12 PM
 */
public abstract class JRCsvLink extends JRResourceExportLink
{

    public JRCsvLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick(AjaxRequestTarget target)
    {
	JRCsvResource resource = new  JRCsvResource(getReportFile());
	resource.setReportDataSource(getSource());
	resource.setReportParameters(getParams());
	
	resource.onResourceRequested();

    }

}
