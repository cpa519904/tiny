package org.tinygroup.bizframedslenterprise.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.tinygroup.bizframedslenterprise.inter.TreeNode;
import org.tinygroup.bizframedslenterprise.inter.user.UserService;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.weblayer.AbstractTinyFilter;
import org.tinygroup.weblayer.WebContext;

public class BizframeFilter extends AbstractTinyFilter {
	private final static String BIZFRAME_FUNCTION_ID = "@bizframe_function_id";
	private UserService userService;
	private Map<Integer, TreeNode> functionMap = new HashMap<Integer, TreeNode>();
	private final String PATH_PARAM = "error-path";
	private final String PATH_DEFAULT_VALUE = "";
	
	public void preProcess(WebContext context) throws ServletException, IOException{
		String functionIdString = context.get(BIZFRAME_FUNCTION_ID);
		boolean pass = false;
		if (!StringUtil.isBlank(functionIdString)){
			if(functionMap.containsKey(Integer.parseInt(functionIdString))){
				pass = true;
			}
		}
		if(!pass){
			String path = getPath();
			//TODO：待renhui修改接口后取消注释
			context.getResponse().sendRedirect(path);
		}
	}

	private String getPath(){
		String path = get(PATH_PARAM);
		if(StringUtil.isBlank(path)){
			path = PATH_DEFAULT_VALUE;
		}
		if(path.startsWith("/")){
			path=path.substring(1);
		}
		return path;
	}
	
	private int getUserId() {
		return 0;//TODO:从session中读取
	}

	private void toMap(TreeNode node){
		functionMap.put(node.getId(), node);
		for(TreeNode sub:node.getSubNode()){
			toMap(sub);
		}
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void postProcess(WebContext context) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void customInit() {
		int userId = getUserId();
		TreeNode[] functionTree  = userService.getUserFunctionTree(userId);
		for(TreeNode function:functionTree){
			toMap(function);
		}
		
	}

}
