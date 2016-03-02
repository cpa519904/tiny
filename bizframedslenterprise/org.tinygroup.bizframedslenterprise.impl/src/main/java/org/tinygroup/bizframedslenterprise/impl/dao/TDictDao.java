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

import static org.tinygroup.bizframedslenterprise.impl.dao.TDictTable.*;
import static org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationTable.T_ORGANIZATION;
import static org.tinygroup.bizframedslenterprise.impl.dao.TRoleTable.T_ROLE;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TDict;
import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TDictDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TDict insertTDict(TDict tDict) {
		Insert insert = insertInto(T_DICT).values(
//				T_DICT.DICT_ID.value(tDict.getDictId()),
				T_DICT.DICT_NAME.value(tDict.getDictName()),
				T_DICT.DICT_VALUE.value(tDict.getDictValue()),
				T_DICT.DICT_TYPE.value(tDict.getDictType()),
				T_DICT.SORT_NUM.value(tDict.getSortNum()));
		dslSession.execute(insert);
		return tDict;
	}

	public void updateTDict(TDict tDict) {
		Update update = update(T_DICT).set(
				T_DICT.DICT_NAME.value(tDict.getDictName()),
				T_DICT.DICT_VALUE.value(tDict.getDictValue()),
				T_DICT.DICT_TYPE.value(tDict.getDictType()),
				T_DICT.SORT_NUM.value(tDict.getSortNum())).where(
				T_DICT.DICT_ID.eq(tDict.getDictId()));
		dslSession.execute(update);
	}

	public void deleteTDict(String id) {
		Delete delete = delete(T_DICT).where(T_DICT.DICT_ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTDictByIds(Object... ids) {
		Delete delete = delete(T_DICT).where(T_DICT.DICT_ID.in(ids));
		dslSession.execute(delete);
	}

	public TDict getTDictById(String id) {
		Select select = selectFrom(T_DICT).where(T_DICT.DICT_ID.eq(id));
		return dslSession.fetchOneResult(select, TDict.class);
	}

	public List<TDict> queryTDicts(TDict tDict) {
		if(tDict==null){
			tDict = new TDict();
		}
		Select select = selectFrom(T_DICT).where(
				and(
				T_DICT.DICT_NAME.like(tDict.getDictName()),
				T_DICT.DICT_VALUE.like(tDict.getDictValue()),
				T_DICT.DICT_TYPE.eq(tDict.getDictType()),
				T_DICT.SORT_NUM.eq(tDict.getSortNum())));
		return dslSession.fetchList(select, TDict.class);
	}
	
	public Pager queryTDictsPager(TDict tDict,int start,int limit) {
		if(tDict==null){
			tDict = new TDict();
		}
		Condition condition = 
				and(
				T_DICT.DICT_NAME.like(tDict.getDictName()),
				T_DICT.DICT_VALUE.like(tDict.getDictValue()),
				T_DICT.DICT_TYPE.eq(tDict.getDictType()),
				T_DICT.SORT_NUM.eq(tDict.getSortNum()));
		MysqlSelect select = selectFrom(T_DICT).where(condition).limit(start, limit);
		List<TDict> tDictList =  dslSession.fetchList(select, TDict.class);
		
		Select countSelect = select(T_DICT.DICT_ID.count()).from(T_DICT).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tDictList);
		return pager;
	}

}
