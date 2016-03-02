package org.tinygroup.bizframedslenterprise.inter.function;

import java.util.List;

import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;
import org.tinygroup.bizframedslenterprise.inter.pojo.TRole;
import org.tinygroup.bizframedslenterprise.inter.util.Pager;


public interface FunctionService {
	TFunction addFunction(TFunction tFunction);

	void updateFunciton(TFunction tFunction);

	void deleteFunction(String functionId);
	
	void deleteFunctions(String functionIds);

	/**
	 * 根据 function Id 获取对象详细信息
	 * 
	 * @param functionId
	 * @return
	 */
	TFunction getFunction(String functionId);

	/**
	 * 根据查询条件，从所有的权限中查询(包含子权限)
	 * 
	 * @param function
	 *            查询条件
	 * @return
	 */
	List<TFunction> getAllFunctions(TFunction tFunction);

	/**
	 * 根据查询条件，从所有权限中进行分页查询(包含子权限)
	 * 
	 * @param function
	 *            查询条件
	 * @param start
	 *            起始记录数
	 * @param limit
	 *            页数
	 * @return
	 */
	List<TFunction> getAllFunctions(TFunction tFunction, int start, int limit);
	
	
	Pager getAllFunctionPager(TFunction tFunction, int start, int limit);

	/**
	 * 根据查询条件查询权限(不包含子权限)
	 * 
	 * @param function
	 * @return
	 */
	List<TFunction> getFunctions(TFunction tFunction);

	/**
	 * 根据查询条件查询子权限
	 * 
	 * @param function
	 * @return
	 */
	List<TFunction> getSubFunctions(TFunction tFunction);

	/**
	 * 根据权限ID查询其下的所有权限
	 * 
	 * @param functionId
	 * @return
	 */
	List<TFunction> getChildrenAllFunctions(int functionId);

	/**
	 * 根据权限ID查询其下的权限(不包含子权限)
	 * 
	 * @param functionId
	 * @return
	 */
	List<TFunction> getChildrenFunctions(int functionId);

	/**
	 * 根据权限ID查询其下的所有子权限
	 * 
	 * @param functionId
	 * @return
	 */
	List<TFunction> getChildrenSubFunctions(int functionId);
	
	
	/**
	 * 根据权限id获取拥有该权限的角色列表
	 * @param functionId
	 * @return
	 */
	List<TRole> getRolesByFunctionId(int functionId);
	
	
	List<TFunction> getRootFunctions();
	
	Pager getRootFunctionsPager(int start,int limit);
	
	List<TRole> getFunctionRoles(int functionId);
	
	List<TRole> getUnssignFunctionRoles(int functionId);
	
	void addFunctionRoles(int functionId,Integer[] roleId);
	
	void removeFunctionRoles(int functionId,Integer[] roleId);
	
	boolean checkFunction(TFunction tFunction);
}
