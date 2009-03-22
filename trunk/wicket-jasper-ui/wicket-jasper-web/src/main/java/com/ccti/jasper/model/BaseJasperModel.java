/**
 * 
 */
package com.ccti.jasper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 21, 2009 - 4:31:35 PM
 *
 */
@MappedSuperclass
public class BaseJasperModel implements Serializable
{

    @Id()
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")*/
    @Column(name="customer_id",length = 11)
    private Integer id;
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
