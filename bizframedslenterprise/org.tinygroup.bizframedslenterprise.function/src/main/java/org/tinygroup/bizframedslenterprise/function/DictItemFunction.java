package org.tinygroup.bizframedslenterprise.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.dict.Dict;
import org.tinygroup.dict.DictGroup;
import org.tinygroup.dict.DictItem;
import org.tinygroup.dict.DictManager;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.TemplateFunction;

public class DictItemFunction  implements  TemplateFunction{
	private static Logger logger = LoggerFactory.getLogger(DictItemFunction.class);

	public String getBindingTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNames() {
		// TODO Auto-generated method stub
		return "getDictItem";
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
		DictManager dictManager = BeanContainerFactory.getBeanContainer(DictItemFunction.class.getClassLoader()).getBean("dictManager");
		Dict dict = null;
		try{
			dict = dictManager.getDict(parameters[0].toString(), context);
		}catch(Exception e){
			logger.errorMessage(e.getMessage());
			return null;
		}
		List<DictGroup> dictList = dict.getDictGroupList();
		Map<String,String> dictItemMap = new HashMap<String,String>();
		List<DictItem> dictItemlist = null;
		if(dictList.size()==1){
			dictItemlist = dictList.get(0).getItemList();
			if(dictItemlist!=null){
				for(DictItem dictItem:dictItemlist){
					dictItemMap.put(dictItem.getValue(), dictItem.getText());
				}
			}
		}
		Map<String,Object> dictItem = new HashMap<String,Object>();
		dictItem.put("map", dictItemMap);
		dictItem.put("list", dictItemlist);
		return dictItem;
	}
}
