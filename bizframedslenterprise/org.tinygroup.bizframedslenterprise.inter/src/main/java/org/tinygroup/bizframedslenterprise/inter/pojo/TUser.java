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


public class TUser implements Serializable{

	private Integer userId;
	private Integer organizationId;
	private String userName;
	private String userPassword;
	private Integer userType;
	private String userStatus;
	private Integer sortNum;
	private String loginName;
	private Integer isDefault;
	private String organizationName;
	private TOrganization tOrganization;

	public void setUserId(Integer userId){
		this. userId = userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setOrganizationId(Integer organizationId){
		this. organizationId = organizationId;
	}

	public Integer getOrganizationId(){
		return organizationId;
	}

	public void setUserName(String userName){
		this. userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserPassword(String userPassword){
		this. userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserType(Integer userType){
		this. userType = userType;
	}

	public Integer getUserType(){
		return userType;
	}

	public void setUserStatus(String userStatus){
		this. userStatus = userStatus;
	}

	public String getUserStatus(){
		return userStatus;
	}

	public void setSortNum(Integer sortNum){
		this. sortNum = sortNum;
	}

	public Integer getSortNum(){
		return sortNum;
	}

	public void setLoginName(String loginName){
		this. loginName = loginName;
	}

	public String getLoginName(){
		return loginName;
	}

	public void setIsDefault(Integer isDefault){
		this. isDefault = isDefault;
	}

	public Integer getIsDefault(){
		return isDefault;
	}

	public TOrganization gettOrganization() {
		return tOrganization;
	}

	public void settOrganization(TOrganization tOrganization) {
		this.tOrganization = tOrganization;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	

}
