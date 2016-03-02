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

import static org.tinygroup.tinysqldsl.Insert.insertInto;

import java.io.Serializable;

import org.tinygroup.tinysqldsl.Insert;


public class TUserTRole implements Serializable{

	private Integer id;
	private Integer roleId;
	private Integer userId;

	public void setId(Integer id){
		this. id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setRoleId(Integer roleId){
		this. roleId = roleId;
	}

	public Integer getRoleId(){
		return roleId;
	}

	public void setUserId(Integer userId){
		this. userId = userId;
	}

	public Integer getUserId(){
		return userId;
	}
}
