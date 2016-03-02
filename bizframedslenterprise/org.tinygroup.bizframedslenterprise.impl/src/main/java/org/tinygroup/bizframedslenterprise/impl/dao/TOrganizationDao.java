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

import static org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationTable.*;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TOrganizationDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TOrganization insertTOrganization(TOrganization tOrganization) {
		Insert insert = insertInto(T_ORGANIZATION).values(
//				T_ORGANIZATION.ORGANIZATION_ID.value(tOrganization.getOrganizationId()),
				T_ORGANIZATION.ORGANIZATION_NAME.value(tOrganization.getOrganizationName()),
				T_ORGANIZATION.PARENT_ID.value(tOrganization.getParentId()),
				T_ORGANIZATION.SORT_NUM.value(tOrganization.getSortNum()));
		dslSession.execute(insert);
		return tOrganization;
	}

	public void updateTOrganization(TOrganization tOrganization) {
		Update update = update(T_ORGANIZATION).set(
				T_ORGANIZATION.ORGANIZATION_NAME.value(tOrganization.getOrganizationName()),
				T_ORGANIZATION.PARENT_ID.value(tOrganization.getParentId()),
				T_ORGANIZATION.SORT_NUM.value(tOrganization.getSortNum())).where(
				T_ORGANIZATION.ORGANIZATION_ID.eq(tOrganization.getOrganizationId()));
		dslSession.execute(update);
	}

	public void deleteTOrganization(int id) {
		Delete delete = delete(T_ORGANIZATION).where(T_ORGANIZATION.ORGANIZATION_ID.eq(id));
		dslSession.execute(delete);
	}
	
	public void deleteTOrganizationByIds(Object... ids) {
		Delete delete = delete(T_ORGANIZATION).where(T_ORGANIZATION.ORGANIZATION_ID.in(ids));
		dslSession.execute(delete);
	}

	public TOrganization getTOrganizationById(int id) {
		Select select = selectFrom(T_ORGANIZATION).where(T_ORGANIZATION.ORGANIZATION_ID.eq(id));
		return dslSession.fetchOneResult(select, TOrganization.class);
	}

	public List<TOrganization> queryTOrganizations(TOrganization tOrganization) {
		
		Select select = selectFrom(T_ORGANIZATION);
		if(tOrganization!=null){
			select.and(
				T_ORGANIZATION.ORGANIZATION_NAME.like(tOrganization.getOrganizationName()),
				T_ORGANIZATION.PARENT_ID.eq(tOrganization.getParentId()),
				T_ORGANIZATION.SORT_NUM.eq(tOrganization.getSortNum()));
		}
		return dslSession.fetchList(select, TOrganization.class);
		
	}
	
	public Pager queryTOrganizations(TOrganization tOrganization,int start,int limit) {
		if(tOrganization==null){
			tOrganization = new TOrganization();
		}
		Condition condition = and(
				T_ORGANIZATION.ORGANIZATION_NAME.like(tOrganization.getOrganizationName()),
				T_ORGANIZATION.PARENT_ID.eq(tOrganization.getParentId()),
				T_ORGANIZATION.SORT_NUM.eq(tOrganization.getSortNum()));
		return queryTOrganizationsByCondition(condition,start,limit);
	}

	public Pager queryRootTOrganizations(TOrganization tOrganization,int start, int limit) {
		if(tOrganization==null){
			tOrganization = new TOrganization();
		}
		Condition condition = and(
				T_ORGANIZATION.ORGANIZATION_NAME.like(tOrganization.getOrganizationName()),
				T_ORGANIZATION.PARENT_ID.isNull(),//preantid is null
				T_ORGANIZATION.SORT_NUM.eq(tOrganization.getSortNum()));
		return queryTOrganizationsByCondition(condition,start,limit);
	}
	
	private Pager queryTOrganizationsByCondition(Condition condition,int start, int limit){
		MysqlSelect select = selectFrom(T_ORGANIZATION).where(condition).limit(start, limit);
		List<TOrganization> tRoleList =  dslSession.fetchList(select, TOrganization.class);
		
		Select countSelect = select(T_ORGANIZATION.ORGANIZATION_ID.count()).from(T_ORGANIZATION).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tRoleList);
		return pager;
	}
}
