/**
 * 
 */
package wicket.contrib.jasperreports.link;

import org.apache.wicket.IResourceListener;
import org.apache.wicket.Resource;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Emmannuel A. Nollase Created: Feb 2, 2009 - 4:19:05 PM
 */
public class JRResourceLink extends Link implements IResourceListener
{

    /** The Resource */
    transient final Resource resource;

    /**
     * Constructs a link directly to the provided resource.
     * 
     * @param id
     *                See Component
     * @param resource
     *                The resource
     */
    public JRResourceLink(final String id, final Resource resource)
    {
	super(id);
	this.resource = resource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    public void onClick()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.IResourceListener#onResourceRequested()
     */
    public void onResourceRequested()
    {
	resource.onResourceRequested();
	onClick();
    }

    protected final CharSequence getURL()
    {
	return urlFor(IResourceListener.INTERFACE);
    }

}
