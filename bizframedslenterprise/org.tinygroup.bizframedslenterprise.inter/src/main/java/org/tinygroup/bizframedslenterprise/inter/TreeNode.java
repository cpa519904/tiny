package org.tinygroup.bizframedslenterprise.inter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3474762326551112872L;
	private int id;
	private Object info;
	private List<TreeNode> subNode = new ArrayList<TreeNode>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public List<TreeNode> getSubNode() {
		return subNode;
	}
	public void setSubNode(List<TreeNode> subNode) {
		this.subNode = subNode;
	}
	
	public TreeNode(){
		
	}
	
	public TreeNode(int id,Object info){
		this.id =id;
		this.info = info;
	}
}
