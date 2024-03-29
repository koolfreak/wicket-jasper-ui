/**
 * 
 */
package wicket.contrib.jasperreports.view;

import org.apache.wicket.IResourceListener;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;

import wicket.contrib.jasperreports.JRResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Jan 28, 2009 - 1:36:54 PM
 */
public class EmbeddedPdfReport extends WebComponent implements IResourceListener
{
    
	transient private JRResource resource;
	/**
	 * Construct.
	 * 
	 * @param componentID
	 *            component componentID
	 * @param resource
	 *            the resource
	 */
	public EmbeddedPdfReport(String componentID, JRResource resource)
	{
		super(componentID);
		this.resource = resource;
	}

	/**
	 * @see wicket.IResourceListener#onResourceRequested()
	 */
	public void onResourceRequested()
	{
	       resource.onResourceRequested();
	}

	/**
	 * @see wicket.Component#onComponentTag(wicket.markup.ComponentTag)
	 */
	protected void onComponentTag(ComponentTag tag)
	{
		if (!"embed".equalsIgnoreCase(tag.getName()))
		{
			findMarkupStream().throwMarkupException(
					"Component "
							+ getId() + " must be applied to a tag of type 'embed' not "
							+ tag.toUserDebugString());
		}
		tag.put("src", getResponse().encodeURL(urlFor(IResourceListener.INTERFACE)));
		tag.put("type", resource.getContentType());
		tag.put("fullscreen", "yes");
		super.onComponentTag(tag);
	}

}
