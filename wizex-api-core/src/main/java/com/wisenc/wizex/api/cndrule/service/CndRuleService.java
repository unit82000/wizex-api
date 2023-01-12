package com.wisenc.wizex.api.cndrule.service;

import com.wisenc.wizex.api.cndrule.vo.CndRuleParamVO;
import com.wisenc.wizex.framework.vo.ResponseVO;

/**
 * Class Name : CndRuleService.java
 * Description : 조건규칙 서비스.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 3.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 3.
 * version 1.0
 */
public interface CndRuleService {

	/**
	 * 조건규칙 정보를 초기화.
	 */
	void init();

	/**
	 * 조건규칙 실행.
	 *
	 * @param cndRuleParamVO
	 * @return
	 */
	ResponseVO exec(CndRuleParamVO cndRuleParamVO);

}
