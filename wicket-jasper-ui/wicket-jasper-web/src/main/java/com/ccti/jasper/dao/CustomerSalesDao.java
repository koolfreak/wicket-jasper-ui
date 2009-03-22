/**
 * 
 */
package com.ccti.jasper.dao;

import java.util.List;

import com.ccti.jasper.model.customer.CustomerSales;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:33:47 PM
 * 
 */
public interface CustomerSalesDao
{
    CustomerSales load(final Integer id);
    
    List<CustomerSales> loadAll(final int first,final int max);
    
    int countAll();
    
    List<CustomerSales> loadAll();
}
