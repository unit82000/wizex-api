package com.wisenc.wizex.framework.util;

import org.springframework.util.StringUtils;

/**
 * @Class Name : StringUtil.java
 * @Description : string util.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2020. 2. 27.   		최초생성
 *
 * @author 김경석
 * @since 2020. 2. 27.
 * @version 1.0
 * @see
 */
public class StringUtil {

	public static final String	CAMEL_CASE_PATTERN	= "([a-z])([A-Z]+)";

	/**
	 * camel case to snake case.
	 *
	 * @param camelCase
	 * @return
	 */
	public static String toSnakeCase(String camelCase) {
		if (StringUtils.isEmpty(camelCase)) return "";

		return camelCase.replaceAll(CAMEL_CASE_PATTERN, "$1_$2").toUpperCase();
	}
}
