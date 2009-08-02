/**
 * 
 */
package wicket.contrib.jasperreports.link;

import org.apache.wicket.ajax.AjaxRequestTarget;

import wicket.contrib.jasperreports.JRTextResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 4:16:23 PM
 */
public abstract class JRTextLink extends JRResourceExportLink
{

    public JRTextLink(String id)
    {
	super(id);
	// TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	JRTextResource resource = new  JRTextResource(getReportFile());
	resource.setReportDataSource(getSource());
	resource.setReportParameters(getParams());
	
	resource.onResourceRequested();

    }

}
