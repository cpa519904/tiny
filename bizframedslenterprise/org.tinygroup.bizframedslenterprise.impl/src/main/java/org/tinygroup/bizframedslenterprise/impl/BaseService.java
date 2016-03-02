package org.tinygroup.bizframedslenterprise.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tinygroup.bizframedslenterprise.inter.TreeNode;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;

public class BaseService {

	private static final String STATUS = "status";
	protected static final String ALLOW = "0";

	private List<String> relationTypeList = new ArrayList<String>();
	private Map<String, String> relationMap = new HashMap<String, String>(); // <relationType,objectType>


	protected <T> List<T> getObjects(String subjectType, int subjectId,
			String objectType) {
		List<List<String>> lists = new ArrayList<List<String>>();
		for (String relationType : relationTypeList) {
			if (!relationType.startsWith(subjectType)) {
				continue;
			}
			List<String> path = new ArrayList<String>();
			List<String> skipRelation = new ArrayList<String>();
			find(relationType, subjectType, objectType, path, skipRelation);
			if (objectType.equals(path.get(path.size() - 1))) {
				lists.add(path);
			}

		}

		return null;
	}

	public String getFullFieldKey(String tableName,String fieldKey){
		return tableName+"."+fieldKey;
	}

	private void find(String relationType, String subjectType,
			String targetObjectType, List<String> path,
			List<String> skipRelation) {
		path.add(subjectType);//
		String firstType = relationType.substring(0, subjectType.length());
		if (firstType.equals(targetObjectType)) {
			path.add(targetObjectType);
			return;
		}
		skipRelation.add(relationType);

		for (String findRelationType : relationTypeList) {
			if (!findRelationType.startsWith(firstType)
					|| skipRelation.contains(findRelationType)) {
				continue;
			}
			find(findRelationType, firstType, targetObjectType, path,
					skipRelation);

		}

	}

	
	protected String getRelationType(String permissionSubjectType,
			String permissionObjectType) {
		return permissionSubjectType + permissionObjectType;
	}


	protected Integer[] toArray(String str) {
		if (str == null || str.trim().length() == 0) {
			return new Integer[0];
		}
		String[] array = str.split(",");
		Integer[] idArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			idArray[i] = Integer.parseInt(array[i]);
		}
		return idArray;
	}

	protected TreeNode[] toTreeByList(List<TFunction> tUserTFunctions,List<TFunction> tFunctions){
		TFunction[] userFunctionArray = new TFunction[tUserTFunctions!=null?tUserTFunctions.size():0];
		TFunction[] tFunctionArray = new TFunction[tFunctions!=null?tFunctions.size():0];
		tUserTFunctions.toArray(userFunctionArray);
		tFunctions.toArray(tFunctionArray);
		return toTree(userFunctionArray , tFunctionArray);
	}
	
	protected TreeNode[] toTree(TFunction[] userFunctions,
			TFunction[] functions) {
		if (userFunctions == null) {
			return new TreeNode[0];
		}
		// 存放所有的权限菜单
		Map<Integer, TreeNode> nodeMap = new HashMap<Integer, TreeNode>();
		for (TFunction function : functions) {
			TreeNode node = new TreeNode(function.getFunctionId(), function);
			nodeMap.put(node.getId(), node);
		}
		// 存放已计算过的节点Id
		List<Integer> computedIds = new ArrayList<Integer>();
		// 存放已计算过的节点
		Map<Integer, TreeNode> computedNodes = new HashMap<Integer, TreeNode>();
		for (TFunction tFunction : userFunctions) {
			TreeNode node = new TreeNode(tFunction.getFunctionId(), tFunction);
			computed(node, computedIds, computedNodes, nodeMap);
		}

		return toArray(computedNodes);
	}

	protected TreeNode[] toArray(Map<Integer, TreeNode> computedNodes) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (TreeNode node : computedNodes.values()) {
			TFunction function = (TFunction) node.getInfo();
			if (function.getParentId()==null || function.getParentId() == 0) {
				list.add(node);
				continue;
			}
		}
		TreeNode[] tree = new TreeNode[list.size()];
		int i = 0;
		for (TreeNode node : list) {
			tree[i] = node;
			i++;
		}
		return tree;
	}

	protected void computed(TreeNode node, List<Integer> computedIds,
			Map<Integer, TreeNode> computedNodes, Map<Integer, TreeNode> nodeMap) {
		int id = node.getId();
		if (computedNodes.containsKey(id)) {
			return;
		}
		// 如果没算过，就把他存放到已算列表
		computedNodes.put(id, node);
		computedIds.add(id);
		// 然后计算该节点的父节点
		TFunction tFunction = (TFunction) node.getInfo();
		int parentId = 0;
		if(tFunction.getParentId()!=null){
			parentId = tFunction.getParentId();
		}
		if (parentId==0) {
			return;
		}
		TFunction parent = (TFunction) nodeMap.get(parentId).getInfo();
		TreeNode nodeParaent = computedNodes.get(parentId);
		if (nodeParaent == null) {
			nodeParaent = new TreeNode(parentId, parent);
		}
		nodeParaent.getSubNode().add(node);
		computed(nodeParaent, computedIds, computedNodes, nodeMap);

	}
	

}
