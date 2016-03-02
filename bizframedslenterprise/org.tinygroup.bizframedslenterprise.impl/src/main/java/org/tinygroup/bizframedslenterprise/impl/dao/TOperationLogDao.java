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

import static org.tinygroup.bizframedslenterprise.impl.dao.TOperationLogTable.*;
import static org.tinygroup.bizframedslenterprise.impl.dao.TOrganizationTable.T_ORGANIZATION;
import static org.tinygroup.tinysqldsl.Select.and;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.select;
import static org.tinygroup.tinysqldsl.extend.MysqlSelect.selectFrom;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TOperationLog;
import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TOperationLogDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TOperationLog insertTOperationLog(TOperationLog tOperationLog) {
		Insert insert = insertInto(T_OPERATION_LOG).values(
				T_OPERATION_LOG.OPERATION_CONTENT.value(tOperationLog.getOperationContent()),
				T_OPERATION_LOG.USER_ID.value(tOperationLog.getUserId()),
				T_OPERATION_LOG.OPERATION_IP.value(tOperationLog.getOperationIp()),
				T_OPERATION_LOG.OPERATION_TYPE.value(tOperationLog.getOperationType()),
				T_OPERATION_LOG.CREATE_TIME.value(tOperationLog.getCreateTime()));
		dslSession.execute(insert);
		return tOperationLog;
	}

	public void updateTOperationLog(TOperationLog tOperationLog) {
		Update update = update(T_OPERATION_LOG).set(
				T_OPERATION_LOG.OPERATION_CONTENT.value(tOperationLog.getOperationContent()),
				T_OPERATION_LOG.USER_ID.value(tOperationLog.getUserId()),
				T_OPERATION_LOG.OPERATION_IP.value(tOperationLog.getOperationIp()),
				T_OPERATION_LOG.OPERATION_TYPE.value(tOperationLog.getOperationType()),
				T_OPERATION_LOG.CREATE_TIME.value(tOperationLog.getCreateTime())).where(
				T_OPERATION_LOG.OPERATOR_LOG_ID.eq(tOperationLog.getOperatorLogId()));
		dslSession.execute(update);
	}

	public void deleteTOperationLog(String id) {
		Delete delete = delete(T_OPERATION_LOG).where(T_OPERATION_LOG.OPERATOR_LOG_ID.eq(id));
		dslSession.execute(delete);
	}

	public TOperationLog getTOperationLogById(String id) {
		Select select = selectFrom(T_OPERATION_LOG).where(T_OPERATION_LOG.OPERATOR_LOG_ID.eq(id));
		return dslSession.fetchOneResult(select, TOperationLog.class);
	}

	public List<TOperationLog> queryTOperationLogs(TOperationLog tOperationLog) {
		if(tOperationLog == null){
			tOperationLog = new TOperationLog();
		}
		Select select = selectFrom(T_OPERATION_LOG).where(
				and(
				T_OPERATION_LOG.OPERATION_CONTENT.like(tOperationLog.getOperationContent()),
				T_OPERATION_LOG.USER_ID.eq(tOperationLog.getUserId()),
				T_OPERATION_LOG.OPERATION_IP.like(tOperationLog.getOperationIp()),
				T_OPERATION_LOG.OPERATION_TYPE.eq(tOperationLog.getOperationType()),
				T_OPERATION_LOG.CREATE_TIME.eq(tOperationLog.getCreateTime())));
		return dslSession.fetchList(select, TOperationLog.class);
	}
	
	public Pager queryTOperationLogsPager(TOperationLog tOperationLog,int start,int limit) {
		if(tOperationLog == null){
			tOperationLog = new TOperationLog();
		}
		Condition condition = 
		and(
				T_OPERATION_LOG.OPERATION_CONTENT.eq(tOperationLog.getOperationContent()),
				T_OPERATION_LOG.USER_ID.eq(tOperationLog.getUserId()),
				T_OPERATION_LOG.OPERATION_IP.eq(tOperationLog.getOperationIp()),
				T_OPERATION_LOG.OPERATION_TYPE.eq(tOperationLog.getOperationType()),
				T_OPERATION_LOG.CREATE_TIME.eq(tOperationLog.getCreateTime()));
		
		MysqlSelect select = selectFrom(T_OPERATION_LOG).where(condition).limit(start, limit);
		List<TOperationLog> tOperationList =  dslSession.fetchList(select, TOperationLog.class);
		
		Select countSelect = select(T_OPERATION_LOG.OPERATOR_LOG_ID.count()).from(T_OPERATION_LOG).where(condition);
		Integer countObj = dslSession.fetchOneResult(countSelect, Integer.class);
		Pager pager = new Pager(Integer.valueOf(countObj),start,limit,tOperationList);
		return pager;
	}

}
