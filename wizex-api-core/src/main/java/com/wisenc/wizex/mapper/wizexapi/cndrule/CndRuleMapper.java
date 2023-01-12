package com.wisenc.wizex.mapper.wizexapi.cndrule;

import java.util.List;

import com.wisenc.wizex.api.cndrule.vo.CndRuleParamVO;
import com.wisenc.wizex.api.cndrule.vo.CndRuleVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * Class Name : CndRuleMapper.java
 * Description : 조건규칙 관리.
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
@Mapper
public interface CndRuleMapper {
	/**
	 * 조건규칙 전체목록 조회.
	 *
	 * @param cndRuleParamVO
	 * @return
	 */
	List<CndRuleVO> listCndRule(CndRuleParamVO cndRuleParamVO);
}
