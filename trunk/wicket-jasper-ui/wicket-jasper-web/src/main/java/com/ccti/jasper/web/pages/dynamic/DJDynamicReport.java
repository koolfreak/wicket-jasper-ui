/**
 * 
 */
package com.ccti.jasper.web.pages.dynamic;

import java.util.Date;

import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.ccti.jasper.dynamic.core.DJReportBase;

/**
 * @author Emmanuel Nollase - emanux 
 * created 2009 8 7 - 23:40:11
 */

public class DJDynamicReport extends DJReportBase
{

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ccti.jasper.dynamic.core.DJReportBase#initilizeBuilder(ar.com.fdvs
     * .dj.domain.builders.DynamicReportBuilder)
     */
    @Override
    public void initilizeBuilder(DynamicReportBuilder reportBuilder)
    {
	reportBuilder.setTitle("Testing Dynamic report");
	//reportBuilder.setSubtitle("This report was generated: " + new Date());
	reportBuilder.setPrintBackgroundOnOddRows(true);
	reportBuilder.setUseFullPageWidth(true);
	
	reportBuilder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT);
	reportBuilder.addAutoText(AutoText.AUTOTEXT_CREATED_ON, AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT,AutoText.PATTERN_DATE_DATE_TIME);

    }

    @Override
    public String getDynamicReportName()
    {
	return "Test";
    }

    @Override
    public LayoutManager getReportLayout()
    {
	return new ClassicLayoutManager();
    }

}
