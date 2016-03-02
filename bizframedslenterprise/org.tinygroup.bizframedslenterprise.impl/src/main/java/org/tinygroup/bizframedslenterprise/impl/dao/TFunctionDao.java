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

import static org.tinygroup.bizframedslenterprise.impl.dao.TFunctionTable.*;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TFunctionDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TFunction insertTFunction(TFunction tFunction) {
		Insert insert = insertInto(T_FUNCTION).values(
				// T_FUNCTION.FUNCTION_ID.value(tFunction.getFunctionId()),
				T_FUNCTION.PARENT_ID.value(tFunction.getParentId()),
				T_FUNCTION.FUNCTION_NAME.value(tFunction.getFunctionName()),
				T_FUNCTION.FUNCTION_TYPE.value(tFunction.getFunctionType()),
				T_FUNCTION.FUNCTION_URL.value(tFunction.getFunctionUrl()),
				T_FUNCTION.SORT_NUM.value(tFunction.getSortNum()),
				T_FUNCTION.FUNCTION_CODE.value(tFunction.getFunctionCode()));
		dslSession.execute(insert);
		return tFunction;
	}

	public void updateTFunction(TFunction tFunction) {
		Update update = update(T_FUNCTION).set(
				T_FUNCTION.PARENT_ID.value(tFunction.getParentId()),
				T_FUNCTION.FUNCTION_NAME.value(tFunction.getFunctionName()),
				T_FUNCTION.FUNCTION_TYPE.value(tFunction.getFunctionType()),
				T_FUNCTION.FUNCTION_URL.value(tFunction.getFunctionUrl()),
				T_FUNCTION.SORT_NUM.value(tFunction.getSortNum()),
				T_FUNCTION.FUNCTION_CODE.value(tFunction.getFunctionCode()))
				.where(T_FUNCTION.FUNCTION_ID.eq(tFunction.getFunctionId()));
		dslSession.execute(update);
	}

	public void deleteTFunction(String id) {
		Delete delete = delete(T_FUNCTION).where(T_FUNCTION.FUNCTION_ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTFunctionByIds(Object... ids) {
		Delete delete = delete(T_FUNCTION).where(T_FUNCTION.FUNCTION_ID.in(ids));
		dslSession.execute(delete);
	}

	public TFunction getTFunctionById(String id) {
		Select select = selectFrom(T_FUNCTION).where(
				T_FUNCTION.FUNCTION_ID.eq(id));
		return dslSession.fetchOneResult(select, TFunction.class);
	}

	public List<TFunction> queryTFunctions(TFunction tFunction) {
		if (tFunction == null) {
			tFunction = new TFunction();
		}
		;
		return dslSession.fetchList(
				selectFrom(T_FUNCTION).where(
						and(T_FUNCTION.PARENT_ID.eq(tFunction.getParentId()),
								T_FUNCTION.FUNCTION_NAME.like(tFunction
										.getFunctionName()),
								T_FUNCTION.FUNCTION_TYPE.eq(tFunction
										.getFunctionType()),
								T_FUNCTION.FUNCTION_URL.like(tFunction
										.getFunctionUrl()), T_FUNCTION.SORT_NUM
										.eq(tFunction.getSortNum()),
								T_FUNCTION.FUNCTION_CODE.like(tFunction
										.getFunctionCode()))), TFunction.class);
	}
	
	public int queryTFunctionCount(TFunction tFunction) {
		if (tFunction == null) {
			return 0;
		}
		;
		return dslSession.fetchOneResult(
				select(T_FUNCTION.FUNCTION_ID.count()).from(
						T_FUNCTION).where(and(T_FUNCTION.FUNCTION_CODE.eq(tFunction
										.getFunctionCode()),
										T_FUNCTION.FUNCTION_ID.eq(tFunction.getFunctionId()
										)))
				, Integer.class);
	}

	public Pager queryTFunctions(TFunction tFunction, int start, int limit) {
		Condition condition = null;
		if (tFunction == null) {
			tFunction = new TFunction();
		}
		condition = and(T_FUNCTION.PARENT_ID.eq(tFunction.getParentId()),
				T_FUNCTION.FUNCTION_NAME.like(tFunction.getFunctionName()),
				T_FUNCTION.FUNCTION_TYPE.eq(tFunction.getFunctionType()),
				T_FUNCTION.FUNCTION_URL.like(tFunction.getFunctionUrl()),
				T_FUNCTION.SORT_NUM.eq(tFunction.getSortNum()),
				T_FUNCTION.FUNCTION_CODE.like(tFunction.getFunctionCode()));
		return queryTFunctionsByCondition(condition,start,limit);
	}
	
	public Pager queryRootTFunctions(TFunction tFunction, int start, int limit) {
		Condition condition = null;
		if (tFunction == null) {
			tFunction = new TFunction();
		}
		condition = and(T_FUNCTION.PARENT_ID.isNull(),
				T_FUNCTION.FUNCTION_NAME.like(tFunction.getFunctionName()),
				T_FUNCTION.FUNCTION_TYPE.eq(tFunction.getFunctionType()),
				T_FUNCTION.FUNCTION_URL.like(tFunction.getFunctionUrl()),
				T_FUNCTION.SORT_NUM.eq(tFunction.getSortNum()),
				T_FUNCTION.FUNCTION_CODE.like(tFunction.getFunctionCode()));
		return queryTFunctionsByCondition(condition,start,limit);
	}
	
	private Pager queryTFunctionsByCondition(Condition condition,int start,int limit){
		MysqlSelect select = selectFrom(T_FUNCTION).where(condition).limit(
				start, limit);
		List<TFunction> tFunctionList = dslSession.fetchList(select,
				TFunction.class);

		Select countSelect = select(T_FUNCTION.FUNCTION_ID.count()).from(
				T_FUNCTION).where(condition);
		Integer countObj = dslSession
				.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj), start, limit,
				tFunctionList);
		return pager;
	}

}
