package com.wisenc.wizex.api.ifclogread.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisenc.wizex.api.ifclogread.service.IfcLogReadService;
import com.wisenc.wizex.api.ifclogread.service.IfcLogReadVO;

@Controller
public class IfcLogReadController {

	@Resource
	private IfcLogReadService ifcLogReadService;

	@RequestMapping(value = "/admin/ifcLogRead/goIfcLogRead.wizex", method = { RequestMethod.GET, RequestMethod.POST })
	public String goIfcLogRead() {
		return "admin/ifclogread/ifcLogRead";
	}

	@RequestMapping(value = "/admin/ifcLogRead/getIfcLogList.wizex", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getIfcLogList(IfcLogReadVO ifcLogReadVO){

		List<IfcLogReadVO> list = this.ifcLogReadService.getIfcLogList(ifcLogReadVO);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result",	list);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}
