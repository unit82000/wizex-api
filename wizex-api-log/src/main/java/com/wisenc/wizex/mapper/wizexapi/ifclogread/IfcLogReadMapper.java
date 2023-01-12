package com.wisenc.wizex.mapper.wizexapi.ifclogread;

import java.util.List;

import com.wisenc.wizex.api.ifclogread.service.IfcLogReadVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
/**
 * @Class Name : IfcLogReadMapper.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 28.   		유지우			최초생성
 *
 * @author 유지우
 * @since 2022. 10. 28.
 * @version 1.0
 * @see
 */

@Mapper
public interface IfcLogReadMapper {

	/**
	 * 인터페이스 로그 목록 조회.
	 * @param ifcLogReadVO
	 * @return
	 */
	List<IfcLogReadVO> getIfcLogList(IfcLogReadVO ifcLogReadVO);

}
