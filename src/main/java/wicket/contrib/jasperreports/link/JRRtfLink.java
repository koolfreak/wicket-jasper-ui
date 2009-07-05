/**
 * 
 */
package wicket.contrib.jasperreports.link;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.Link;

import wicket.contrib.jasperreports.JRRtfResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 4:15:51 PM
 */
public abstract class JRRtfLink extends JRResourceExportLink
{

    public JRRtfLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick(AjaxRequestTarget target)
    {
	JRRtfResource resource = new  JRRtfResource(getReportFile());
	resource.setReportDataSource(getSource());
	resource.setReportParameters(getParams());
	
	resource.onResourceRequested(); 
    }

}
