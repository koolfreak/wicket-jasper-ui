/**
 * 
 */
package wicket.contrib.jasperreports.link;

import org.apache.wicket.ajax.AjaxRequestTarget;

import wicket.contrib.jasperreports.JRXlsResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 3:07:58 PM
 */
public abstract class JRXlsLink extends JRResourceExportLink
{
    public JRXlsLink(String id)
    {
	super(id);
    }

    @Override
    public void onClick()
    {
	JRXlsResource resource = new  JRXlsResource(getReportFile());
	resource.setReportDataSource(getSource());
	resource.setReportParameters(getParams());
	
	resource.onResourceRequested();
    }

}
