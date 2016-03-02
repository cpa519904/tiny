package org.tinygroup.bizframedslenterprise.inter;

import java.io.Serializable;

public class Function implements
		Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8119905152594539554L;
	public static final String TYPE="TFunction";
	public static final int SUBFUNCTION = 1; // 子功能节点
	public static final int FUNCTION = 0; // 菜单节点
	private int functionId;
	private String functionName;
	private int functionType;
	private String functionUrl;
	private int parentId;
	private String description;
	private int sortNum;
	private String functionCode;
	

	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return "TFunction";
	}
	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
