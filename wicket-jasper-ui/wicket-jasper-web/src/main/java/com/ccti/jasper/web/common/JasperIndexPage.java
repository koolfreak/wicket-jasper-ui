/*
 * $Id: Index.java 279 2005-09-07 21:17:58Z eelco12 $ $Revision: 279 $ $Date:
 * 2005-09-08 05:17:58 +0800 (Thu, 08 Sep 2005) $
 * 
 * ==================================================================== Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ccti.jasper.web.common;


/**
 * @author Emmanuel A. Nollase - emanux
 * @created Feb 20, 2009 - 9:26:14 AM
 * 
 */
public class JasperIndexPage extends BaseJasperPage
{
    
     /**
     * Constructor
     */
    public JasperIndexPage()
    {
	add(new JasperMenu("menu"));
    }
}