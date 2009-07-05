/*
 * $Id: EmbeddedJRReport.java 1959 2007-04-19 04:11:08Z eelco12 $
 * $Revision: 1959 $ $Date: 2007-04-19 12:11:08 +0800 (Thu, 19 Apr 2007) $
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.jasperreports.view;

import org.apache.wicket.IResourceListener;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;

import wicket.contrib.jasperreports.JRResource;

/**
 * Component for embedding a jasper report in a page. This component must be attached to an &lt;object&gt; tag. If you don't want to embed the report, but have a link to it instead, use  {@link ResourceReference} .
 * @author  <a href="mailto:evanchooly@gmail.com">Justin Lee</a>
 */
public final class EmbeddedJRReport extends WebComponent implements IResourceListener
{
	/**
	 * @uml.property  name="resource"
	 * @uml.associationEnd  
	 */
	private final JRResource resource;

	/**
	 * Construcxt.
	 * 
	 * @param componentID
	 *            component componentID
	 * @param resource
	 *            the resource
	 */
	public EmbeddedJRReport(String componentID, JRResource resource)
	{
		super(componentID);
		this.resource = resource;
	}

	/**
	 * @see wicket.IResourceListener#onResourceRequested()
	 */
	public void onResourceRequested()
	{
		resource.onResourceRequested();
	}

	/**
	 * @see wicket.Component#onComponentTag(wicket.markup.ComponentTag)
	 */
	protected void onComponentTag(ComponentTag tag)
	{
		if (!"embed".equalsIgnoreCase(tag.getName()))
		{
			findMarkupStream().throwMarkupException(
					"Component "
							+ getId() + " must be applied to a tag of type 'object' not "
							+ tag.toUserDebugString());
		}
		tag.put("src", getResponse().encodeURL(urlFor(IResourceListener.INTERFACE)));
		tag.put("type", resource.getContentType());
		super.onComponentTag(tag);
		
	}
	
	
}