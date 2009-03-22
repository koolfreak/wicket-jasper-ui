/**
 * 
 */
package com.ccti.jasper.service.customer;

import java.util.List;

import com.ccti.jasper.dao.CustomerSalesDao;
import com.ccti.jasper.model.customer.CustomerSales;
import com.ccti.jasper.service.BaseJasperServiceImpl;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:20:55 PM
 * 
 */
public class CustomerSalesServiceImpl implements CustomerSalesService
{

    private CustomerSalesDao customerSalesDao;
    
    public void setCustomerSalesDao(CustomerSalesDao customerSalesDao)
    {
        this.customerSalesDao = customerSalesDao;
    }

    /* (non-Javadoc)
     * @see com.ccti.jasper.service.EmployeeService#countAll()
     */
    public int countAll()
    {
	return customerSalesDao.countAll();
    }

    /* (non-Javadoc)
     * @see com.ccti.jasper.service.EmployeeService#load(java.lang.String)
     */
    public CustomerSales load(Integer id)
    {
	return customerSalesDao.load(id);
    }

    /* (non-Javadoc)
     * @see com.ccti.jasper.service.EmployeeService#loadAll(int, int)
     */
    public List<CustomerSales> loadAll(int first, int max)
    {
	return customerSalesDao.loadAll(first, max);
    }

    public List<CustomerSales> loadAll()
    {
	return customerSalesDao.loadAll();
    }

}
