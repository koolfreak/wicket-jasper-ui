/**
 * 
 */
package com.ccti.jasper.model.customer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.ccti.jasper.model.BaseJasperModel;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 11, 2009 - 3:10:53 PM
 * 
 */
@Entity
@Proxy(lazy = false)
@Table(name = "customer_sales")
public class CustomerSales extends BaseJasperModel
{
    
    
    @Basic
    @Column(name="customer_fullname")
    private String customerName;
  
    
    @Basic
    @Column(name="store_sales")
    private Double storeSales;

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
    
    public Double getStoreSales()
    {
        return storeSales;
    }

    public void setStoreSales(Double storeSales)
    {
        this.storeSales = storeSales;
    }
    
}
