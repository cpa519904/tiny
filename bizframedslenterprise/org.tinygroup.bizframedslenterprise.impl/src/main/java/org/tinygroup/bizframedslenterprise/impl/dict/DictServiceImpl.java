package org.tinygroup.bizframedslenterprise.impl.dict;

import java.util.List;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TDictDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TDictitemDao;
import org.tinygroup.bizframedslenterprise.inter.dict.DictService;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDict;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDictitem;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public class DictServiceImpl extends BaseService implements DictService {
	private TDictDao tDictDao;
	
	private TDictitemDao tDictitemDao;
	
	public TDictitemDao gettDictitemDao() {
		return tDictitemDao;
	}

	public void settDictitemDao(TDictitemDao tDictitemDao) {
		this.tDictitemDao = tDictitemDao;
	}

	public TDictDao gettDictDao() {
		return tDictDao;
	}

	public void settDictDao(TDictDao tDictDao) {
		this.tDictDao = tDictDao;
	}

	public TDict addDict(TDict tDict) {
		return tDictDao.insertTDict(tDict);
	}

	public TDict getDict(String dictId) {
		return tDictDao.getTDictById(dictId);
	}

	public void deleteDict(String dictId) {
		tDictDao.deleteTDict(dictId);
	}

	public void deleteDicts(String dictIds) {
		tDictDao.deleteTDictByIds(dictIds);
	}

	public void updateDict(TDict tDict) {
		tDictDao.updateTDict(tDict);
	}

	public List<TDict> getDicts(TDict tDict) {
		return null;
	}

	public List<TDict> getDicts(TDict tDict, int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager getDictPager(
			TDict tDict, int start, int limit) {
		return tDictDao.queryTDictsPager(tDict, start, limit);
	}

	public void deleteDictItems(int dictId) {
		// TODO Auto-generated method stub
		
	}

	public TDictitem addDictItem(TDictitem tDictItem) {
		return tDictitemDao.insertTDictitem(tDictItem);
	}

	public void deleteDictItem(int dictitemId) {
		
	}

	public void deleteDictItems(String dictitemIds) {
		tDictitemDao.deleteTDictitemByIds(dictitemIds.split(","));
	}

	public void updateDictItem(TDictitem tDictitem) {
		tDictitemDao.updateTDictitem(tDictitem);
	}

	public TDictitem getDictItem(int dictitemId) {
		// TODO Auto-generated method stub
		return tDictitemDao.getTDictitemById(dictitemId);
	}

	public List getDictItems(int dictId) {
		TDictitem tDictitem = new TDictitem();
		tDictitem.setDictId(dictId);
		return tDictitemDao.queryTDictitems(tDictitem);
	}

	public Pager getDictItemPager(int dictId, int start, int limit) {
		TDictitem tDictitem = new TDictitem();
		tDictitem.setDictId(dictId);
		Pager pager = tDictitemDao.queryTDictitemsPager(tDictitem, start, limit);
		return pager;
	}

}
