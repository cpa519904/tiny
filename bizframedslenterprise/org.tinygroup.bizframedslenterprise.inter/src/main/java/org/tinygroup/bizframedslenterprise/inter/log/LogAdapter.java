package org.tinygroup.bizframedslenterprise.inter.log;


public interface LogAdapter {
	public void writeLog(int userId,int operationType,String operationContent,String operationIp);
}
