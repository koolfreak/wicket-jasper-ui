/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * Wicket complain that {@link JRBeanCollectionDataSource } is not {@link Serializable}.
 * 
 * @author Emmanuel A. Nollase
 * @created Sep 19, 2008 - 10:27:46 AM
 */
public final class JRSerializeBeanDataSource extends JRBeanCollectionDataSource implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    
    /**
     * @param beanCollection
     */
    public JRSerializeBeanDataSource(Collection beanCollection) throws InvalidClassException
    {
	super(beanCollection);
    }

}
