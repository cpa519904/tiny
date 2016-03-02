package org.tinygroup.bizframedslenterprise.inter.user;


import org.tinygroup.bizframedslenterprise.inter.Function;
import org.tinygroup.bizframedslenterprise.inter.TreeNode;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

import java.util.List;

public interface UserService {
	TUser addUser(TUser tUser);

	void updateUser(TUser tUser);

	void deleteUser(int userId);
	
	void deleteUsers(String userIds);

	TUser getUser(int userId);

	List<TUser> getUsers(TUser tUser);

	List<TUser> getUsers(TUser tUser, int start, int limit);
	
	Pager getUserPager(TUser tUser, int start, int limit);

	List<TUser> getUsers(int organizationId);

	List<TUser> getUsers(int organizationId, int start, int limit);
	
	Pager getUserPager(int organizationId, int start, int limit);

	void authorizeRole(int userId, int[] roleIds);

	void removeAuthorizeRole(int userId, int[] roleIds);
	
	void authorizeRoleByString(int userId, String roleIds);

	void removeAuthorizeByString(int userId, String roleIds);
	
	Function[] getUserAllFunctions(int userId);
	
	Function[] getUserMenuFunctions(int userId);
	
	TreeNode[] getUserMenuFunctionTree(int userId);
	
	Function[] getUserMenuSubFunctions(int userId,int menuFunctionId);
	
	List<TRole> getUserRoles(String userId);
	
	List<TRole> getUnassignedUserRoles(String userId);
	
	TreeNode[] getUserFunctionTree(int userId);
	
	void authorizeRoleInsteadByString(int userId, String roleIds,String removeRoleIds);
	
	void authorizeRolesToUsers(String userIds,String roleIds);
	
	void removeAuthorizeRolesFromUsers(String userIds,String roleIds);
	
	void updateAuthorizeRolesUsers(int roleId,String authorizeUserIds, String removeUserIds);
	
	/**
	 * 登录验证，未加密密码
	 * @param loginname
	 * @param password
	 * @return
	 */
	TUser loginValidate(String loginname, String password);
	
	/**
	 * 登录验证,已加密密码
	 * @param loginname
	 * @param password
	 * @return
	 */
	TUser loginValidateByEncodedPwd(String loginname, String password);
	
	boolean isUserExist(TUser tUser);
	
	List<TUser> getUsersByRoleAndOrganizationId(int roleId, int organizationId);

	TFunction checkSubFunctionByUserId(int userId, int functionId);
	
	TFunction checkSubFunction(int userId,int functionCode);
	
	void addUserRoles(int userId, Integer[] roleId);
	
	void removeUserRoles(int userId,Integer[] roleId);
}
