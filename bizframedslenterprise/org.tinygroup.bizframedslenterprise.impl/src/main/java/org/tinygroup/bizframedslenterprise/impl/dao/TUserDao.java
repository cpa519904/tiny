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

import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTable.*;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.base.FragmentSql.fragmentFrom;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;
import static org.tinygroup.tinysqldsl.formitem.SubSelect.subSelect;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
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
import static org.tinygroup.tinysqldsl.base.FragmentSql.fragmentCondition;

import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTRoleTable.T_USER_T_ROLE;
import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTable.T_ROLE;
import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTFunctionTable.T_ROLE_T_FUNCTION;
import static org.tinygroup.bizframedslenterprise.impl.dao.TFunctionTable.T_FUNCTION;
import static org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationTable.T_ORGANIZATION;


public class TUserDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TUser insertTUser(TUser tUser) {
		Insert insert = insertInto(T_USER).values(
//				T_USER.USER_ID.value(tUser.getUserId()),
				T_USER.ORGANIZATION_ID.value(tUser.getOrganizationId()),
				T_USER.USER_NAME.value(tUser.getUserName()),
				T_USER.USER_PASSWORD.value(tUser.getUserPassword()),
				T_USER.USER_TYPE.value(tUser.getUserType()),
				T_USER.USER_STATUS.value(tUser.getUserStatus()),
				T_USER.SORT_NUM.value(tUser.getSortNum()),
				T_USER.LOGIN_NAME.value(tUser.getLoginName()),
				T_USER.IS_DEFAULT.value(tUser.getIsDefault()));
		dslSession.execute(insert);
		return tUser;
	}

	public void updateTUser(TUser tUser) {
		Update update = update(T_USER).set(
				T_USER.ORGANIZATION_ID.value(tUser.getOrganizationId()),
				T_USER.USER_NAME.value(tUser.getUserName()),
				T_USER.USER_PASSWORD.value(tUser.getUserPassword()),
				T_USER.USER_TYPE.value(tUser.getUserType()),
				T_USER.USER_STATUS.value(tUser.getUserStatus()),
				T_USER.SORT_NUM.value(tUser.getSortNum()),
				T_USER.LOGIN_NAME.value(tUser.getLoginName()),
				T_USER.IS_DEFAULT.value(tUser.getIsDefault())).where(
				T_USER.USER_ID.eq(tUser.getUserId()));
		dslSession.execute(update);
	}

	public void deleteTUser(int id) {
		Delete delete = delete(T_USER).where(T_USER.USER_ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTUserByIds(Object... ids) {
		Delete delete = delete(T_USER).where(T_USER.USER_ID.in(ids));
		dslSession.execute(delete);
	}

	public TUser getTUserById(int id) {
		Select select = selectFrom(T_USER).where(T_USER.USER_ID.eq(id));
		return dslSession.fetchOneResult(select, TUser.class);
	}

	public List<TUser> queryTUsers(TUser tUser) {
		if(tUser==null){
			tUser = new TUser();
		}
		Select select = select(T_USER.ALL,T_ORGANIZATION.ORGANIZATION_NAME).from(T_USER,T_ORGANIZATION).where(
				and(
				T_USER.ORGANIZATION_ID.eq(T_ORGANIZATION.ORGANIZATION_ID),
				T_USER.ORGANIZATION_ID.eq(tUser.getOrganizationId()),
				T_USER.USER_NAME.eq(tUser.getUserName()),
				T_USER.USER_PASSWORD.eq(tUser.getUserPassword()),
				T_USER.USER_TYPE.eq(tUser.getUserType()),
				T_USER.USER_STATUS.eq(tUser.getUserStatus()),
				T_USER.SORT_NUM.eq(tUser.getSortNum()),
				T_USER.LOGIN_NAME.eq(tUser.getLoginName()),
				T_USER.IS_DEFAULT.eq(tUser.getIsDefault())));
		return dslSession.fetchList(select, TUser.class);
	}
	
	public Pager queryTUsersPager(TUser tUser,int start,int limit) {
		if(tUser==null){
			tUser = new TUser();
		}
		Condition condition =
				and(
				T_USER.ORGANIZATION_ID.eq(T_ORGANIZATION.ORGANIZATION_ID),
				T_USER.ORGANIZATION_ID.eq(tUser.getOrganizationId()),
				T_USER.USER_NAME.eq(tUser.getUserName()),
				T_USER.USER_PASSWORD.eq(tUser.getUserPassword()),
				T_USER.USER_TYPE.eq(tUser.getUserType()),
				T_USER.USER_STATUS.eq(tUser.getUserStatus()),
				T_USER.SORT_NUM.eq(tUser.getSortNum()),
				T_USER.LOGIN_NAME.eq(tUser.getLoginName()),
				T_USER.IS_DEFAULT.eq(tUser.getIsDefault()));
				MysqlSelect select = select(T_USER.ALL,T_ORGANIZATION.ORGANIZATION_NAME).from(T_USER,T_ORGANIZATION).where(condition).limit(start, limit);
				List<TUser> tUserUList =  dslSession.fetchList(select, TUser.class);
				Select countSelect = select(T_USER.USER_ID.count()).from(T_USER,T_ORGANIZATION).where(condition);
				Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
				Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tUserUList);
				return pager;
	}
	
	/**
	 * 
	 * select * from t_user
	 * @param tUser
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pager queryTUsersCascadePager(TUser tUser,int start,int limit) {
		if(tUser==null){
			tUser = new TUser();
		}
		Condition condition = and(
				T_USER.ORGANIZATION_ID.eq(tUser.getOrganizationId()),
				T_USER.USER_NAME.eq(tUser.getUserName()),
				T_USER.USER_PASSWORD.eq(tUser.getUserPassword()),
				T_USER.USER_TYPE.eq(tUser.getUserType()),
				T_USER.USER_STATUS.eq(tUser.getUserStatus()),
				T_USER.SORT_NUM.eq(tUser.getSortNum()),
				T_USER.LOGIN_NAME.eq(tUser.getLoginName()),
				T_USER.IS_DEFAULT.eq(tUser.getIsDefault()),
				T_USER.ORGANIZATION_ID.eq(T_ORGANIZATION.ORGANIZATION_ID));
		select(T_USER.ALL,T_ORGANIZATION.ORGANIZATION_NAME).from(T_USER,T_ORGANIZATION).where(condition);
				MysqlSelect select = selectFrom(T_USER).where(condition).limit(start, limit);
				List<TUser> tUserUList =  dslSession.fetchList(select, TUser.class);
				
				Select countSelect = select(T_USER.USER_ID.count()).from(T_USER).where(condition);
				Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
				Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tUserUList);
				return pager;
	}

	/**
	 * @param userId
	 * @return
	 */
	public List<TRole> queryTRoleByUserId(String userId){
		Select select = selectFrom(T_USER,T_ROLE,T_USER_T_ROLE)
				.where(and(
						T_USER_T_ROLE.ROLE_ID.eq(T_ROLE.ROLE_ID),
						T_USER.USER_ID.eq(T_USER_T_ROLE.USER_ID),
						T_USER.USER_ID.eq(userId)
						)
				);
		return dslSession.fetchList(select, TRole.class);
	}
	
	public List<TRole> queryUnassignTRoleIds(String userId){
		Select select = select(T_ROLE.ALL).from(T_ROLE).where(fragmentCondition("t_role.role_id not in(SELECT t_role.role_id FROM t_user,t_role,t_user_t_role WHERE t_user_t_role.role_id = t_role.role_id and t_user.user_id = t_user_t_role.user_id and t_user.user_id = ?)",userId));
		return dslSession.fetchList(select, TRole.class);
	}
	
	public List<TFunction> queryUserMenuFunction(int userId,int functionType){
		/**
		 * select t_function.* from t_function,t_user,t_role_t_function,t_user_t_role 
		where t_user.USER_ID=? 
		and t_user.USER_ID=t_user_t_role.USER_ID
		and t_user_t_role.ROLE_ID=t_role_t_function.ROLE_ID and 
		t_role_t_function.FUNCTION_ID=t_function.FUNCTION_ID and
		 t_function.function_type=?],参数:[1,0]
		**/
		Select select = select(T_FUNCTION.ALL).from(T_FUNCTION,T_USER,T_ROLE_T_FUNCTION,T_USER_T_ROLE).where(
				and(
				T_USER.USER_ID.eq(userId),
				T_USER.USER_ID.eq(T_USER_T_ROLE.USER_ID),
				T_USER_T_ROLE.ROLE_ID.eq(T_ROLE_T_FUNCTION.ROLE_ID),
				T_ROLE_T_FUNCTION.FUNCTION_ID.eq(T_FUNCTION.FUNCTION_ID),
				T_FUNCTION.FUNCTION_TYPE.eq(functionType)));
		return dslSession.fetchList(select, TFunction.class);
	}
	
	//	String sql = "select t_function.* from t_function,t_role_t_function,t_user_t_role where t_user_t_role.USER_ID=? and t_role_t_function.ROLE_ID=t_user_t_role.ROLE_ID and t_function.function_id=t_role_t_function.function_id and t_function.function_id=? and t_function.function_type=?";
	public TFunction queryTFunctionByUser(int userId,int functionId,int functionType){
		Select select = select(T_FUNCTION.ALL).from(T_FUNCTION,T_ROLE_T_FUNCTION,T_USER_T_ROLE).where(
				and(T_ROLE_T_FUNCTION.ROLE_ID.eq(T_USER_T_ROLE.ROLE_ID),
					T_FUNCTION.FUNCTION_ID.eq(T_ROLE_T_FUNCTION.FUNCTION_ID),
					T_USER_T_ROLE.USER_ID.eq(userId),
					T_FUNCTION.FUNCTION_ID.eq(functionId),
					T_FUNCTION.FUNCTION_TYPE.eq(functionType)));
		List<TFunction> tFunctionlist = dslSession.fetchList(select, TFunction.class);
		return (tFunctionlist!=null && tFunctionlist.size()>0)?tFunctionlist.get(0):null;
	}
	
	public TFunction queryTFunction(int userId,int functionCode,int functionType){
		Select select = select(T_FUNCTION.ALL).from(T_FUNCTION,T_ROLE_T_FUNCTION,T_USER_T_ROLE).where(
				and(T_ROLE_T_FUNCTION.ROLE_ID.eq(T_USER_T_ROLE.ROLE_ID),
					T_FUNCTION.FUNCTION_ID.eq(T_ROLE_T_FUNCTION.FUNCTION_ID),
					T_USER_T_ROLE.USER_ID.eq(userId),
					T_FUNCTION.FUNCTION_CODE.eq(functionCode),
					T_FUNCTION.FUNCTION_TYPE.eq(functionType)));
		List<TFunction> tFunctionlist = dslSession.fetchList(select, TFunction.class);
		return (tFunctionlist!=null && tFunctionlist.size()>0)?tFunctionlist.get(0):null;
	}
	
}
