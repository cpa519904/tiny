package org.tinygroup.bizframedslenterprise.inter.role;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.User;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public interface RoleService {
	TRole addRole(TRole tRole);

	TRole getRole(String roleId);

	void updateRole(TRole tRole);

	void removeRole(String roleId);
	
	void removeRoles(String roleIds);

	List<TRole> getRoles(TRole tRole);

	Pager getRoles(TRole tRole, int start, int limit);

	Pager getRolePager(TRole tRole, int start, int limit);

	List<TFunction> getRoleFunctions(int roleId);

	void authorizeFunction(int roleId, int[] functionIds);

	void removeAuthorizeFunction(int roleId, int[] functionIds);

	void authorizeFunctionByString(int roleId, String functionIds);

	void removeAuthorizeFunctionByString(int roleId, String functionIds);

	void authorizeFunctionsToRoles(String roleIds,String functionIds);
	
	void removeFunctionsFromRoles(String roleIds,String functionIds);
	
	void updateAuthorizeRoleFunctions(int roleId,
			String authorizeFunctionIds, String removeFunctionIds);
	
	User[] getUsersByRoleId(int roleId);
	
	void updateAuthorizeFunctionRoles(Integer functionId,
			String authorizeRoleIds, String removeRoleIds);

	Pager getRoleUsers(int start,int limit, Integer roleId,TUser tUser);
	
	Pager getUnssignRoleUsers(int start,int limit,Integer roleId,TUser tUser);

	void addRoleUsers(int roleId, Integer[] userId);
	
	void removeRoleUsers(int roleId, Integer[] userId);
}
