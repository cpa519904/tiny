package org.tinygroup.bizframedslenterprise.inter.log;

import org.tinygroup.bizframedslenterprise.inter.pojo.TOperationLog;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;


public interface LogAdapterService {
	Pager getLogsPager(TOperationLog tOperationLog, int start,int limit);
}
