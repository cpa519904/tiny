package org.tinygroup.bizframedslenterprise.inter.action;

import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;


public interface RoleAction {
	TRole addRoleMethod(TRole tRole);
	
	void updateRoleMethod(TRole tRole);
	
	void removeRoleMethod(String roleIds);
	
	void updateAuthorizeRoleFunctionsMethod(int roleId,String addFunctionIds,String removeFunctionIds);
	
	void updateAuthFunctionRolesMethod(int functionId,String authorizeRoleIds, String removeRoleIds);
	
	void addRoleUsers(int roleId, Integer[] userId);
	
	void removeRoleUsers(int roleId, Integer[] userId);
}
