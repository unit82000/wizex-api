package com.wisenc.wizex.api.cndrule.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wisenc.wizex.api.common.javascript.service.JavascriptService;

/**
 * Class Name : CndRuleFuncVO.java
 * Description : 조건규칙을 수행하기 위한 스크립트 정보.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 16.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 16.
 * version 1.0
 */
public class CndRuleFuncVO {

	/** 조건규칙코드 */
	private String	cndRuleCd;

	/** 함수호출결과 중 참조할 값 명칭 */
	private String	result;

	/** 함수호출시 사용할 파라미터 Map */
	private Map<String, Object>	params;

	public CndRuleFuncVO() {
		this.params		= new HashMap<String, Object>();
	}

	public CndRuleFuncVO(String cndRuleCd) {
		this();
		this.cndRuleCd		= cndRuleCd;
	}

	public CndRuleFuncVO(Map<String, Object>	params) {
		if (ObjectUtils.isEmpty(params))	this.params		= new HashMap<String, Object>();
		else										this.params		= SerializationUtils.clone((HashMap<String, Object>)params);
	}

	public CndRuleFuncVO(String cndRuleCd, Map<String, Object>	params) {
		this(params);
		this.cndRuleCd	= cndRuleCd;
	}

	/**
	 * 주어진 정보로 실행스크립트를 작성.
	 *
	 * @return
	 */
	public String createRunScript() {
		// 실행스크립트를 작성.
		StringBuilder	builder	= new StringBuilder();
		builder.append(JavascriptService.FUNC_PREFIX).append(cndRuleCd).append('(')
				.append(this.getParamString())
				.append(')');

		if (!StringUtils.isEmpty(this.result))	builder.append('.').append(this.result);

		return builder.toString();
	}

	public String getCndRuleCd() {
		return cndRuleCd;
	}

	public void setCndRuleCd(String cndRuleCd) {
		this.cndRuleCd = cndRuleCd;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * add param.
	 *
	 * @param key
	 * @param value
	 */
	public void addParam(String key, Object value) {
		this.params.put(key, value);
	}

	/**
	 * parameter를 json 문자열로 반환.
	 *
	 * @return
	 */
	public String getParamString() {
		JSONObject	json	= new JSONObject(this.params);
		return json.toString();
	}
}
