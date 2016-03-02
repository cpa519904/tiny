package org.tinygroup.bizframedslenterprise.impl.log;

import org.tinygroup.application.Application;
import org.tinygroup.application.ApplicationProcessor;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.bizframedslenterprise.inter.log.LogAdapter;
import org.tinygroup.bizframedslenterprise.inter.log.LogSerialization;
import org.tinygroup.config.impl.AbstractConfiguration;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

public class LogAdapterImpl extends AbstractConfiguration implements ApplicationProcessor,LogAdapter{
	private static final Logger logger = LoggerFactory.getLogger(LogAdapterImpl.class);
	
	private static final String LOG_OPERATION_PATH = "/application/log-operation-config";
	
	private static final String OPERATOR_BEAN_TAG = "operater-bean";
	
	private static final String OPERATOR_ATTR_NAME = "name";
	
	private LogSerialization logSerialization;
	
	
	public String getApplicationNodePath() {
		// TODO Auto-generated method stub
		return LOG_OPERATION_PATH;
	}

	public String getComponentConfigPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void start() {
		
		logger.logMessage(LogLevel.INFO, "开始启动LogAdapterImpl");
		String logOperatorBean = applicationConfig.getSubNode(OPERATOR_BEAN_TAG).getAttribute(OPERATOR_ATTR_NAME);
		logSerialization = BeanContainerFactory.getBeanContainer(
				this.getClass().getClassLoader()).getBean(logOperatorBean);
		logger.logMessage(LogLevel.INFO, "启动LogAdapterImpl完毕");
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public void setApplication(Application application) {
		// TODO Auto-generated method stub
		
	}

	public void writeLog(int userId, int operationType,
			String operationContent, String operationIp) {
		logSerialization.saveLog(userId, operationType,operationContent, operationIp);
	}

}
