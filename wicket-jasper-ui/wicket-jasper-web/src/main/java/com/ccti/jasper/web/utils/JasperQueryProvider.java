/**
 * 
 */
package com.ccti.jasper.web.utils;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.ccti.jasper.model.BaseJasperModel;
import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.service.BaseJasperService;
import com.ccti.jasper.service.customer.CustomerSalesService;



/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:25:03 PM
 * 
 */
public abstract class JasperQueryProvider extends SortableDataProvider
{
    private BaseJasperService jasperService;

    public JasperQueryProvider(BaseJasperService jasperService)
    {
	this.jasperService = jasperService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    public IModel model(Object object)
    {
	final BaseJasperModel model = (BaseJasperModel) object;
	return new CompoundPropertyModel(new ModelDetachable(model.getId(),jasperService));
    }
}
