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

import static org.tinygroup.bizframedslenterprise.impl.dao.TSysparamTable.*;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.and;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TSysparam;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TSysparamDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TSysparam insertTSysparam(TSysparam tSysparam) {
		Insert insert = insertInto(T_SYSPARAM).values(
//				T_SYSPARAM.SYSPARAM_ID.value(tSysparam.getSysparamId()),
				T_SYSPARAM.SYSPARAM_TYPE.value(tSysparam.getSysparamType()),
				T_SYSPARAM.SYSPARAM_NAME.value(tSysparam.getSysparamName()),
				T_SYSPARAM.SYSPARAM_VALUE.value(tSysparam.getSysparamValue()));
		dslSession.execute(insert);
		return tSysparam;
	}

	public void updateTSysparam(TSysparam tSysparam) {
		Update update = update(T_SYSPARAM).set(
				T_SYSPARAM.SYSPARAM_TYPE.value(tSysparam.getSysparamType()),
				T_SYSPARAM.SYSPARAM_NAME.value(tSysparam.getSysparamName()),
				T_SYSPARAM.SYSPARAM_VALUE.value(tSysparam.getSysparamValue())).where(
				T_SYSPARAM.SYSPARAM_ID.eq(tSysparam.getSysparamId()));
		dslSession.execute(update);
	}

	public void deleteTSysparam(String id) {
		Delete delete = delete(T_SYSPARAM).where(T_SYSPARAM.SYSPARAM_ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTSysparamByIds(Object... ids) {
		Delete delete = delete(T_SYSPARAM).where(T_SYSPARAM.SYSPARAM_ID.in(ids));
		dslSession.execute(delete);
	}

	public TSysparam getTSysparamById(String id) {
		Select select = selectFrom(T_SYSPARAM).where(T_SYSPARAM.SYSPARAM_ID.eq(id));
		return dslSession.fetchOneResult(select, TSysparam.class);
	}

	public List<TSysparam> queryTSysparams(TSysparam tSysparam) {
		Select select = selectFrom(T_SYSPARAM).where(and(
				T_SYSPARAM.SYSPARAM_TYPE.eq(tSysparam.getSysparamType()),
				T_SYSPARAM.SYSPARAM_NAME.like(tSysparam.getSysparamName()),
				T_SYSPARAM.SYSPARAM_VALUE.like(tSysparam.getSysparamValue())));
		return dslSession.fetchList(select, TSysparam.class);
	}
	
	public Pager queryTSysparamsPager(TSysparam tSysparam,int start,int limit) {
		if(tSysparam==null){
			tSysparam = new TSysparam();
		}
		Condition condition = and(
				T_SYSPARAM.SYSPARAM_TYPE.eq(tSysparam.getSysparamType()),
				T_SYSPARAM.SYSPARAM_NAME.like(tSysparam.getSysparamName()),
				T_SYSPARAM.SYSPARAM_VALUE.like(tSysparam.getSysparamValue()));
		
		MysqlSelect select = selectFrom(T_SYSPARAM).where(condition).limit(start, limit);
		List<TSysparam> tSysparamList =  dslSession.fetchList(select, TSysparam.class);
		
		Select countSelect = select(T_SYSPARAM.SYSPARAM_ID.count()).from(T_SYSPARAM).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tSysparamList);
		return pager;
	}

}
