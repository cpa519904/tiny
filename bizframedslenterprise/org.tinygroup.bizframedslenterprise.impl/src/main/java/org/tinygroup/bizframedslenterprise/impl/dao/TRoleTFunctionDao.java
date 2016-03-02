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

import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTFunctionTable.*;
import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTable.*;
import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTRoleTable.T_USER_T_ROLE;
import static org.tinygroup.bizframedslenterprise.impl.dao.TUserTable.T_USER;
import static org.tinygroup.bizframedslenterprise.impl.dao.TFunctionTable.*;
import static org.tinygroup.tinysqldsl.base.FragmentSql.fragmentCondition;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.and;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRoleTFunction;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;

public class TRoleTFunctionDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TRoleTFunction insertTRoleTFunction(TRoleTFunction tRoleTFunction) {
		Insert insert = insertInto(T_ROLE_T_FUNCTION).values(
//				T_ROLE_T_FUNCTION.ID.value(tRoleTFunction.getId()),
				T_ROLE_T_FUNCTION.FUNCTION_ID.value(tRoleTFunction.getFunctionId()),
				T_ROLE_T_FUNCTION.ROLE_ID.value(tRoleTFunction.getRoleId()));
		dslSession.execute(insert);
		return tRoleTFunction;
	}

	public void updateTRoleTFunction(TRoleTFunction tRoleTFunction) {
		Update update = update(T_ROLE_T_FUNCTION).set(
				T_ROLE_T_FUNCTION.FUNCTION_ID.value(tRoleTFunction.getFunctionId()),
				T_ROLE_T_FUNCTION.ROLE_ID.value(tRoleTFunction.getRoleId())).where(
				T_ROLE_T_FUNCTION.ID.eq(tRoleTFunction.getId()));
		dslSession.execute(update);
	}

	public void deleteTRoleTFunction(String id) {
		Delete delete = delete(T_ROLE_T_FUNCTION).where(T_ROLE_T_FUNCTION.ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTRoleTFunctions(int roleId,Object... functionIds) {
		Delete delete = delete(T_ROLE_T_FUNCTION).where(
			and(
				T_ROLE_T_FUNCTION.ROLE_ID.eq(roleId),
				T_ROLE_T_FUNCTION.FUNCTION_ID.in(functionIds)));
		dslSession.execute(delete);
	}
	
	public void deleteTFunctionTRoles(int functionId,Object... roleIds) {
		Delete delete = delete(T_ROLE_T_FUNCTION).where(
			and(
				T_ROLE_T_FUNCTION.FUNCTION_ID.eq(functionId),
				T_ROLE_T_FUNCTION.ROLE_ID.in(roleIds)));
		dslSession.execute(delete);
	}

	public TRoleTFunction getTRoleTFunctionById(String id) {
		Select select = selectFrom(T_ROLE_T_FUNCTION).where(T_ROLE_T_FUNCTION.ID.eq(id));
		return dslSession.fetchOneResult(select, TRoleTFunction.class);
	}

	public List<TRoleTFunction> queryTRoleTFunctions(TRoleTFunction tRoleTFunction) {
		if(tRoleTFunction == null){
			tRoleTFunction = new TRoleTFunction();
		}
		Select select = selectFrom(T_ROLE_T_FUNCTION).where(
				and(
				T_ROLE_T_FUNCTION.FUNCTION_ID.eq(tRoleTFunction.getFunctionId()),
				T_ROLE_T_FUNCTION.ROLE_ID.eq(tRoleTFunction.getRoleId())));
		return dslSession.fetchList(select, TRoleTFunction.class);
	}
	
	public List<TFunction> queryRoleFunctions(int roleId){
		Select select = select(T_FUNCTION.ALL).from(T_FUNCTION,T_ROLE_T_FUNCTION,T_ROLE).where(
				and(
					T_ROLE_T_FUNCTION.FUNCTION_ID.eq(T_FUNCTION.FUNCTION_ID),
					T_ROLE_T_FUNCTION.ROLE_ID.eq(T_ROLE.ROLE_ID),
					T_ROLE.ROLE_ID.eq(roleId)
				)
		);
		return dslSession.fetchList(select, TFunction.class);
	}

	public List<TRole> queryFunctionRoles(int functionId){
		Select select = select(T_ROLE.ALL).from(T_FUNCTION,T_ROLE_T_FUNCTION,T_ROLE).where(
				and(
					T_ROLE_T_FUNCTION.FUNCTION_ID.eq(T_FUNCTION.FUNCTION_ID),
					T_ROLE_T_FUNCTION.ROLE_ID.eq(T_ROLE.ROLE_ID),
					T_FUNCTION.FUNCTION_ID.eq(functionId)
				)
		);
		return dslSession.fetchList(select, TRole.class);
	}

	public List<TRole> queryTRoleByFunctionId(int functionId) {
		Select select = select(T_ROLE.ALL).from(T_FUNCTION,T_ROLE,T_ROLE_T_FUNCTION)
				.where(and(
						T_ROLE_T_FUNCTION.ROLE_ID.eq(T_ROLE.ROLE_ID),
						T_ROLE_T_FUNCTION.FUNCTION_ID.eq(T_FUNCTION.FUNCTION_ID),
						T_FUNCTION.FUNCTION_ID.eq(functionId)
						)
				);
		return dslSession.fetchList(select, TRole.class);
	}
	
	public List<TRole> queryUnassignTRoles(int functionId){
		Select select = select(T_ROLE.ALL).from(T_ROLE).where(fragmentCondition("t_role.role_id not in(SELECT t_role.role_id FROM t_role,t_function,t_role_t_function WHERE t_role_t_function.role_id = t_role.role_id and t_function.function_id = t_role_t_function.function_id and t_function.function_id = ?)",functionId));
		return dslSession.fetchList(select, TRole.class);
	}
}
