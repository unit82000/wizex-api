package com.wisenc.wizex.api.common.javascript.service;

import java.util.Map;
import java.util.regex.Pattern;

import com.wisenc.wizex.api.cndrule.vo.CndRuleFuncVO;

/**
 * Class Name : JavascriptService.java
 * Description : Javascript service.
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
public interface JavascriptService {
	public static final Pattern 	PATTERN 	= Pattern.compile("\\$\\{[a-zA-Z0-9 _:\"=]*\\}");

	/** function 명과 결과 사이의 구분자 */
	public static final String		RESULT_DELIMITER		= ":";

	/** 결과와 파라미터 사이의 구분자 */
	public static final String		PARAMS_DELIMITER	= "::";

	/** function prefix */
	public static final String		FUNC_PREFIX				= "fn";

	/**
	 * script 실행.
	 *
	 * @param script
	 * @return
	 */
	public Object run(String script);

	/**
	 * ${CND0000006:uwtRstMsg::payRuleCode="09"} 형식의 문자열을 실행 가능한 javascript로 변환.
	 *
	 * @param funcDesc
	 * @param paramMap
	 * @return
	 */
	CndRuleFuncVO createToCallInFunc(String funcDesc, Map<String, Object> paramMap);
}
