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


public class TSysparam implements Serializable{

	private Integer sysparamId;
	private Integer sysparamType;
	private String sysparamName;
	private String sysparamValue;

	public void setSysparamId(Integer sysparamId){
		this. sysparamId = sysparamId;
	}

	public Integer getSysparamId(){
		return sysparamId;
	}

	public void setSysparamType(Integer sysparamType){
		this. sysparamType = sysparamType;
	}

	public Integer getSysparamType(){
		return sysparamType;
	}

	public void setSysparamName(String sysparamName){
		this. sysparamName = sysparamName;
	}

	public String getSysparamName(){
		return sysparamName;
	}

	public void setSysparamValue(String sysparamValue){
		this. sysparamValue = sysparamValue;
	}

	public String getSysparamValue(){
		return sysparamValue;
	}

}
