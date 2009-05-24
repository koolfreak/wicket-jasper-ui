/**
 * 
 */
package com.ccti.jasper.session;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 26, 2009 - 3:01:53 PM
 * 
 */
public class JasperSession extends WebSession {

	private String reportId;

	public JasperSession(Request request) {
		super(request);
	}

	public static JasperSession get() {
		return (JasperSession) Session.get();
	}

	public synchronized String getReportId() {
		return reportId;
	}

	public synchronized void setReportId(String reportId) {
		this.reportId = reportId;
		dirty();
	}

}
