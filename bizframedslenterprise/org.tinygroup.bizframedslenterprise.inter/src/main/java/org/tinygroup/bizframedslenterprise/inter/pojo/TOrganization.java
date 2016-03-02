/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.bizframedslenterprise.inter.pojo;

import java.io.Serializable;


public class TOrganization implements Serializable{

	private Integer organizationId;
	private String organizationName;
	private Integer parentId;
	private Integer sortNum;

	public void setOrganizationId(Integer organizationId){
		this. organizationId = organizationId;
	}

	public Integer getOrganizationId(){
		return organizationId;
	}

	public void setOrganizationName(String organizationName){
		this. organizationName = organizationName;
	}

	public String getOrganizationName(){
		return organizationName;
	}

	public void setParentId(Integer parentId){
		this. parentId = parentId;
	}

	public Integer getParentId(){
		return parentId;
	}

	public void setSortNum(Integer sortNum){
		this. sortNum = sortNum;
	}

	public Integer getSortNum(){
		return sortNum;
	}

}
