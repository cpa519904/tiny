package org.tinygroup.bizframedslenterprise.inter.action;

import org.tinygroup.bizframedslenterprise.inter.pojo.TFunction;

public interface FunctionAction {
	TFunction addFunctionMethod();
	
	void updateFuncitonMethod(); 
	
	void deleteFunctionsMethod(String functionIds);
}
