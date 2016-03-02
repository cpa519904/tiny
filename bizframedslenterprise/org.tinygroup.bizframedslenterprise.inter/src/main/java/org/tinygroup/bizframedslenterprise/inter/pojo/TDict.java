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


public class TDict implements Serializable{

	private Integer dictId;
	private String dictName;
	private String dictValue;
	private Integer dictType;
	private Integer sortNum;

	public void setDictId(Integer dictId){
		this. dictId = dictId;
	}

	public Integer getDictId(){
		return dictId;
	}

	public void setDictName(String dictName){
		this. dictName = dictName;
	}

	public String getDictName(){
		return dictName;
	}

	public void setDictValue(String dictValue){
		this. dictValue = dictValue;
	}

	public String getDictValue(){
		return dictValue;
	}

	public void setDictType(Integer dictType){
		this. dictType = dictType;
	}

	public Integer getDictType(){
		return dictType;
	}

	public void setSortNum(Integer sortNum){
		this. sortNum = sortNum;
	}

	public Integer getSortNum(){
		return sortNum;
	}

}
