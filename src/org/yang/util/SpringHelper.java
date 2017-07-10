package org.yang.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringHelper {

	private static ApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("beans.xml");
	}

	public final static ApplicationContext getApplicationContext() {
		return context;
	}

}
