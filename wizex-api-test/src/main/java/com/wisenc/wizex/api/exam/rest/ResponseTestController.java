package com.wisenc.wizex.api.exam.rest;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisenc.wizex.api.exam.service.ResponseTestService;
import com.wisenc.wizex.framework.vo.ResponseVO;

@RestController
public class ResponseTestController {
	@Resource
	private ResponseTestService		responseTestService;

	@RequestMapping(value = "/api/users.jsonapi", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseVO usersJson() {
		ResponseVO	users	= this.responseTestService.users();

		return users;
	}

	@RequestMapping(value = "/api/users.xmlapi", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseVO usersXml() {
		ResponseVO	users	= this.responseTestService.users();

		return users;
	}

	@RequestMapping(value = "/api/products.jsonapi", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseVO productsJson() {
		ResponseVO	products	= this.responseTestService.products();

		return products;
	}

	@RequestMapping(value = "/api/products.xmlapi", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseVO productsXml() {
		ResponseVO	products	= this.responseTestService.products();

		return products;
	}

}
