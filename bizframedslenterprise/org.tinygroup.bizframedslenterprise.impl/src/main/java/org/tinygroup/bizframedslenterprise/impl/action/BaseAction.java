package org.tinygroup.bizframedslenterprise.impl.action;

import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.bizframedslenterprise.impl.util.IpAddressUtil;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;
import org.tinygroup.weblayer.WebContext;


public class BaseAction {
	@Autowired
	CEPCore core;
	
	/*public CEPCore getCore() {
		return core;
	}

	public void setCore(CEPCore core) {
		this.core = core;
	}*/
	
	int getCurrentUserId(WebContext wContext) {
		Object userIdObj = wContext.getRequest().getSession().getAttribute("currentUserId");
		int currentUserId = 0;
		if(userIdObj!=null){
			currentUserId = (Integer)userIdObj;
		}
		return currentUserId;
	}
	
	void writeOperatorLog(LogAdapter logAdapter,WebContext wContext,int operationType,String operationContent){
		logAdapter.writeLog(getCurrentUserId(wContext), operationType, operationContent, IpAddressUtil.getClientIp(wContext.getRequest()));
	}
	
	protected Object callService(String serviceId, Context context) {
//		CEPCore core = SpringBeanContainer.getBean(CEPCore.CEP_CORE_BEAN);
		Event event = new Event();
		ServiceRequest sq = new ServiceRequest();
		sq.setServiceId(serviceId);
		sq.setContext(context);
		event.setServiceRequest(sq);
		core.process(event);

		ServiceInfo info = core.getServiceInfo(serviceId);
		List<Parameter> resultsParam = info.getResults();
		if (resultsParam==null||resultsParam.size() == 0) {
			return null;
		}
		return event.getServiceRequest().getContext()
				.get(resultsParam.get(0).getName());
	}
	
}
