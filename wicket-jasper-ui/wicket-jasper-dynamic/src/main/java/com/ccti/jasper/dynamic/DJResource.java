/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import wicket.contrib.jasperreports.JRResource;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;


/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 6 - 11:38:16
 */
public abstract class DJResource extends JRResource
{
    protected DynamicReportBuilder reportBuilder;

    public DJResource()
    {
	super();
    }
    
    public DJResource(InputStream report)
    {
	super(report);
    }
    
    public DJResource(URL report)
    {
	super(report);
    }
    
    public DJResource(File report)
    {
	super(report);
    }
    
    @Override
    protected JasperPrint newJasperPrint() throws JRException
    {
	
	return  DynamicJasperHelper.generateJasperPrint(getDynamicReport(), new ClassicLayoutManager(), getReportDataSource(), getReportParameters());
	
    }

    public DynamicReport getDynamicReport()
    {
        return getReportBuilder().build();
    }

    public DynamicReportBuilder getReportBuilder()
    {
        return reportBuilder;
    }

    public void setReportBuilder(DynamicReportBuilder reportBuilder)
    {
        this.reportBuilder = reportBuilder;
    }

}
