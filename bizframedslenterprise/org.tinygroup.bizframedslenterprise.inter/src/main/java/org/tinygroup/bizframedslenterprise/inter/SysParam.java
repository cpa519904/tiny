package org.tinygroup.bizframedslenterprise.inter;

import java.io.Serializable;

public class SysParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3405582500141687265L;
	public static final String TYPE = "TSysparam";
	private String sysparamName;
	private int sysparamId;
	private String sysparamValue;
	private int sysparamType;
	private String description;
	private String sortNum;
	


	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSysparamName() {
		return sysparamName;
	}

	public void setSysparamName(String sysparamName) {
		this.sysparamName = sysparamName;
	}

	public int getSysparamId() {
		return sysparamId;
	}

	public void setSysparamId(int sysparamId) {
		this.sysparamId = sysparamId;
	}

	public String getSysparamValue() {
		return sysparamValue;
	}

	public void setSysparamValue(String sysparamValue) {
		this.sysparamValue = sysparamValue;
	}

	public int getSysparamType() {
		return sysparamType;
	}

	public void setSysparamType(int sysparamType) {
		this.sysparamType = sysparamType;
	}

}
