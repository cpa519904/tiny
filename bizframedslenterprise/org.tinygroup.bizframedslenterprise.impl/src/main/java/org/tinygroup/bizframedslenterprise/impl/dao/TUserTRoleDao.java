/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.bizframedslenterprise.impl.dao;

import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTable.T_ROLE;
import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTRoleTable.*;
import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTable.T_USER;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUserTRole;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;

public class TUserTRoleDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TUserTRole insertTUserTRole(TUserTRole tUserTRole) {
		Insert insert = insertInto(T_USER_T_ROLE).values(
//				T_USER_T_ROLE.ID.value(tUserTRole.getId()),
				T_USER_T_ROLE.ROLE_ID.value(tUserTRole.getRoleId()),
				T_USER_T_ROLE.USER_ID.value(tUserTRole.getUserId()));
		dslSession.execute(insert);
		return tUserTRole;
	}

	public void updateTUserTRole(TUserTRole tUserTRole) {
		Update update = update(T_USER_T_ROLE).set(
				T_USER_T_ROLE.ROLE_ID.value(tUserTRole.getRoleId()),
				T_USER_T_ROLE.USER_ID.value(tUserTRole.getUserId())).where(
				T_USER_T_ROLE.ID.eq(tUserTRole.getId()));
		dslSession.execute(update);
	}

	public void deleteTUserTRole(String id) {
		Delete delete = delete(T_USER_T_ROLE).where(T_USER_T_ROLE.ID.eq(id));
		dslSession.execute(delete);
	}
	
	/**
	 * 批量删除某用户的id
	 * @param userId
	 * @param roleIds
	 */
	public void deleteTUserTRole(int userId,Integer... roleIds) {
		
		Delete delete = delete(T_USER_T_ROLE).where(and(
				T_USER_T_ROLE.ROLE_ID.in(roleIds),
				T_USER_T_ROLE.USER_ID.eq(userId)));
		dslSession.execute(delete);
	}
	
	/**
	 * 批量删除某用户的id
	 * @param roleId
	 * @param userIds
	 */
	public void deleteTRoleTUser(int roleId,Integer... userIds) {
		
		Delete delete = delete(T_USER_T_ROLE).where(and(
				T_USER_T_ROLE.USER_ID.in(userIds),
				T_USER_T_ROLE.ROLE_ID.eq(roleId)));
		dslSession.execute(delete);
	}
	
	/**
	 * 删除某角色下的一些用户
	 * @param roleId
	 * @param userIds
	 */
	public void deleteTUserTRoleByRoleId(int roleId,Object... userIds) {
		Delete delete = delete(T_USER_T_ROLE).where(and(
				T_USER_T_ROLE.USER_ID.in(userIds),
				T_USER_T_ROLE.ROLE_ID.eq(roleId)));
		dslSession.execute(delete);
	}

	public TUserTRole getTUserTRoleById(String id) {
		Select select = selectFrom(T_USER_T_ROLE).where(T_USER_T_ROLE.ID.eq(id));
		return dslSession.fetchOneResult(select, TUserTRole.class);
	}

	public List<TUserTRole> queryTUserTRoles(TUserTRole tUserTRole) {
		Select select = selectFrom(T_USER_T_ROLE).where(
				and(
				T_USER_T_ROLE.ROLE_ID.eq(tUserTRole.getRoleId()),
				T_USER_T_ROLE.USER_ID.eq(tUserTRole.getUserId())));
		return dslSession.fetchList(select, TUserTRole.class);
	}

	/**
	 * SELECT
			t_user.*
		FROM
			t_user,
			t_role,
			t_user_t_role
		WHERE
			t_user.USER_ID = t_user_t_role.USER_ID
		AND t_user_t_role.ROLE_ID = t_role.ROLE_ID
		AND t_role.ROLE_ID =?
		AND t_user.organization_id =?
	 */
	public List<TUser> queryTUserTRole(int roleId,int organizationId){
		Select select = select(T_USER.ALL).from(T_USER,T_ROLE,T_USER_T_ROLE).where(
				and(
					T_USER.USER_ID.eq(T_USER_T_ROLE.USER_ID),
					T_USER_T_ROLE.ROLE_ID.eq(T_ROLE.ROLE_ID),
					T_ROLE.ROLE_ID.eq(roleId),
					T_USER.ORGANIZATION_ID.eq(organizationId)
				)
		);
		return dslSession.fetchList(select, TUser.class);
	}
}
