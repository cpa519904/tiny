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

import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTable.*;
import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTRoleTable.T_USER_T_ROLE;
import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTable.T_USER;
import static org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationTable.T_ORGANIZATION;
import static org.tinygroup.tinysqldsl.base.FragmentSql.fragmentCondition;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.and;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TRoleDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TRole insertTRole(TRole tRole) {
		Insert insert = insertInto(T_ROLE).values(
//				T_ROLE.ROLE_ID.value(tRole.getRoleId()),
				T_ROLE.ROLE_NAME.value(tRole.getRoleName()),
				T_ROLE.ROLE_TYPE.value(tRole.getRoleType()),
				T_ROLE.SORT_NUM.value(tRole.getSortNum()),
				T_ROLE.ROLE_CODE.value(tRole.getRoleCode()),
				T_ROLE.IS_DEFAULT.value(tRole.getIsDefault()));
		dslSession.execute(insert);
		return tRole;
	}

	public void updateTRole(TRole tRole) {
		Update update = update(T_ROLE).set(
				T_ROLE.ROLE_NAME.value(tRole.getRoleName()),
				T_ROLE.ROLE_TYPE.value(tRole.getRoleType()),
				T_ROLE.SORT_NUM.value(tRole.getSortNum()),
				T_ROLE.ROLE_CODE.value(tRole.getRoleCode()),
				T_ROLE.IS_DEFAULT.value(tRole.getIsDefault())).where(
				T_ROLE.ROLE_ID.eq(tRole.getRoleId()));
		dslSession.execute(update);
	}

	public void deleteTRole(String id) {
		Delete delete = delete(T_ROLE).where(T_ROLE.ROLE_ID.eq(id));
		dslSession.execute(delete);
	}

	public TRole getTRoleById(String id) {
		Select select = selectFrom(T_ROLE).where(T_ROLE.ROLE_ID.eq(id));
		return dslSession.fetchOneResult(select, TRole.class);
	}

	public List<TRole> queryTRoles(TRole tRole) {
		Select select = selectFrom(T_ROLE);
		if(tRole!=null){
			select.where(
				and(
				T_ROLE.ROLE_NAME.like(tRole.getRoleName()),
				T_ROLE.ROLE_TYPE.eq(tRole.getRoleType()),
				T_ROLE.SORT_NUM.eq(tRole.getSortNum()),
				T_ROLE.ROLE_CODE.like(tRole.getRoleCode()),
				T_ROLE.IS_DEFAULT.eq(tRole.getIsDefault())));
		}
		return dslSession.fetchList(select, TRole.class);
	}
	
	public Pager queryTRoles(TRole tRole,int start,int limit) {
		Condition condition = and(	T_ROLE.ROLE_NAME.eq(tRole.getRoleName()),
				T_ROLE.ROLE_TYPE.eq(tRole.getRoleType()),
				T_ROLE.SORT_NUM.eq(tRole.getSortNum()),
				T_ROLE.ROLE_CODE.like(tRole.getRoleCode()),
				T_ROLE.IS_DEFAULT.eq(tRole.getIsDefault()));
		Select select = selectFrom(T_ROLE).where(condition);
		Select countSelect = select(T_ROLE.ROLE_ID.count()).where(condition);
		List<Integer> counts = dslSession.fetchList(countSelect, Integer.class);
		List currentList = dslSession.fetchList(select, TRole.class);
		return new Pager(counts.get(0),start,currentList);
	}
	
	public Pager queryTRolesPager(TRole tRole,int start,int limit) {
		if(tRole==null){
			tRole = new TRole();
		}
		Condition condition = and(	T_ROLE.ROLE_NAME.like(tRole.getRoleName()),
					T_ROLE.ROLE_TYPE.eq(tRole.getRoleType()),
					T_ROLE.SORT_NUM.eq(tRole.getSortNum()),
					T_ROLE.ROLE_CODE.like(tRole.getRoleCode()),
					T_ROLE.IS_DEFAULT.eq(tRole.getIsDefault()));

		MysqlSelect select = selectFrom(T_ROLE).where(condition).limit(start, limit);
		List<TRole> tRoleList =  dslSession.fetchList(select, TRole.class);
		
		Select countSelect = select(T_ROLE.ROLE_ID.count()).from(T_ROLE).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tRoleList);
		return pager;
	}
	
	public void deleteTRolesByIds(Object... ids) {
		Delete delete = delete(T_ROLE).where(T_ROLE.ROLE_ID.in(ids));
		dslSession.execute(delete);
	}
	
	public Pager queryRoleTUsers(int start,int limit,Integer roleId,TUser tUser) {
		if(tUser==null){
			tUser  = new TUser();
		}
		if(tUser.getOrganizationId()==0) tUser.setOrganizationId(null);
		Condition condition = and(
						T_USER.ORGANIZATION_ID.eq(T_ORGANIZATION.ORGANIZATION_ID),
						T_USER_T_ROLE.ROLE_ID.eq(T_ROLE.ROLE_ID),
						T_USER.USER_ID.eq(T_USER_T_ROLE.USER_ID),
						T_USER.ORGANIZATION_ID.eq(tUser.getOrganizationId()),
						T_USER.USER_NAME.like(tUser.getUserName()),
						T_USER.LOGIN_NAME.like(tUser.getLoginName()),
						T_USER_T_ROLE.ROLE_ID.eq(roleId)
						);
		Select select =	select(T_USER.ALL,T_ORGANIZATION.ORGANIZATION_NAME).from(T_USER,T_ROLE,T_USER_T_ROLE,T_ORGANIZATION)
				.where(condition).limit(start, limit);
		Select countSelect = select(T_USER.USER_ID.count()).from(T_USER,T_ROLE,T_USER_T_ROLE,T_ORGANIZATION).where(condition);
		List currentList = dslSession.fetchList(select, TUser.class);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,currentList);
		return pager;
	}
	
	public Pager queryUnassignRoleTUsers(int start,int limit,Integer roleId,TUser tUser) {
		if(tUser==null){
			tUser  = new TUser();
		}
		if(tUser.getOrganizationId()==0) tUser.setOrganizationId(null);
		Condition condition = and(
				T_USER.ORGANIZATION_ID.eq(T_ORGANIZATION.ORGANIZATION_ID),
				T_USER.ORGANIZATION_ID.eq(tUser.getOrganizationId()),
				T_USER.USER_NAME.like(tUser.getUserName()),
				T_USER.LOGIN_NAME.like(tUser.getLoginName()),
				fragmentCondition("t_user.user_id not in(SELECT t_user.user_id FROM t_user,t_role,t_user_t_role WHERE t_user_t_role.role_id = t_role.role_id and t_user.user_id = t_user_t_role.user_id and t_role.role_id=?)",roleId)
				);
		Select select = select(T_USER.ALL,T_ORGANIZATION.ORGANIZATION_NAME).from(T_USER,T_ORGANIZATION).where(condition).limit(start, limit);
		Select countSelect = select(T_USER.USER_ID.count()).from(T_USER,T_ORGANIZATION).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		List currentList = dslSession.fetchList(select, TUser.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,currentList);
		return pager;
	}
	
}
