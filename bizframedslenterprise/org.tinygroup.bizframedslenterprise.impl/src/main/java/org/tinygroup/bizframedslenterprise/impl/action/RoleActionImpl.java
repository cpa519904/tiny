package org.tinygroup.bizframedslenterprise.impl.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.bizframedslenterprise.inter.action.RoleAction;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.role.RoleService;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

@Controller
public class RoleActionImpl  extends BaseAction implements RoleAction,WebContextAware{

	private WebContext webContext;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private LogAdapter logAdapter;

	public @ResponseBody void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	@RequestMapping(value = { "/addRole.do" })
	public @ResponseBody TRole addRoleMethod(TRole tRole) {
		writeOperatorLog(logAdapter,webContext, 1, "开始增加角色");
		tRole = roleService.addRole(tRole);
		writeOperatorLog(logAdapter,webContext, 1, "完成增加角色");
		return tRole;
	}

	@RequestMapping(value = { "/updateRole.do" })
	public @ResponseBody void updateRoleMethod(TRole tRole) {
		writeOperatorLog(logAdapter,webContext, 1, "开始更新角色");
		roleService.updateRole(tRole);
		writeOperatorLog(logAdapter,webContext, 1, "完成更新角色");
	}

	@RequestMapping(value = { "/removeRole.do" })
	public @ResponseBody void removeRoleMethod(String roleIds) {
		writeOperatorLog(logAdapter,webContext, 1, "开始刪除角色");
		roleService.removeRoles(roleIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成刪除角色");
	}

	@RequestMapping(value = { "/updateAuthorizeRoleFunctions.do" })
	public @ResponseBody void updateAuthorizeRoleFunctionsMethod(int roleId,String addFunctionIds,String removeFunctionIds) {
		writeOperatorLog(logAdapter,webContext, 1, "开始给角色分配权限");
		roleService.updateAuthorizeRoleFunctions(roleId, addFunctionIds,removeFunctionIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成给角色分配权限");
	}
	
	@RequestMapping(value = { "/updateAuthFunctionRoles.do" })
	public @ResponseBody void updateAuthFunctionRolesMethod(int functionId,
			String authorizeRoleIds, String removeRoleIds){
		writeOperatorLog(logAdapter,webContext, 1, "开始给权限分配角色");
		roleService.updateAuthorizeFunctionRoles(functionId, authorizeRoleIds, removeRoleIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成给权限分配角色");
	}
	
    @RequestMapping(value = {"/addRoleUsers.do"})
    public  @ResponseBody void addRoleUsers(int roleId, Integer[] userId) {
        writeOperatorLog(logAdapter, webContext, 1, "开始给角色分配用户");
        webContext.put("userId", userId);
        callService("addRoleUsers",webContext);
        writeOperatorLog(logAdapter, webContext, 1, "完成给角色分配用户");
    }
    
    @RequestMapping(value = {"/removeRoleUsers.do"})
    public  @ResponseBody void removeRoleUsers(int roleId, Integer[] userId) {
        writeOperatorLog(logAdapter, webContext, 1, "开始删除角色已分配的用户");
        webContext.put("userId", userId);
        callService("removeRoleUsers",webContext);
        writeOperatorLog(logAdapter, webContext, 1, "完成删除对角色已分配的用户");
    }
}
