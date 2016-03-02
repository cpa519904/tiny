package org.tinygroup.bizframedslenterprise.impl.role;

import java.util.List;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TRoleDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TRoleTFunctionDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TUserTRoleDao;
import org.tinygroup.bizframedslenterprise.inter.User;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRoleTFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUserTRole;
import org.tinygroup.bizframedslenterprise.inter.role.RoleService;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public class RoleServiceImpl extends BaseService implements RoleService {
	private TRoleDao tRoleDao;
	
	private TRoleTFunctionDao tRoleTFunctionDao;
	
	private TUserTRoleDao tUserTRoleDao;
	
	public TRoleTFunctionDao gettRoleTFunctionDao() {
		return tRoleTFunctionDao;
	}

	public void settRoleTFunctionDao(TRoleTFunctionDao tRoleTFunctionDao) {
		this.tRoleTFunctionDao = tRoleTFunctionDao;
	}

	public TRoleDao gettRoleDao() {
		return tRoleDao;
	}

	public void settRoleDao(TRoleDao tRoleDao) {
		this.tRoleDao = tRoleDao;
	}

	public TRole addRole(TRole tRole) {
		return tRoleDao.insertTRole(tRole);
	}

	public TRole getRole(String roleId) {
		return tRoleDao.getTRoleById(roleId);
	}

	public void updateRole(TRole tRole) {
		tRoleDao.updateTRole(tRole);
	}

	public void removeRole(String roleId) {
		tRoleDao.deleteTRole(roleId);
	}

	public void removeRoles(String roleIds) {
		String[] roleIdStr = roleIds.split(",");
		tRoleDao.deleteTRolesByIds(roleIdStr);
	}

	public List<TRole> getRoles(TRole tRole) {
		return tRoleDao.queryTRoles(tRole);
	}

	public Pager getRoles(TRole tRole, int start, int limit) {
		return tRoleDao.queryTRolesPager(tRole, start, limit);
	}

	public Pager getRolePager(TRole tRole, int start, int limit) {
		return tRoleDao.queryTRolesPager(tRole, start, limit);
	}

	public List<TFunction> getRoleFunctions(int roleId) {
		return tRoleTFunctionDao.queryRoleFunctions(roleId);
	}

	public void authorizeFunction(int roleId, int[] functionIds) {
		// TODO Auto-generated method stub
		
	}

	public void removeAuthorizeFunction(int roleId, int[] functionIds) {
		// TODO Auto-generated method stub
		
	}

	public void authorizeFunctionByString(int roleId, String functionIds) {
		// TODO Auto-generated method stub
		
	}

	public void removeAuthorizeFunctionByString(int roleId, String functionIds) {
		// TODO Auto-generated method stub
		
	}

	public void authorizeFunctionInsteadByString(int roleId, String functionIds,String removeFunctionIds) {
		
	}

	public void authorizeFunctionsToRoles(String roleIds, String functionIds) {
		// TODO Auto-generated method stub
		
	}

	public void removeFunctionsFromRoles(String roleIds, String functionIds) {
		// TODO Auto-generated method stub
		
	}

	public void updateAuthorizeFunctionRoles(Integer functionId,
			String authorizeRoleIds, String removeRoleIds) {
		if(removeRoleIds!=null && removeRoleIds.trim().length()>0){
			Integer[] removeRoleArray = toArray(removeRoleIds);
			tRoleTFunctionDao.deleteTFunctionTRoles(functionId, removeRoleArray);
		}
		if(authorizeRoleIds!=null && authorizeRoleIds.trim().length()>0){
			Integer[] authorizeUserIdArray = toArray(authorizeRoleIds);
			for(int roleId : authorizeUserIdArray){
				TRoleTFunction tRoleTFunction = new TRoleTFunction();
				tRoleTFunction.setFunctionId(functionId);
				tRoleTFunction.setRoleId(roleId);
				tRoleTFunctionDao.insertTRoleTFunction(tRoleTFunction);
			}
		}
	}
	
	public void updateAuthorizeRoleFunctions(int roleId,
			String authorizeFunctionIds, String removeFunctionIds) {
		if(removeFunctionIds!=null && removeFunctionIds.trim().length()>0){
			Integer[] removeFunctionArray = toArray(removeFunctionIds);
			tRoleTFunctionDao.deleteTRoleTFunctions(roleId, removeFunctionArray);
		}
		if(authorizeFunctionIds!=null && authorizeFunctionIds.trim().length()>0){
			Integer[] authorizeFunctionIdArray = toArray(authorizeFunctionIds);
			for(int functionId:authorizeFunctionIdArray){
				TRoleTFunction tRoleTFunction = new TRoleTFunction();
				tRoleTFunction.setFunctionId(functionId);
				tRoleTFunction.setRoleId(roleId);
				tRoleTFunctionDao.insertTRoleTFunction(tRoleTFunction);
			}
		}
	}

	public User[] getUsersByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager getRoleUsers(int start,int limit, Integer roleId,TUser tUser) {
		if(tUser!=null) tUser.setLoginName(tUser.getUserName());
		return tRoleDao.queryRoleTUsers(start,limit,roleId, tUser);
	}

	public Pager getUnssignRoleUsers(int start,int limit,Integer roleId,TUser tUser) {
		if(tUser!=null) tUser.setLoginName(tUser.getUserName());
		return tRoleDao.queryUnassignRoleTUsers(start,limit,roleId, tUser);
	}
	
	public void addRoleUsers(int roleId, Integer[] userId) {
		for(int uId:userId){
			TUserTRole tUserTRole = new TUserTRole();
			tUserTRole.setUserId(uId);
			tUserTRole.setRoleId(roleId);
			tUserTRoleDao.insertTUserTRole(tUserTRole);
		}
	}

	public void removeRoleUsers(int roleId, Integer[] userId) {
		tUserTRoleDao.deleteTRoleTUser(roleId,userId);
	}

	public TUserTRoleDao gettUserTRoleDao() {
		return tUserTRoleDao;
	}

	public void settUserTRoleDao(TUserTRoleDao tUserTRoleDao) {
		this.tUserTRoleDao = tUserTRoleDao;
	}


}
