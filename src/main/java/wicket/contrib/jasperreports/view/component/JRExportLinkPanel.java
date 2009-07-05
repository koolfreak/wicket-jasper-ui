package wicket.contrib.jasperreports.view.component;

import java.io.File;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.wicket.markup.html.panel.Panel;

import wicket.contrib.jasperreports.link.JRCsvLink;
import wicket.contrib.jasperreports.link.JRImageLink;
import wicket.contrib.jasperreports.link.JRPdfLink;
import wicket.contrib.jasperreports.link.JRRtfLink;
import wicket.contrib.jasperreports.link.JRTextLink;
import wicket.contrib.jasperreports.link.JRXlsLink;

/**
 * @author Emmanuel Nollase - emanux
 * created: Jul 5, 2009 - 2:01:09 PM
 */
public abstract class JRExportLinkPanel extends Panel {

	/**
	 * 
	 * @param id - panel id
	 * @param hasPdflinlk - enable pdf export link
	 * @param hasCsvlink - enable csv export link
	 * @param hasImagelink - enable image export link
	 * @param hasRtflink - enable rtf export link
	 * @param hasXlslink - enable xls export link
	 * @param hasTxtlink - enable txt export link
	 */
	public JRExportLinkPanel(String id,boolean hasPdflink,boolean hasCsvlink,boolean hasImagelink,
							 boolean hasRtflink,boolean hasXlslink,boolean hasTxtlink) {
		super(id);
		
		final JRPdfLink pdflnk = new JRPdfLink("pdflink")
		{
			@Override
			public Map<String, Object> getParams() {
				return getParams();
			}
			@Override
			public File getReportFile() {
				return getReportFile();
			}
			@Override
			public JRDataSource getSource() {
				return getSource();
			}
		};
		add(pdflnk.setVisible(hasPdflink));
		
		final JRCsvLink csvlnk = new JRCsvLink("csvlink")
		{
			@Override
			public Map<String, Object> getParams() {
				return getParams();
			}
			@Override
			public File getReportFile() {
				return getReportFile();
			}
			@Override
			public JRDataSource getSource() {
				return getSource();
			}
		};
		add(csvlnk.setVisible(hasCsvlink));
		
		final JRImageLink imglnk = new JRImageLink("imglink")
		{
			@Override
			public Map<String, Object> getParams() {
				return getParams();
			}
			@Override
			public File getReportFile() {
				return getReportFile();
			}
			@Override
			public JRDataSource getSource() {
				return getSource();
			}
		};
		add(imglnk.setVisible(hasImagelink));
		
		final JRRtfLink rtflnk = new JRRtfLink("rtflink")
		{
			@Override
			public Map<String, Object> getParams() {
				return getParams();
			}
			@Override
			public File getReportFile() {
				return getReportFile();
			}
			@Override
			public JRDataSource getSource() {
				return getSource();
			}
		};
		add(rtflnk.setVisible(hasRtflink));
		
		final JRXlsLink xlslnk = new JRXlsLink("xlslink")
		{
			@Override
			public Map<String, Object> getParams() {
				return getParams();
			}
			@Override
			public File getReportFile() {
				return getReportFile();
			}
			@Override
			public JRDataSource getSource() {
				return getSource();
			}
		};
		add(xlslnk.setVisible(hasXlslink));
		
		final JRTextLink txtlnk = new JRTextLink("txtlink")
		{
			@Override
			public Map<String, Object> getParams() {
				return getParams();
			}
			@Override
			public File getReportFile() {
				return getReportFile();
			}
			@Override
			public JRDataSource getSource() {
				return getSource();
			}
		};
		add(txtlnk.setVisible(hasTxtlink));
		
		
	}

	public abstract JRDataSource getSource();
    
    public abstract File getReportFile();
    
    public abstract Map<String, Object> getParams();
}

