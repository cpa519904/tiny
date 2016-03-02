package org.tinygroup.bizframedslenterprise.impl.user;

import org.springframework.stereotype.Service;
import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TFunctionDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TRoleDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TUserDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TUserTRoleDao;
import org.tinygroup.bizframedslenterprise.impl.security.EncryptionProcessor;
import org.tinygroup.bizframedslenterprise.inter.Function;
import org.tinygroup.bizframedslenterprise.inter.TreeNode;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUserTRole;
import org.tinygroup.bizframedslenterprise.inter.user.UserService;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	
	private TUserDao tUserDao;
	
	private TFunctionDao tFunctionDao;
	
	private TOrganizationDao tOrganizationDao;
	
	private TUserTRoleDao tUserTRoleDao;
	
	private TRoleDao tRoleDao;
	
	private EncryptionProcessor encryptionProcessor;

	
	
	public TOrganizationDao gettOrganizationDao() {
		return tOrganizationDao;
	}

	public void settOrganizationDao(TOrganizationDao tOrganizationDao) {
		this.tOrganizationDao = tOrganizationDao;
	}

	public TFunctionDao gettFunctionDao() {
		return tFunctionDao;
	}

	public void settFunctionDao(TFunctionDao tFunctionDao) {
		this.tFunctionDao = tFunctionDao;
	}

	public TUserDao gettUserDao() {
		return tUserDao;
	}

	public void settUserDao(TUserDao tUserDao) {
		this.tUserDao = tUserDao;
	}

	public EncryptionProcessor getEncryptionProcessor() {
		return encryptionProcessor;
	}

	public void setEncryptionProcessor(EncryptionProcessor encryptionProcessor) {
		this.encryptionProcessor = encryptionProcessor;
	}

	public TUser addUser(TUser tUser) {
		return tUserDao.insertTUser(tUser);
	}

	public void updateUser(TUser tUser) {
		tUserDao.updateTUser(tUser);
	}

	public void deleteUser(int userId) {
		tUserDao.deleteTUser(userId);
	}

	public void deleteUsers(String userIds) {
		tUserDao.deleteTUserByIds(toArray(userIds));
	}

	public TUser getUser(int userId) {
		return tUserDao.getTUserById(userId);
	}

	public List<TUser> getUsers(TUser tUser) {
		// TODO Auto-generated method stub
		return tUserDao.queryTUsers(tUser);
	}

	public List<TUser> getUsers(TUser tUser, int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager getUserPager(
			TUser tUser, int start, int limit) {
		Pager pager = tUserDao.queryTUsersPager(tUser, start, limit);
		for(Object tUserobj : pager.getCurrentList()){
			TUser user = (TUser)tUserobj;
			if(user.getOrganizationId()!=null){
				user.settOrganization(tOrganizationDao.getTOrganizationById(user.getOrganizationId()));
			}
		}
		return pager;
	}

	public List<TUser> getUsers(int organizationId) {
		TUser tUser = new TUser();
		tUser.setOrganizationId(organizationId);
		return tUserDao.queryTUsers(tUser);
	}

	public List<TUser> getUsers(int organizationId, int start, int limit) {
		TUser tUser = new TUser();
		tUser.setOrganizationId(organizationId);
		return tUserDao.queryTUsers(tUser);
	}

	public TRoleDao gettRoleDao() {
		return tRoleDao;
	}

	public void settRoleDao(TRoleDao tRoleDao) {
		this.tRoleDao = tRoleDao;
	}

	public Pager getUserPager(
			int organizationId, int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public void authorizeRole(int userId, int[] roleIds) {
		// TODO Auto-generated method stub
		
	}

	public void removeAuthorizeRole(int userId, int[] roleIds) {
		// TODO Auto-generated method stub
		
	}

	public void authorizeRoleByString(int userId, String roleIds) {
		// TODO Auto-generated method stub
		
	}

	public void removeAuthorizeByString(int userId, String roleIds) {
		// TODO Auto-generated method stub
		
	}

	public Function[] getUserAllFunctions(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Function[] getUserMenuFunctions(int userId) {
		// TODO Auto-generated method stub
		//select t_function.* from t_function,t_user,t_role_t_function,t_user_t_role where t_user.USER_ID=? and t_user.USER_ID=t_user_t_role.USER_ID and t_user_t_role.ROLE_ID=t_role_t_function.ROLE_ID and t_role_t_function.FUNCTION_ID=t_function.FUNCTION_ID and t_function.function_type=?],参数:[1,0]
		
		return null;
	}

	public TreeNode[] getUserMenuFunctionTree(int userId) {
		return toTreeByList(tUserDao.queryUserMenuFunction(userId, 0), tFunctionDao.queryTFunctions(null));
	}

	public Function[] getUserMenuSubFunctions(int userId, int menuFunctionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TRole> getUserRoles(String userId) {
		return tUserDao.queryTRoleByUserId(userId);
	}

	public TreeNode[] getUserFunctionTree(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void authorizeRoleInsteadByString(int userId, String roleIds,String removeRoleIds) {
		
		if(removeRoleIds!=null && removeRoleIds.trim().length()>0){
			Integer[] removeRoleIdArray = toArray(removeRoleIds);
			tUserTRoleDao.deleteTUserTRole(userId,removeRoleIdArray);
		}
		
		if(roleIds!=null && roleIds.trim().length()>0){
			Integer[] authorizeRoleIdArray = toArray(roleIds);
			for(int roleId:authorizeRoleIdArray){
				TUserTRole tUserTRole = new TUserTRole();
				tUserTRole.setRoleId(roleId);
				tUserTRole.setUserId(userId);
				tUserTRoleDao.insertTUserTRole(tUserTRole);
			}
		}
		
	}

	public void authorizeRolesToUsers(String userIds, String roleIds) {
		// TODO Auto-generated method stub
		
	}

	public void removeAuthorizeRolesFromUsers(String userIds, String roleIds) {
		// TODO Auto-generated method stub
		
	}

	public void updateAuthorizeRolesUsers(int roleId,
			String authorizeUserIds, String removeUserIds) {
		if(removeUserIds!=null && removeUserIds.trim().length()>0){
			Integer[] removeUserIdArray = toArray(removeUserIds);
			tUserTRoleDao.deleteTUserTRoleByRoleId(roleId, removeUserIdArray);
		}
		
		if(authorizeUserIds!=null && authorizeUserIds.trim().length()>0){
			Integer[] authorizeUserIdArray = toArray(authorizeUserIds);
			for(int userId : authorizeUserIdArray){
				TUserTRole tUserTRole = new TUserTRole();
				tUserTRole.setRoleId(roleId);
				tUserTRole.setUserId(userId);
				tUserTRoleDao.insertTUserTRole(tUserTRole);
			}
		}
	}

	public TUser loginValidate(String loginname, String password) {
		return loginValidateByEncodedPwd(loginname, encryptionProcessor.encode(password));
	}

	public TUser loginValidateByEncodedPwd(String loginname, String password) {
		TUser tUser = new TUser();
		tUser.setLoginName(loginname);
		tUser.setUserPassword(password);
		List<TUser> tUserList = tUserDao.queryTUsers(tUser);
		return (tUserList!=null && tUserList.size()>0)?tUserList.get(0):null;
	}

	public boolean isUserExist(TUser tUser) {
		// TODO Auto-generated method stub
		return false;
	}

	public TUserTRoleDao gettUserTRoleDao() {
		return tUserTRoleDao;
	}

	public void settUserTRoleDao(TUserTRoleDao tUserTRoleDao) {
		this.tUserTRoleDao = tUserTRoleDao;
	}

	public List<TUser> getUsersByRoleAndOrganizationId(int roleId, int organizationId) {
		return tUserTRoleDao.queryTUserTRole(roleId,organizationId);
	}
	
	public TFunction checkSubFunctionByUserId(int userId, int functionId) {
		return tUserDao.queryTFunctionByUser(userId,functionId,0);
	}
	
	public TFunction checkSubFunction(int userId,int functionCode) {
		return tUserDao.queryTFunction(userId,functionCode,0);
	}

	public List<TRole> getUnassignedUserRoles(String userId) {
		// TODO Auto-generated method stub
		return tUserDao.queryUnassignTRoleIds(userId);
	}

	public void addUserRoles(int userId, Integer[] roleId) {
		for(int rId:roleId){
			TUserTRole tUserTRole = new TUserTRole();
			tUserTRole.setUserId(userId);
			tUserTRole.setRoleId(rId);
			tUserTRoleDao.insertTUserTRole(tUserTRole);
		}
	}

	public void removeUserRoles(int userId, Integer[] roleId) {
		tUserTRoleDao.deleteTRoleTUser(userId, roleId);
	}
	
	public void addUserRoless(int userId, String[] roleId) {
		
	}
}
