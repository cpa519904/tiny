package org.tinygroup.bizframedslenterprise.impl.dict;

import java.util.List;

import org.tinygroup.bizframedslenterprise.impl.dao.TDictDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TDictitemDao;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDict;
import org.tinygroup.bizframedslenterprise.inter.pojo.TDictitem;
import org.tinygroup.dict.DictGroup;
import org.tinygroup.dict.DictManager;
import org.tinygroup.dict.impl.AbstractDictLoader;

public class DictDataLoaderImpl extends AbstractDictLoader {

	private TDictDao tDictDao;
	
	private TDictitemDao tDictitemDao;
	
	private LogAdapter logAdapter;

	
	public TDictDao gettDictDao() {
		return tDictDao;
	}

	public void settDictDao(TDictDao tDictDao) {
		this.tDictDao = tDictDao;
	}

	public TDictitemDao gettDictitemDao() {
		return tDictitemDao;
	}

	public void settDictitemDao(TDictitemDao tDictitemDao) {
		this.tDictitemDao = tDictitemDao;
	}

	public LogAdapter getLogAdapter() {
		return logAdapter;
	}

	public void setLogAdapter(LogAdapter logAdapter) {
		this.logAdapter = logAdapter;
	}


	public void load(DictManager dictManager) {

			//select * from t_dict
			List<TDict> tDicts = tDictDao.queryTDicts(null);
			if (tDicts != null) {
				for (TDict tDict : tDicts) {
					int dictId = tDict.getDictId();
					TDictitem tDictitem = new TDictitem();
					tDictitem.setDictId(dictId);
					//select * from t_dict_item where dict_id=?
					List<TDictitem> items = tDictitemDao.queryTDictitems(tDictitem);
					org.tinygroup.dict.Dict dict = new org.tinygroup.dict.Dict();
//					dict.setName((String) bean.get(DICT_VALUE));
					dict.setName(tDict.getDictValue());
					DictGroup group = new DictGroup();
					if(items!=null){
						for (TDictitem item : items) {
							org.tinygroup.dict.DictItem dictItem = new org.tinygroup.dict.DictItem();
//							dictItem.setText((String) item.get(DICT_ITEM_NAME));
//							dictItem.setValue((String) item.get(DICT_ITEM_VALUE));
							dictItem.setText(item.getDictitemName());
							dictItem.setValue(item.getDictitemValue());
							group.addDictItem(dictItem);
						}
					}
					dict.addDictGroup(group);
					putDict(dict.getName(), dict, dictManager);
				}
			}

	}

}
