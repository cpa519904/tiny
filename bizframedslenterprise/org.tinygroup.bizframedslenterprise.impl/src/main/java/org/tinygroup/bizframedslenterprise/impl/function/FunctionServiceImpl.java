package org.tinygroup.bizframedslenterprise.impl.function;

import java.util.List;

import org.tinygroup.bizframedslenterprise.impl.BaseService;
import org.tinygroup.bizframedslenterprise.impl.dao.TFunctionDao;
import org.tinygroup.bizframedslenterprise.impl.dao.TRoleTFunctionDao;
import org.tinygroup.bizframedslenterprise.inter.function.FunctionService;
import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRoleTFunction;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;

public class FunctionServiceImpl extends BaseService implements FunctionService {

	private TFunctionDao tFunctionDao;
	
	private TRoleTFunctionDao tRoleTFunctionDao;
	
	public TFunctionDao gettFunctionDao() {
		return tFunctionDao;
	}

	public void settFunctionDao(TFunctionDao tFunctionDao) {
		this.tFunctionDao = tFunctionDao;
	}

	public TRoleTFunctionDao gettRoleTFunctionDao() {
		return tRoleTFunctionDao;
	}

	public void settRoleTFunctionDao(TRoleTFunctionDao tRoleTFunctionDao) {
		this.tRoleTFunctionDao = tRoleTFunctionDao;
	}

	public TFunction addFunction(TFunction tFunction) {
		return tFunctionDao.insertTFunction(tFunction);
	}

	public void updateFunciton(TFunction tFunction) {
		tFunctionDao.updateTFunction(tFunction);
	}

	public void deleteFunction(String functionId) {
		// TODO Auto-generated method stub
		
	}

	public void deleteFunctions(String functionIds) {
		tFunctionDao.deleteTFunctionByIds(toArray(functionIds));
	}

	public TFunction getFunction(String functionId) {
		return tFunctionDao.getTFunctionById(functionId);
	}

	public List<TFunction> getAllFunctions(TFunction tFunction) {
		return tFunctionDao.queryTFunctions(tFunction);
	}

	public List<TFunction> getAllFunctions(TFunction tFunction, int start,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager getAllFunctionPager(TFunction tFunction, int start, int limit) {
		return tFunctionDao.queryTFunctions(tFunction, start, limit);
	}

	public List<TFunction> getFunctions(TFunction tFunction) {
		return tFunctionDao.queryTFunctions(tFunction);
	}

	public List<TFunction> getSubFunctions(TFunction tFunction) {
		return tFunctionDao.queryTFunctions(tFunction);
	}

	public List<TFunction> getChildrenAllFunctions(int functionId) {
		return null;
	}

	public List<TFunction> getChildrenFunctions(int functionId) {
		return null;
	}

	public List<TFunction> getChildrenSubFunctions(int functionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TRole> getRolesByFunctionId(int functionId) {
		return tRoleTFunctionDao.queryFunctionRoles(functionId);
	}

	public List<TFunction> getRootFunctions() {
		// TODO Auto-generated method stub
		return tFunctionDao.queryTFunctions(null);
	}

	public Pager getRootFunctionsPager(int start, int limit) {
		// TODO Auto-generated method stub
		return tFunctionDao.queryRootTFunctions(null, start, limit);
	}

	public List<TRole> getFunctionRoles(int functionId) {
		return tRoleTFunctionDao.queryTRoleByFunctionId(functionId);
	}

	public List<TRole> getUnssignFunctionRoles(int functionId) {
		return tRoleTFunctionDao.queryUnassignTRoles(functionId);
	}

	public void addFunctionRoles(int functionId, Integer[] roleId) {
		for(Integer rId:roleId){
			TRoleTFunction tRoleTFunction = new TRoleTFunction();
			tRoleTFunction.setFunctionId(functionId);
			tRoleTFunction.setRoleId(rId);
			tRoleTFunctionDao.insertTRoleTFunction(tRoleTFunction);
		}
	}

	public void removeFunctionRoles(int functionId, Integer[] roleId) {
		tRoleTFunctionDao.deleteTFunctionTRoles(functionId, roleId);
	}

	public boolean checkFunction(TFunction tFunction) {
		if(tFunction.getFunctionId()!=null){
			if(tFunctionDao.queryTFunctionCount(tFunction)==1){//表示是当前记录	
				return false;
			}
			tFunction.setFunctionId(null);
		}
		if(tFunctionDao.queryTFunctionCount(tFunction)>0){
			return true;
		}
		return false;
	}

	
	
}
