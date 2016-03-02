package org.tinygroup.bizframedslenterprise.inter.sysparam;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TSysparam;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public interface SysParamService {
	
	TSysparam addSysParamService(TSysparam tSysparam);

	void updateSysParamService(TSysparam tSysparam);

	void deleteSysParam(String sysParamId);

	void deleteSysParams(String sysParamIds);
	
	TSysparam getSysParam(String sysParamId);

	List<TSysparam> getSysParams(TSysparam tSysparam);

	Pager getSysParamsPager(TSysparam tSysparam, int start,int limit);
}
