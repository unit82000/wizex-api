package com.wisenc.wizex.api.cndrule.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Class Name : SvcCndRuleInVO.java
 * Description : 조건규칙 설정 정보. 파라미터.
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
public class SvcCndRuleInVO {

    private String cndRuleCd;

    private Integer cndRuleInSeq;

    private String paramKnm;

    private String paramEnm;

    private String inputType;

    private String inputGrp;

    private String arrYn;

	public String getCndRuleCd() {
		return cndRuleCd;
	}

	public void setCndRuleCd(String cndRuleCd) {
		this.cndRuleCd = cndRuleCd;
	}

	public Integer getCndRuleInSeq() {
		return cndRuleInSeq;
	}

	public void setCndRuleInSeq(Integer cndRuleInSeq) {
		this.cndRuleInSeq = cndRuleInSeq;
	}

	public String getParamKnm() {
		return paramKnm;
	}

	public void setParamKnm(String paramKnm) {
		this.paramKnm = paramKnm;
	}

	public String getParamEnm() {
		return paramEnm;
	}

	public void setParamEnm(String paramEnm) {
		this.paramEnm = paramEnm;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getInputGrp() {
		return inputGrp;
	}

	public void setInputGrp(String inputGrp) {
		this.inputGrp = inputGrp;
	}

	public String getArrYn() {
		return arrYn;
	}

	public void setArrYn(String arrYn) {
		this.arrYn = arrYn;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
