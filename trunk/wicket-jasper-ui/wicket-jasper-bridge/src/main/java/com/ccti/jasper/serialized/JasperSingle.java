/**
 * 
 */
package com.ccti.jasper.serialized;

import java.io.Serializable;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 27, 2009 - 3:21:31 PM
 * 
 */
public class JasperSingle implements Serializable
{

    private JasperObject jasperObject;
    
    private static JasperSingle jasperSingle;
    
    private JasperSingle()
    {
	
    }
    
    public static JasperSingle getInstance()
    {
	if(null == jasperSingle)
	{
	    jasperSingle = new JasperSingle();
	}
	
	return jasperSingle;
    }

    
    public JasperObject getJasperObject()
    {
        return jasperObject;
    }

    
    public void setJasperObject(JasperObject jasperObject)
    {
        this.jasperObject = jasperObject;
    }
    
    
}
