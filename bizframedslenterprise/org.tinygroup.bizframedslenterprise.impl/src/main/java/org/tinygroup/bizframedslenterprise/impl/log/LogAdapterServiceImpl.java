package org.tinygroup.bizframedslenterprise.impl.log;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TOperationLogDao;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapterService;
import org.tinygroup.bizframedslenterprise.inter.pojo.TOperationLog;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public class LogAdapterServiceImpl extends BaseService implements LogAdapterService {
	private TOperationLogDao tOperationLogDao;
	
	public TOperationLogDao gettOperationLogDao() {
		return tOperationLogDao;
	}


	public void settOperationLogDao(TOperationLogDao tOperationLogDao) {
		this.tOperationLogDao = tOperationLogDao;
	}


	public Pager getLogsPager(
			TOperationLog tOperationLog, int start, int limit) {
		return tOperationLogDao.queryTOperationLogsPager(tOperationLog, start, limit);
	}

	

}
