package org.tinygroup.bizframedslenterprise.impl.security;

import org.tinygroup.application.Application;
import org.tinygroup.application.ApplicationProcessor;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.bizframedslenterprise.inter.security.Encryption;
import org.tinygroup.config.impl.AbstractConfiguration;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

public class EncryptionProcessor extends AbstractConfiguration implements ApplicationProcessor{
	private static final Logger logger = LoggerFactory.getLogger(EncryptionProcessor.class);
	
	private static final String LOG_OPERATION_PATH = "/application/encryption-config";
	
	private static final String ENCRYPTION_BEAN_TAG = "encryption-bean";
	
	private static final String ENCRYPTION_ATTR_NAME = "name";
	
	private Encryption encryption;

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
		logger.logMessage(LogLevel.INFO, "开始启动EncryptionProcessor");
		String encryptionBean = applicationConfig.getSubNode(ENCRYPTION_BEAN_TAG).getAttribute(ENCRYPTION_ATTR_NAME);
		encryption = BeanContainerFactory.getBeanContainer(
				this.getClass().getClassLoader()).getBean(encryptionBean);
		logger.logMessage(LogLevel.INFO, "启动EncryptionProcessor完毕");
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
	
	
	public String encode(String inputStr){
		return encryption.execute(inputStr);
	}


}