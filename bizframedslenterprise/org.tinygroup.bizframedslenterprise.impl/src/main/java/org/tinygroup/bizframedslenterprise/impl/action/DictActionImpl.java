package org.tinygroup.bizframedslenterprise.impl.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.bizframedslenterprise.inter.action.DictAction;
import org.tinygroup.bizframedslenterprise.inter.dict.DictService;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDict;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDictitem;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

@Controller()
public class DictActionImpl extends BaseAction implements DictAction,WebContextAware{
	private WebContext webContext;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private LogAdapter logAdapter;
	
	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	public DictService getDictService() {
		return dictService;
	}

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	@RequestMapping(value = { "/addDict.do" })
	public @ResponseBody TDict addDictMethod(){
		writeOperatorLog(logAdapter,webContext, 1, "开始增加字典");
//		TDict dict = dictService.addDict(tDict);
		TDict dict = (TDict) callService("addDict", webContext);
		writeOperatorLog(logAdapter,webContext, 1, "完成增加字典");
		return dict;
	}
	
	@RequestMapping(value= {"/delDict.do"})
	public @ResponseBody void deleteDictMethod(String dictIds){
		writeOperatorLog(logAdapter,webContext, 1, "开始删除字典");
		dictService.deleteDicts(dictIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成删除字典");
	}
	
	@RequestMapping(value= {"/updateDict.do"})
	public @ResponseBody void updateDictMethod(){
		String ip = webContext.getRequest().getRemoteAddr();
		writeOperatorLog(logAdapter,webContext, 1, "开始更新字典");
//		dictService.updateDict(tDictBean);
		callService("updateDict",webContext);
		writeOperatorLog(logAdapter,webContext, 1, "完成更新字典");
	}

	@RequestMapping(value= {"/addDictItem.do"})
	public @ResponseBody TDictitem addDictItemMethod(TDictitem tDictItem) {
		writeOperatorLog(logAdapter,webContext, 1, "开始新增字典项");
		tDictItem = dictService.addDictItem(tDictItem);
		writeOperatorLog(logAdapter,webContext,1, "完成新增字典项");
		return tDictItem;
		
	}

	@RequestMapping(value= {"/updateDictItem.do"})
	public @ResponseBody void updateDictItemMethod(TDictitem tDictitem) {
		writeOperatorLog(logAdapter,webContext, 1, "开始更新字典项");
		dictService.updateDictItem(tDictitem);
		writeOperatorLog(logAdapter,webContext, 1, "完成更新字典项");
	}

	@RequestMapping(value= {"/deleteDictItem.do"})
	public @ResponseBody void deleteDictItemMethod(String dictitemIds) {
		writeOperatorLog(logAdapter,webContext, 1, "开始删除字典项");
		dictService.deleteDictItems(dictitemIds);
		writeOperatorLog(logAdapter,webContext, 1, "完成删除字典项");
	}
	

}
