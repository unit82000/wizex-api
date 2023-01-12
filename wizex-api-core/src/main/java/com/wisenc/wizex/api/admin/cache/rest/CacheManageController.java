package com.wisenc.wizex.api.admin.cache.rest;

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

import com.wisenc.wizex.api.admin.cache.service.CacheManageService;

/**
 * Class Name : CacheManageController.java
 * Description : API 요청/응답을 위한 cache 기능을 관리.
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
public class CacheManageController {

	@Resource
	private CacheManageService cacheManageService;

	/**
	 * Cache를 초기화.
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/cache/clearCache.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> clearCache(HttpServletRequest req) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();

		String name = req.getParameter("name");
		this.cacheManageService.clearCache(name);

		result.put("result", true);
		return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
	}

}
