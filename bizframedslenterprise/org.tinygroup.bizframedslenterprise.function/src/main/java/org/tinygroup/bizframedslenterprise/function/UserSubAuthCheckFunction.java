package org.tinygroup.bizframedslenterprise.function;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.bizframedslenterprise.inter.user.UserService;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.TemplateFunction;

/**
 * 检测用户是否具有子功能权限
 * @author wangwy11342
 *
 */
public class UserSubAuthCheckFunction  implements  TemplateFunction{
	public String getBindingTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNames() {
		// TODO Auto-generated method stub
		return "userSubAuthCheck,checkSubAuth";
	}

	public void setTemplateEngine(TemplateEngine templateEngine) {
		// TODO Auto-generated method stub
		
	}

	public TemplateEngine getTemplateEngine() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object execute(Template template, TemplateContext context,
			Object... parameters) throws TemplateException {
		int userId = Integer.valueOf(parameters[0].toString());
		int functionId = Integer.valueOf(parameters[1].toString());
		UserService userService = BeanContainerFactory.getBeanContainer(this.getClass().getClassLoader()).getBean("userService");
		return userService.checkSubFunctionByUserId(userId, functionId);
	}
	
}
