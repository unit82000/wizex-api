package com.wisenc.wizex.mapper.wizexapi.ifclog;

import com.wisenc.wizex.api.ifclog.vo.IfcLogVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface IfcLogMapper {

	/**
	 * 인터페이스 로그 등록.
	 *
	 * @param ifcLogVO
	 * @return
	 */
	int insert(IfcLogVO ifcLogVO);
}
