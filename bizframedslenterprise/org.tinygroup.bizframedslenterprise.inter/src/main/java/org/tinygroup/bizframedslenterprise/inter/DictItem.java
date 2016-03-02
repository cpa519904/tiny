package org.tinygroup.bizframedslenterprise.inter;

import java.io.Serializable;

public class DictItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6644671926619803508L;
	public static final String TYPE = "TDictitem";
	private String dictitemName;
	private String dictitemValue;
	private int dictitemId;
	private int dictId;
	private int sortNum;
	private String description;

	
	

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

	public String getDictitemName() {
		return dictitemName;
	}

	public void setDictitemName(String dictitemName) {
		this.dictitemName = dictitemName;
	}

	public String getDictitemValue() {
		return dictitemValue;
	}

	public void setDictitemValue(String dictitemValue) {
		this.dictitemValue = dictitemValue;
	}

	public int getDictitemId() {
		return dictitemId;
	}

	public void setDictitemId(int dictitemId) {
		this.dictitemId = dictitemId;
	}

	public int getDictId() {
		return dictId;
	}

	public void setDictId(int dictId) {
		this.dictId = dictId;
	}

}
