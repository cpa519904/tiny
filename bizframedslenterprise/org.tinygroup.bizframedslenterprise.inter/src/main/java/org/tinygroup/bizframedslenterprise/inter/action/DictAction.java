package org.tinygroup.bizframedslenterprise.inter.action;

import org.tinygroup.bizframedslenterprise.inter.pojo.TDict;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDictitem;

public interface DictAction {
	TDict addDictMethod();
	
	void deleteDictMethod(String dictIds);
	
	void updateDictMethod();
	
	TDictitem addDictItemMethod(TDictitem tDictItem);
	
	void updateDictItemMethod(TDictitem tDictItem);
	
	void deleteDictItemMethod(String dictitemIds);
}
