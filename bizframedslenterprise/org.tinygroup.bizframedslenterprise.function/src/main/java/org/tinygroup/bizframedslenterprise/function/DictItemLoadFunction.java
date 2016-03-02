package org.tinygroup.bizframedslenterprise.function;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.cache.CacheInitConfig;
import org.tinygroup.dict.DictManager;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.TemplateFunction;

public class DictItemLoadFunction  implements  TemplateFunction{
	private static Logger logger = LoggerFactory.getLogger(DictItemLoadFunction.class);
	 
	private CacheInitConfig config;
	
	public CacheInitConfig getConfig() {
		return config;
	}

	public void setConfig(CacheInitConfig config) {
		this.config = config;
	}

	public String getBindingTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNames() {
		// TODO Auto-generated method stub
		return "loadDictItemCache";
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
		boolean result = false;
		try{
		DictManager dictManager = BeanContainerFactory.getBeanContainer(DictItemLoadFunction.class.getClassLoader()).getBean("dictManager");
		dictManager.getCache().init(config.getRegion());
		dictManager.load();
		result = true;
		}catch(Exception e){
			logger.logMessage(LogLevel.ERROR, "加载字典缓存异常",e);
		}
		return result;
	}
}
