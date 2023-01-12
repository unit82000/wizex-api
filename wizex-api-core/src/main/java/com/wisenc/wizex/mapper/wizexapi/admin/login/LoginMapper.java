package com.wisenc.wizex.mapper.wizexapi.admin.login;

import com.wisenc.wizex.api.admin.user.service.UserVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface LoginMapper {

	/**
	 * 사용자 정보 조회
	 * @param userVO
	 * @return
	 */
	UserVO get(UserVO userVO);

}
