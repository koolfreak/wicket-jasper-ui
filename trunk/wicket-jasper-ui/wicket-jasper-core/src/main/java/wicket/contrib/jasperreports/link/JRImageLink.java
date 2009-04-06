/**
 * 
 */
package wicket.contrib.jasperreports.link;

import wicket.contrib.jasperreports.JRImageResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 4:16:59 PM
 */
public abstract class JRImageLink extends JRResourceExportLink
{

    public JRImageLink(String id)
    {
	super(id);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick()
    {
	JRImageResource resource = new  JRImageResource(getReportFile());
	resource.setReportDataSource(getSource());
	resource.setReportParameters(getParams());
	
	resource.onResourceRequested(); 

    }

}
