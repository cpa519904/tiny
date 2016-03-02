package org.tinygroup.bizframedslenterprise.impl.sysparam;

import java.util.List;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TSysparamDao;
import org.tinygroup.bizframedslenterprise.inter.pojo.TSysparam;
import org.tinygroup.bizframedslenterprise.inter.sysparam.SysParamService;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public class SysParamServiceImpl extends BaseService implements SysParamService {

	private TSysparamDao tSysparamDao;
	
	public TSysparamDao gettSysparamDao() {
		return tSysparamDao;
	}

	public void settSysparamDao(TSysparamDao tSysparamDao) {
		this.tSysparamDao = tSysparamDao;
	}

	public TSysparam addSysParamService(TSysparam tSysparam) {
		return tSysparamDao.insertTSysparam(tSysparam);
	}

	public void updateSysParamService(TSysparam tSysparam) {
		tSysparamDao.updateTSysparam(tSysparam);
	}

	public void deleteSysParam(String sysParamId) {
		tSysparamDao.deleteTSysparam(sysParamId);
	}

	public void deleteSysParams(String sysParamIds) {
		tSysparamDao.deleteTSysparamByIds(toArray(sysParamIds));
	}

	public TSysparam getSysParam(String sysParamId) {
		return tSysparamDao.getTSysparamById(sysParamId);
	}

	public List<TSysparam> getSysParams(TSysparam tSysparam) {
		return tSysparamDao.queryTSysparams(tSysparam);
	}

	public Pager getSysParamsPager(TSysparam tSysparam, int start,
			int limit) {
		return tSysparamDao.queryTSysparamsPager(tSysparam, start, limit);
	}

}
