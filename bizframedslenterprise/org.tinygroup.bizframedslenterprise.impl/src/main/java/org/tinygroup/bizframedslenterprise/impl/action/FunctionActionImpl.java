package org.tinygroup.bizframedslenterprise.impl.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.bizframedslenterprise.impl.util.CheckResult;
import org.tinygroup.bizframedslenterprise.inter.action.FunctionAction;
import org.tinygroup.bizframedslenterprise.inter.function.FunctionService;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

@Controller
public class FunctionActionImpl  extends BaseAction implements FunctionAction,WebContextAware{
	private WebContext webContext;
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private LogAdapter logAdapter;
	
	private static final String BEAN_TYPE = "TFunction";

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	@RequestMapping(value= {"/addFunction.do"})
	public @ResponseBody TFunction addFunctionMethod() {
		TFunction tFunction = webContext.get(BEAN_TYPE);
		writeOperatorLog(logAdapter,webContext, 1, "开始新增权限");
		functionService.addFunction(tFunction);
		writeOperatorLog(logAdapter,webContext, 1, "完成删除权限");
		return tFunction;
	}

	@RequestMapping(value= {"/updateFunction.do"})
	public @ResponseBody void updateFuncitonMethod() {
		TFunction tFunction = webContext.get(BEAN_TYPE);
		writeOperatorLog(logAdapter,webContext, 1, "开始更新权限");
		functionService.updateFunciton(tFunction);
		writeOperatorLog(logAdapter,webContext, 1, "完成删除权限");
	}

	@RequestMapping(value= {"/deleteFunctions.do"})
	public @ResponseBody void deleteFunctionsMethod(String functionIds) {
		writeOperatorLog(logAdapter,webContext, 1, "开始更新权限");
		functionService.deleteFunctions(functionIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成删除权限");
	}

	@RequestMapping(value= {"/addFunctionRoles.do"})
	public @ResponseBody void addFunctionRoles(int functionId,Integer[] roleId) {
		writeOperatorLog(logAdapter,webContext, 1, "开始给权限分配角色");
		 webContext.put("roleId", roleId);
		 callService("addFunctionRoles", webContext);
		writeOperatorLog(logAdapter,webContext, 1, "完成给权限分配角色");
	}
	
	@RequestMapping(value= {"/removeFunctionRoles.do"})
	public @ResponseBody void removeFunctionRoles(int functionId,Integer[] roleId) {
		writeOperatorLog(logAdapter,webContext, 1, "开始取消权限对应的角色");
		webContext.put("roleId", roleId);
		callService("removeFunctionRoles", webContext);
		writeOperatorLog(logAdapter,webContext, 1, "完成取消权限对应的角色");
	}
	
	@RequestMapping(value= {"/checkFunction.do"})
	public @ResponseBody CheckResult checkFunctionRoles(String functionId,String name,String param) {
		String info = "已存在";
		String status = "n";
		if(null!=param && param.length()>0){
			webContext.put(name, param);
			boolean exits = (Boolean) callService("checkFunction", webContext);
			if(!exits){
				info = "";
				status = "y";
			}
		}
		CheckResult checkResult = new CheckResult();
		checkResult.setInfo(info);
		checkResult.setStatus(status);
		return checkResult;
	}

}
