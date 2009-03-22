/**
 * 
 */
package com.ccti.jasper.dao;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ccti.jasper.model.customer.CustomerSales;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:35:23 PM
 * 
 */
public class CustomerSalesDaoImpl extends HibernateDaoSupport implements CustomerSalesDao
{

    /* (non-Javadoc)
     * @see com.ccti.jasper.dao.EmployeeDao#countAll()
     */
    public int countAll()
    {
	return (Integer) this.getSession().createCriteria(CustomerSales.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    /* (non-Javadoc)
     * @see com.ccti.jasper.dao.EmployeeDao#load(java.lang.String)
     */
    public CustomerSales load(Integer id)
    {
	return (CustomerSales) getHibernateTemplate().load(CustomerSales.class, id);
    }

    /* (non-Javadoc)
     * @see com.ccti.jasper.dao.EmployeeDao#loadAll(int, int)
     */
    @SuppressWarnings("unchecked")
    public List<CustomerSales> loadAll(int first, int max)
    {
	return this.getSession().createCriteria(CustomerSales.class).setFirstResult(first).setMaxResults(max).list();
    }

    @SuppressWarnings("unchecked")
    public List<CustomerSales> loadAll()
    {
	return this.getSession().createCriteria(CustomerSales.class).list();
    }

}
