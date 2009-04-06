/**
 * 
 */
package wicket.contrib.jasperreports.link;

import java.io.File;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.wicket.markup.html.link.Link;


/**
 * @author Emmannuel A. Nollase
 * Created: Apr 6, 2009 - 4:19:04 PM
 */
public abstract class JRResourceExportLink extends Link 
{

    public JRResourceExportLink(String id)
    {
	super(id);
    }

    public abstract JRDataSource getSource();
    
    public abstract File getReportFile();
    
    public abstract Map<String, Object> getParams();
}
