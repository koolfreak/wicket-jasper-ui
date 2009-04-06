/**
 * 
 */
package wicket.contrib.jasperreports.link;

import wicket.contrib.jasperreports.JRPdfResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 4:14:38 PM
 */
public abstract class JRPdfLink extends JRResourceExportLink
{

    public JRPdfLink(String id)
    {
	super(id);
	
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	JRPdfResource resource = new  JRPdfResource(getReportFile());
	resource.setReportDataSource(getSource());
	resource.setReportParameters(getParams());
	
	resource.onResourceRequested(); 

    }

}
