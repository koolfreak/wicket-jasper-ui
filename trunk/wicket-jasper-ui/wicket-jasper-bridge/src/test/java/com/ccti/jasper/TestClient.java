/**
 * 
 */
package com.ccti.jasper;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccti.jasper.cxf.webservice.JasperCXFService;
import com.ccti.jasper.cxf.webservice.JasperServiceModel;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Mar 29, 2009 - 4:01:30 PM
 */
public class TestClient extends TestCase
{
    
    
  /*public void testGetReportClass()
    {
	ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
	factory.setServiceClass(JasperCXFService.class);
	factory.setAddress("http://localhost:8080/jasper/services/jasperservice/");
      	
      ClassPathXmlApplicationContext context 
      = new ClassPathXmlApplicationContext(new String[] {"wicket-jasper-cxfclient-impl.xml"});
      
      
	JasperCXFService client = (JasperCXFService) context.getBean("jasperCXFService");
	
	String resp = client.callReportName("simple", "123");
	System.out.println(resp);
    }*/

    /*public void testSetUserCredentials()
    {
	ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
	factory.setServiceClass(JasperCXFService.class);
	factory.setAddress("http://localhost:8080/jasper/services/jasperservice/");
	JasperCXFService client = (JasperCXFService) factory.create();
	
	JasperModel model = new JasperModel();
	model.setId("123");
	
	client.setUserCredentials(model);
	
    }*/
    
    public void testSet()
    {
	  ClassPathXmlApplicationContext context 
	        = new ClassPathXmlApplicationContext(new String[] {"wicket-jasper-cxfclient-impl.xml"});

	  JasperCXFService service = (JasperCXFService) context.getBean("jasperCXFService");
	 
	  JasperServiceModel model = new JasperServiceModel();
	  service.setUserCredentials(model);
    }
}
