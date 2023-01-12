package com.wisenc.wizex.api.cndrule.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Class Name : CndRuleVO.java
 * Description : 조건규칙 설정 정보.
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
public class CndRuleVO {

    private String cndRuleCd;

    private String cndRuleNm;

    private String describe;

    private String aplyStDt;

    private String aplyEdDt;

    private String cndRuleGrp;

    private String cndRuleScript;

    /** 파라미터정보 */
    private List<SvcCndRuleInVO>		svcCndRuleInVOs;

    /** 응답정보 */
    private List<SvcCndRuleOutVO>	svcCndRuleOutVOs;

	public String getCndRuleCd() {
		return cndRuleCd;
	}

	public void setCndRuleCd(String cndRuleCd) {
		this.cndRuleCd = cndRuleCd;
	}

	public String getCndRuleNm() {
		return cndRuleNm;
	}

	public void setCndRuleNm(String cndRuleNm) {
		this.cndRuleNm = cndRuleNm;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAplyStDt() {
		return aplyStDt;
	}

	public void setAplyStDt(String aplyStDt) {
		this.aplyStDt = aplyStDt;
	}

	public String getAplyEdDt() {
		return aplyEdDt;
	}

	public void setAplyEdDt(String aplyEdDt) {
		this.aplyEdDt = aplyEdDt;
	}

	public String getCndRuleGrp() {
		return cndRuleGrp;
	}

	public void setCndRuleGrp(String cndRuleGrp) {
		this.cndRuleGrp = cndRuleGrp;
	}

	public String getCndRuleScript() {
		return cndRuleScript;
	}

	public void setCndRuleScript(String cndRuleScript) {
		this.cndRuleScript = cndRuleScript;
	}

	public List<SvcCndRuleInVO> getSvcCndRuleInVOs() {
		return svcCndRuleInVOs;
	}

	public void setSvcCndRuleInVOs(List<SvcCndRuleInVO> svcCndRuleInVOs) {
		this.svcCndRuleInVOs = svcCndRuleInVOs;
	}

	public List<SvcCndRuleOutVO> getSvcCndRuleOutVOs() {
		return svcCndRuleOutVOs;
	}

	public void setSvcCndRuleOutVOs(List<SvcCndRuleOutVO> svcCndRuleOutVOs) {
		this.svcCndRuleOutVOs = svcCndRuleOutVOs;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
