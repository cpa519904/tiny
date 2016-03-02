package org.tinygroup.bizframedslenterprise.inter.action;

import org.tinygroup.bizframedslenterprise.inter.pojo.TSysparam;


public interface SysParamAction {
	
	TSysparam addSysParamMethod(TSysparam tSysparam);
	
	void updateSysParamMethod(TSysparam tSysparam);
	
	void deleteSysParamMethod(String sysParamIds);
}