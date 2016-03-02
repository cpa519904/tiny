package org.tinygroup.bizframedslenterprise.impl.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.bizframedslenterprise.inter.action.SysParamAction;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.pojo.TSysparam;
import org.tinygroup.bizframedslenterprise.inter.sysparam.SysParamService;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

@Controller
public class SysParamActionImpl  extends BaseAction implements SysParamAction,WebContextAware{

	private WebContext webContext;
	
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	private LogAdapter logAdapter;


	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public LogAdapter getLogAdapter() {
		return logAdapter;
	}

	public void setLogAdapter(LogAdapter logAdapter) {
		this.logAdapter = logAdapter;
	}

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	@RequestMapping(value = { "/addSysParam.do" })
	public @ResponseBody TSysparam addSysParamMethod(TSysparam tSysparam) {
		writeOperatorLog(logAdapter,webContext, 1, "开始增加角色");
		tSysparam = sysParamService.addSysParamService(tSysparam);
		writeOperatorLog(logAdapter,webContext, 1, "完成增加角色");
		return tSysparam;
	}
	

	@RequestMapping(value = { "/updateSysParam.do" })
	public @ResponseBody void updateSysParamMethod(TSysparam tSysparam) {
		
		writeOperatorLog(logAdapter,webContext, 1, "开始更新角色");
		sysParamService.updateSysParamService(tSysparam);
		writeOperatorLog(logAdapter,webContext, 1, "完成更新角色");
	}

	@RequestMapping(value = { "/deleteSysParam.do" })
	public @ResponseBody void deleteSysParamMethod(String sysParamIds) {
		System.out.println("2341");
		writeOperatorLog(logAdapter,webContext, 1, "开始刪除角色");
		sysParamService.deleteSysParams(sysParamIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成刪除角色");
	}
	
}