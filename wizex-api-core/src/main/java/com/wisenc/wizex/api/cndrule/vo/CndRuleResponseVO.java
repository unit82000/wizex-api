package com.wisenc.wizex.api.cndrule.vo;

import java.io.Serializable;
import java.util.Map;

import com.wisenc.wizex.framework.vo.ResponseVO;

/**
 * Class Name : CndRuleResponseVO.java
 * Description : 조건규칙 응답.
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
public class CndRuleResponseVO extends ResponseVO implements Serializable {

	private static final long serialVersionUID = 2749110041104888291L;

	/** 조건규칙 수행 결과 */
	private Map<String, Object>	result;

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
}
