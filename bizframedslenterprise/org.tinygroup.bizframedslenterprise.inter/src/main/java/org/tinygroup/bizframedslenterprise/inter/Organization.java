package org.tinygroup.bizframedslenterprise.inter;

import java.io.Serializable;

public class Organization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1142514917885293887L;
	public static final String TYPE = "TOrganization";
	private String organizationName;
	private int organizationId;
	private int parentId;
	private String companyNum;
	private String description;
	private int sortNum;
	
	

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(String companyNum) {
		this.companyNum = companyNum;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
