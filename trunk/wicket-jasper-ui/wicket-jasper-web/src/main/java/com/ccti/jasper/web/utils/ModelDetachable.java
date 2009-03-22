/**
 * 
 */
package com.ccti.jasper.web.utils;

import org.apache.wicket.model.LoadableDetachableModel;

import com.ccti.jasper.service.BaseJasperService;
import com.ccti.jasper.service.customer.CustomerSalesService;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:21:39 PM
 * 
 */
public class ModelDetachable extends LoadableDetachableModel
{
    private final BaseJasperService jasperService;
    private final Integer id;
    
    
    public ModelDetachable(Integer id, BaseJasperService jasperService)
    {
	this.jasperService = jasperService;
	this.id = id;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.model.LoadableDetachableModel#load()
     */
    @Override
    protected Object load()
    {
	return jasperService.load(id);
    }

}
