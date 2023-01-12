package com.wisenc.wizex.api.cndrule.vo;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wisenc.wizex.framework.vo.RequestForCacheVO;

/**
 * Class Name : CndRuleParamVO.java
 * Description : 조건규칙 요청 Parameter VO
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
public class CndRuleParamVO extends RequestForCacheVO {

	/** 조건규칙코드 */
	private String	cndRuleCd;

	/** 유효일자 */
	private String	stDt;

	/** 요청시 전달한 전체 파라미터 Map */
	private Map<String, String[]>	allParams;

	@Override
	public String getCacheKeySource() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCndRuleCd() {
		return cndRuleCd;
	}

	public void setCndRuleCd(String cndRuleCd) {
		this.cndRuleCd = cndRuleCd;
	}

	public String getStDt() {
		return stDt;
	}

	public void setStDt(String stDt) {
		this.stDt = stDt;
	}

	public Map<String, String[]> getAllParams() {
		return allParams;
	}

	public void setAllParams(Map<String, String[]> allParams) {
		this.allParams = allParams;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
