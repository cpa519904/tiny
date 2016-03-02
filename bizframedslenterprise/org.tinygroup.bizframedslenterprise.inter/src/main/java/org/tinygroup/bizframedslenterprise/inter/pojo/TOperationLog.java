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
import java.util.Date;

public class TOperationLog implements Serializable{

	private Integer operatorLogId;
	private String operationContent;
	private Integer userId;
	private String operationIp;
	private Integer operationType;
	private Date createTime;

	public void setOperatorLogId(Integer operatorLogId){
		this. operatorLogId = operatorLogId;
	}

	public Integer getOperatorLogId(){
		return operatorLogId;
	}

	public void setOperationContent(String operationContent){
		this. operationContent = operationContent;
	}

	public String getOperationContent(){
		return operationContent;
	}

	public void setUserId(Integer userId){
		this. userId = userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setOperationIp(String operationIp){
		this. operationIp = operationIp;
	}

	public String getOperationIp(){
		return operationIp;
	}

	public void setOperationType(Integer operationType){
		this. operationType = operationType;
	}

	public Integer getOperationType(){
		return operationType;
	}

	public void setCreateTime(Date createTime){
		this. createTime = createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

}
