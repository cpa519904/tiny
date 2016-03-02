package org.tinygroup.bizframedslenterprise.inter.action;

import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;

public interface UserAction {
	TUser addUserMethod(TUser tUser);
	
	void updateUserMethod(TUser tUser);
	
	void delteteUserMethod(String userIds);
	
	boolean isLoginNameExit(String loginName);
	
	void addUserRoles(int userId, Integer[] roleId);
	
	void removeUserRoles(int userId, Integer[] roleId);
}