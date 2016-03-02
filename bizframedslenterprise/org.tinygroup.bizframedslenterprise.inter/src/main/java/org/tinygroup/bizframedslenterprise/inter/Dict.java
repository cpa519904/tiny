package org.tinygroup.bizframedslenterprise.inter;

import java.io.Serializable;


public class Dict implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5265906510950111535L;
	public static final String TYPE = "TDict";
	private String dictName;
	private int dictId;
	private String dictValue;
	private int dictType;
	private int sortNum;
	
	
	
	
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public int getDictId() {
		return dictId;
	}
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public int getDictType() {
		return dictType;
	}
	public void setDictType(int dictType) {
		this.dictType = dictType;
	}

	
	
}
