package org.tinygroup.bizframedslenterprise.inter.log;


public interface LogSerialization {
	public void saveLog(int userId, int operationType,String operationContent, String operationIp);
}
