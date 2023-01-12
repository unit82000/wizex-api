package com.wisenc.wizex.api.common.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Class Name : WizexApiApplicationContext.java
 * Description : Spring bean을 얻기 위한 Utility.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 18.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 18.
 * version 1.0
 */
@Component
public class WizexApiApplicationContext implements ApplicationContextAware {

	private static ApplicationContext	applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext	= ac;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object getBean(Class clazz) {
		return applicationContext.getBean(clazz);
	}
}
