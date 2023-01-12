package com.wisenc.wizex.mapper.wizexapi;

import java.util.List;

import com.wisenc.wizex.api.exam.vo.ProductVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface WizexApiExamMapper {

	List<ProductVO> list();
}
