package org.tinygroup.bizframedslenterprise.impl.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.bizframedslenterprise.inter.action.OrganizationAction;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.organization.OrganizationService;
import org.tinygroup.bizframedslenterprise.inter.pojo.TOrganization;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

@Controller
public class OrganizationActionImpl extends BaseAction implements OrganizationAction,WebContextAware{

	private WebContext webContext;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private LogAdapter logAdapter;
	

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	@RequestMapping(value = { "/addOrganization.do" })
	public @ResponseBody TOrganization addOrganizationMethod(TOrganization tOrganization) {
		writeOperatorLog(logAdapter,webContext, 1, "开始增加机构");
		tOrganization = organizationService.addOrganization(tOrganization);
		writeOperatorLog(logAdapter,webContext, 1, "完成增加机构");
		return tOrganization;
	}

	@RequestMapping(value = { "/updateOrganization.do" })
	public @ResponseBody void updateOrganizationMethod(TOrganization tOrganization) {
		writeOperatorLog(logAdapter,webContext, 1, "开始更新机构");
		organizationService.updateOrganization(tOrganization);
		writeOperatorLog(logAdapter,webContext, 1, "完成更新机构");
	}

	@RequestMapping(value = { "/deleteOrganization.do" })
	public  @ResponseBody void deleteOrganizationMethod(String organizationIds) {
		writeOperatorLog(logAdapter,webContext, 1, "开始刪除机构");
		organizationService.deleteOrganizations(organizationIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成刪除机构");
	}
	
}
