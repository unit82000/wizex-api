package com.wisenc.wizex.mapper.wizexpom;

import java.util.List;

import com.wisenc.wizex.api.exam.vo.UserVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface WizexPmExamMapper {

	List<UserVO> list();
}
