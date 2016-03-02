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

import static org.tinygroup.bizframedslenterprise.impl.dao.TDictitemTable.*;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TDictitem;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TDictitemDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TDictitem insertTDictitem(TDictitem tDictitem) {
		Insert insert = insertInto(T_DICTITEM).values(
//				T_DICTITEM.DICTITEM_ID.value(tDictitem.getDictitemId()),
				T_DICTITEM.DICT_ID.value(tDictitem.getDictId()),
				T_DICTITEM.DICTITEM_NAME.value(tDictitem.getDictitemName()),
				T_DICTITEM.DICTITEM_VALUE.value(tDictitem.getDictitemValue()),
				T_DICTITEM.SORT_NUM.value(tDictitem.getSortNum()));
		dslSession.execute(insert);
		return tDictitem;
	}

	public void updateTDictitem(TDictitem tDictitem) {
		Update update = update(T_DICTITEM).set(
				T_DICTITEM.DICT_ID.value(tDictitem.getDictId()),
				T_DICTITEM.DICTITEM_NAME.value(tDictitem.getDictitemName()),
				T_DICTITEM.DICTITEM_VALUE.value(tDictitem.getDictitemValue()),
				T_DICTITEM.SORT_NUM.value(tDictitem.getSortNum())).where(
				T_DICTITEM.DICTITEM_ID.eq(tDictitem.getDictitemId()));
		dslSession.execute(update);
	}

	public void deleteTDictitem(String id) {
		Delete delete = delete(T_DICTITEM).where(T_DICTITEM.DICTITEM_ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTDictitemByIds(Object... ids) {
		Delete delete = delete(T_DICTITEM).where(T_DICTITEM.DICTITEM_ID.in(ids));
		dslSession.execute(delete);
	}

	public TDictitem getTDictitemById(Integer id) {
		Select select = selectFrom(T_DICTITEM).where(T_DICTITEM.DICTITEM_ID.eq(id));
		return dslSession.fetchOneResult(select, TDictitem.class);
	}

	public List<TDictitem> queryTDictitems(TDictitem tDictitem) {
		if(tDictitem == null){
			tDictitem = new TDictitem();
		}
		Select select = selectFrom(T_DICTITEM).where(
				and(
				T_DICTITEM.DICT_ID.eq(tDictitem.getDictId()),
				T_DICTITEM.DICTITEM_NAME.like(tDictitem.getDictitemName()),
				T_DICTITEM.DICTITEM_VALUE.like(tDictitem.getDictitemValue()),
				T_DICTITEM.SORT_NUM.eq(tDictitem.getSortNum())));
		return dslSession.fetchList(select, TDictitem.class);
	}
	
	public Pager queryTDictitemsPager(TDictitem tDictitem,int start,int limit) {
		if(tDictitem==null){
			tDictitem = new TDictitem();
		}
		Condition condition = 
				and(
				T_DICTITEM.DICT_ID.eq(tDictitem.getDictId()),
				T_DICTITEM.DICTITEM_NAME.like(tDictitem.getDictitemName()),
				T_DICTITEM.DICTITEM_VALUE.like(tDictitem.getDictitemValue()),
				T_DICTITEM.SORT_NUM.eq(tDictitem.getSortNum()));
		MysqlSelect select = selectFrom(T_DICTITEM).where(condition).limit(start, limit);
		List<TDictitem> tDictItemList =  dslSession.fetchList(select, TDictitem.class);
		
		Select countSelect = select(T_DICTITEM.DICTITEM_ID.count()).from(T_DICTITEM).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tDictItemList);
		return pager;
	}

}
