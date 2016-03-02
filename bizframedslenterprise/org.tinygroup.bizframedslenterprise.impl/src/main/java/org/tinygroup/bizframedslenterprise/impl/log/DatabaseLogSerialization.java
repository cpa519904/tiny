package org.tinygroup.bizframedslenterprise.impl.log;

import java.util.Date;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TOperationLogDao;
import org.tinygroup.bizframedslenterprise.inter.log.LogSerialization;
import org.tinygroup.bizframedslenterprise.inter.pojo.TOperationLog;

public class DatabaseLogSerialization  extends BaseService implements LogSerialization{
	TOperationLogDao tOperationLogDao;
	
	public TOperationLogDao gettOperationLogDao() {
		return tOperationLogDao;
	}

	public void settOperationLogDao(TOperationLogDao tOperationLogDao) {
		this.tOperationLogDao = tOperationLogDao;
	}

	public void saveLog(int userId, int operationType, String operationContent,
			String operationIp) {
		
		TOperationLog tOperationLog = new TOperationLog();
		tOperationLog.setOperationContent(operationContent);
		tOperationLog.setUserId(userId);
		tOperationLog.setOperationType(operationType);
		tOperationLog.setOperationIp(operationIp);
		tOperationLog.setCreateTime(new Date());
		tOperationLogDao.insertTOperationLog(tOperationLog);
	}
	
}
