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


public class TFunction implements Serializable{

	private Integer functionId;
	private Integer parentId;
	private String functionName;
	private Integer functionType;
	private String functionUrl;
	private Integer sortNum;
	private String functionCode;

	public void setFunctionId(Integer functionId){
		this. functionId = functionId;
	}

	public Integer getFunctionId(){
		return functionId;
	}

	public void setParentId(Integer parentId){
		this. parentId = parentId;
	}

	public Integer getParentId(){
		return parentId;
	}

	public void setFunctionName(String functionName){
		this. functionName = functionName;
	}

	public String getFunctionName(){
		return functionName;
	}

	public void setFunctionType(Integer functionType){
		this. functionType = functionType;
	}

	public Integer getFunctionType(){
		return functionType;
	}

	public void setFunctionUrl(String functionUrl){
		this. functionUrl = functionUrl;
	}

	public String getFunctionUrl(){
		return functionUrl;
	}

	public void setSortNum(Integer sortNum){
		this. sortNum = sortNum;
	}

	public Integer getSortNum(){
		return sortNum;
	}

	public void setFunctionCode(String functionCode){
		this. functionCode = functionCode;
	}

	public String getFunctionCode(){
		return functionCode;
	}

}
