/**
 * 
 */
package com.ccti.jasper.dynamic;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperPrint;
import wicket.contrib.jasperreports.JRResource;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;


/**
 * @author Emmanuel Nollase - emanux
 * created 2009 8 6 - 11:38:16
 */
public abstract class DJResource extends JRResource
{
    protected DynamicReportBuilder reportBuilder;
    
    protected LayoutManager layoutManager;

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
	final Map<String, Object> param = new HashMap<String, Object>();
	param.put(JRParameter.REPORT_VIRTUALIZER, getFileVirtualizer());
	
	return  DynamicJasperHelper.generateJasperPrint(getDynamicReport(), getLayoutManager(), getReportDataSource(), param);
	
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

    public LayoutManager getLayoutManager()
    {
        return layoutManager;
    }

    public void setLayoutManager(LayoutManager layoutManager)
    {
        this.layoutManager = layoutManager;
    }
    
}
