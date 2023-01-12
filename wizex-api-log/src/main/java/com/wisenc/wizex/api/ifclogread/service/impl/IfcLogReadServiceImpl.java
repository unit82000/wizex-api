package com.wisenc.wizex.api.ifclogread.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wisenc.wizex.api.ifclogread.service.IfcLogReadService;
import com.wisenc.wizex.api.ifclogread.service.IfcLogReadVO;
import com.wisenc.wizex.mapper.wizexapi.ifclogread.IfcLogReadMapper;
/**
 * @Class Name : IfcLogReadServiceImpl.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 28.  유지우			최초생성
 *
 * @author 유지우
 * @since 2022. 10. 28.
 * @version 1.0
 * @see
 */

@Service
public class IfcLogReadServiceImpl implements IfcLogReadService{

	@Resource
	private IfcLogReadMapper ifcLogReadMapper;

	private static final Logger Logger =  LoggerFactory.getLogger(IfcLogReadServiceImpl.class);

	@Override
	public List<IfcLogReadVO> getIfcLogList(IfcLogReadVO ifcLogReadVO) {
		List<IfcLogReadVO> result = this.ifcLogReadMapper.getIfcLogList(ifcLogReadVO);
		return result;
	}
}
