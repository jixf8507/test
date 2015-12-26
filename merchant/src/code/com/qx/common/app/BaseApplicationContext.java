package com.qx.common.app;

import javax.naming.NamingException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring bean获取
 * 
 * @author jixf
 * @2014-12-2
 */

public class BaseApplicationContext implements ApplicationContextAware {
	// private static Log logger = LogFactory.getLog(AdsMainApplication.class);
	private static ApplicationContext applicationContext;


	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> theClass, String strName)
			throws NamingException {
		T temp = (T) getApplicationContext().getBean(strName);
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework .context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BaseApplicationContext.applicationContext = applicationContext;

	}

	public static ApplicationContext getApplicationContext()
			throws BeansException {
		return BaseApplicationContext.applicationContext;

	}

}
