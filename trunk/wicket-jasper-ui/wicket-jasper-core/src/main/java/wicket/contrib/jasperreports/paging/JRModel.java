/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.util.List;

import org.apache.wicket.model.IModel;



/**
 * @author  Emmanuel A. Nollase
 * @created  Sep 19, 2008 - 5:20:30 PM
 */
public class JRModel implements IModel
{

    /**
	 * @uml.property  name="jrRepeater"
	 * @uml.associationEnd  
	 */
    private final JRRepeater jrRepeater;
    
    private final int index;
    
    /**
     * 
     */
    public JRModel(final JRRepeater jrRpeater, final int index)
    {
	this.jrRepeater = jrRpeater;
	this.index = index;
    }
    /* (non-Javadoc)
     * @see org.apache.wicket.model.IModel#getObject()
     */
    public Object getObject()
    {
	return ((List)jrRepeater.getModelObject()).get(index);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.model.IModel#setObject(java.lang.Object)
     */
    public void setObject(Object object)
    {
	((List)jrRepeater.getModelObject()).set(index, object);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    public void detach()
    {

    }

}
