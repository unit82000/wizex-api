package com.wisenc.wizex.api.cndrule.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisenc.wizex.api.cndrule.service.CndRuleService;
import com.wisenc.wizex.api.cndrule.vo.CndRuleParamVO;
import com.wisenc.wizex.framework.vo.ResponseVO;

/**
 * Class Name : CndRuleController.java
 * Description : 조건규칙 서비스.
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
@Controller
public class CndRuleController {

	@Resource
	private CndRuleService		cndRuleService;

	/**
	 * 조건규칙 수행. JSON 형태로 반환.
	 *
	 * @param req
	 * @param cndRuleCd
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value = "/api/{cndRuleCd}.jsonapi", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseVO cndRuleJson(HttpServletRequest req, @PathVariable String cndRuleCd, CndRuleParamVO paramVO) {
		paramVO.setCndRuleCd(cndRuleCd);
		paramVO.setAllParams(req.getParameterMap());

		ResponseVO	ruleResult	= this.cndRuleService.exec(paramVO);

		return ruleResult;
	}

	/**
	 * 조건규칙 수행. XML 형태로 반환.
	 *
	 * @param req
	 * @param cndRuleCd
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value = "/api/{cndRuleCd}.xmlapi", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseVO cndRuleXml(HttpServletRequest req, @PathVariable String cndRuleCd, CndRuleParamVO paramVO) {
		paramVO.setCndRuleCd(cndRuleCd);
		paramVO.setAllParams(req.getParameterMap());

		ResponseVO	ruleResult	= this.cndRuleService.exec(paramVO);

		return ruleResult;
	}

}
