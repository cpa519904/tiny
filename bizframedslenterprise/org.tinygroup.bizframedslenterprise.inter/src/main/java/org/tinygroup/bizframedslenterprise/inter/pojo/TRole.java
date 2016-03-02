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


public class TRole implements Serializable{

	private Integer roleId;
	private String roleName;
	private Integer roleType;
	private Integer sortNum;
	private String roleCode;
	private Integer isDefault;

	public void setRoleId(Integer roleId){
		this. roleId = roleId;
	}

	public Integer getRoleId(){
		return roleId;
	}

	public void setRoleName(String roleName){
		this. roleName = roleName;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleType(Integer roleType){
		this. roleType = roleType;
	}

	public Integer getRoleType(){
		return roleType;
	}

	public void setSortNum(Integer sortNum){
		this. sortNum = sortNum;
	}

	public Integer getSortNum(){
		return sortNum;
	}

	public void setRoleCode(String roleCode){
		this. roleCode = roleCode;
	}

	public String getRoleCode(){
		return roleCode;
	}

	public void setIsDefault(Integer isDefault){
		this. isDefault = isDefault;
	}

	public Integer getIsDefault(){
		return isDefault;
	}

}
