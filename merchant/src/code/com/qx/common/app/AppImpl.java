package com.qx.common.app;

import javax.naming.NamingException;

/**
 * @author jixiaofeng
 */

public class AppImpl extends BaseApplicationContext {
	private static AppImpl application = null;
	private final static String APPLICATION_BEAN_NAME = "app";

	public static AppImpl getMe() {
		if (application == null) {
			try {
				application = BaseApplicationContext.getBean(AppImpl.class,
						APPLICATION_BEAN_NAME);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return application;
	}

	public Object getBean(String strName) throws NamingException {
		Object temp = getApplicationContext().getBean(strName);
		return temp;
	}

}
