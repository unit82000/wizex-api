package com.wisenc.wizex.api.admin.cndrule.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisenc.wizex.api.cndrule.service.CndRuleService;

/**
 * Class Name : CndRuleManageController.java
 * Description : 조건규칙 관리.
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
public class CndRuleManageController {

	@Resource
	private CndRuleService		cndRuleService;

	/**
	 * 조건규칙 정보를 다시 로딩.
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/cndrule/clearCndRule.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> clearCndRule(HttpServletRequest req) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();

		this.cndRuleService.init();

		result.put("result", true);
		return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
	}

}
