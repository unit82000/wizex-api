package com.wisenc.wizex.api.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wisenc.wizex.api.exam.service.ResponseTestService;
import com.wisenc.wizex.api.exam.vo.ProductsVO;
import com.wisenc.wizex.api.exam.vo.ResponseTestVO;
import com.wisenc.wizex.api.exam.vo.UserVO;
import com.wisenc.wizex.api.exam.vo.UsersVO;
import com.wisenc.wizex.framework.vo.ResponseVO;
import com.wisenc.wizex.mapper.wizexapi.WizexApiExamMapper;
import com.wisenc.wizex.mapper.wizexpom.WizexPmExamMapper;

@Service
public class ResponseTestServiceImpl implements ResponseTestService {

	@Resource
	private WizexApiExamMapper	wizexApiExamMapper;

	@Resource
	private WizexPmExamMapper	wizexPmExamMapper;

	@Override
	public ResponseTestVO get() {
		return new ResponseTestVO("김길동", 30);
	}

	@Override
	public ResponseVO products() {
		ProductsVO	products	= new ProductsVO();

		products.setProducts(wizexApiExamMapper.list());

		return products;
	}

	@Override
	@Cacheable(value = "wizexApiCache")
	public ResponseVO users() {
		UsersVO	users	= new UsersVO();
		List<UserVO> lstUV = wizexPmExamMapper.list();
		users.setUsers(lstUV);

		return users;
	}

}
