/**
 * 
 */
package wicket.contrib.jasperreports.view;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

import wicket.contrib.jasperreports.JRResource;


/**
 * @author Emmannuel A. Nollase
 * Created: Jan 9, 2009 - 11:55:04 AM
 */
public final class EmbeddedHtmlReport extends Label implements IResourceListener
{
    /** to avoid serialization problem of the resource **/
    final transient JRResource resource;
    
    public EmbeddedHtmlReport(String id, JRResource resource)
    {
	super(id);
	this.resource = resource;
	
	setEscapeModelStrings(false);
	
	InputStream is = null; 
	try
	{
	   is = this.resource.getResourceStream().getInputStream();
	   setDefaultModel(new Model<String>(IOUtils.toString(is)));
	    
	}
	catch (ResourceStreamNotFoundException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}catch(NullPointerException e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    IOUtils.closeQuietly(is);
	}
	
    }

    public void onResourceRequested()
    {
	resource.onResourceRequested(); 
    }
    
}
