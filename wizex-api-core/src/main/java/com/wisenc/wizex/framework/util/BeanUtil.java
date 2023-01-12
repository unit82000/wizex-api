package com.wisenc.wizex.framework.util;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Class Name : BeanUtil.java
 * @Description : reflection을 사용하기 위한 편의 class.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2019. 12. 24.   		최초생성
 *
 * @author 김경석
 * @since 2019. 12. 24.
 * @version 1.0
 * @see
 */
public class BeanUtil {

	private static final Logger	LOGGER	= LoggerFactory.getLogger(BeanUtil.class);

	/**
	 * 호출된 method 조회.
	 *
	 * @param call
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final Method getMethod(ProceedingJoinPoint call) {
		try {
			Object[]	parameters		= call.getArgs();
			Class[]		parameterTypes	= new Class[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					parameterTypes[i]	= parameters[i].getClass();
				else
					parameterTypes[i]	= null;
			}

			String	methodName	= call.getSignature().getName();
			Method	method		= call.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
			return method;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Object to json string.
	 *
	 * @param obj
	 * @return
	 */
	public static final String toJsonString(Object obj) {
		ObjectMapper	mapper	= new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_EMPTY);

		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Object to json string.
	 *
	 * @param obj
	 * @param ignores
	 * @return
	 */
	public static final String toJsonString(Object obj, String[] ignores) {
		Object	newObj	= deepCopy(obj, obj.getClass(), ignores);
		return toJsonString(newObj);
	}

	/**
	 * deep copy.
	 *
	 * @param source
	 * @param targetClass
	 * @param ignores
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final Object deepCopy(Object source, Class targetClass, String[] ignores) {
		try {
			Object	target	= targetClass.newInstance();
			BeanUtils.copyProperties(source, target, ignores);
			return target;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}
}
