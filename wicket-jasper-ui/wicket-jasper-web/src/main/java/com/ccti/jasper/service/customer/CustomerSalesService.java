/**
 * 
 */
package com.ccti.jasper.service.customer;

import java.util.List;

import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.service.BaseJasperService;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:17:02 PM
 * 
 */
public interface CustomerSalesService extends BaseJasperService
{
    CustomerSales load(final Integer id);
    
    List<CustomerSales> loadAll(final int first,final int max);
    
    int countAll();
    
    List<CustomerSales> loadAll();
}
