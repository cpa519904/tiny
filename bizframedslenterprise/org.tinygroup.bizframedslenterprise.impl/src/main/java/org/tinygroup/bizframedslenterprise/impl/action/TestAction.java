package org.tinygroup.bizframedslenterprise.impl.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tinygroup.bizframedslenterprise.inter.action.SysParamAction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TUser;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

@Controller
public class TestAction implements WebContextAware {
	
	private WebContext webContext;
	
	 @RequestMapping(value = {"/sysLogin2.shtm"})
	    public String loginMethod(String loginname, String password) {
	        int result = 1;
	        webContext.getRequest().getSession().setAttribute("curUser2", "2222");
	        TUser user = new TUser();
	        user.setLoginName("aa");
	        user.setUserName("bb");
	        webContext.getRequest().getSession().setAttribute("userobj", user);
	        return "redirect:/page/test.page";
	    }

	 @RequestMapping(value = {"/sysLogin2.do"})
	    public String loginMethod2(String loginname, String password) {
	        webContext.getRequest().getSession().setAttribute("curUser2", "2222");
	        TUser user = new TUser();
	        user.setLoginName("aa");
	        user.setUserName("bb");
	        webContext.getRequest().getSession().setAttribute("userobj", user);
	        return "redirect:/page/test.page";
	    }
	 
	 @RequestMapping(value = {"/testmodel.do"})
	 public ModelAndView testmodel(ModelAndView mv) {
	       mv.addObject("res","11111");
	       mv.setViewName("redirect:/page/test.page");
	        return mv;
	    }
	 
	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}
}
