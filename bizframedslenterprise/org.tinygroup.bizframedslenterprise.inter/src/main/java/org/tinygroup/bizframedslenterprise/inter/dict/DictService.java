package org.tinygroup.bizframedslenterprise.inter.dict;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TDict;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDictitem;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public interface DictService {
	TDict addDict(TDict tDict);

	TDict getDict(String dictId);

	void deleteDict(String dictId);
	
	void deleteDicts(String dictIds);

	void updateDict(TDict tDict);

	List<TDict> getDicts(TDict tDict);

	List<TDict> getDicts(TDict tDict, int start, int limit);
	
	Pager getDictPager(TDict tDict, int start, int limit);

	void deleteDictItems(int dictId);
	
	TDictitem addDictItem(TDictitem tDictItem);

	void deleteDictItem(int dictitemId);
	
	void deleteDictItems(String dictitemIds);
	
	void updateDictItem(TDictitem tDictitem);
	
	TDictitem getDictItem(int dictitemId);

	List getDictItems(int dictId);
	
	Pager getDictItemPager(int dictId,int start, int limit);
}
