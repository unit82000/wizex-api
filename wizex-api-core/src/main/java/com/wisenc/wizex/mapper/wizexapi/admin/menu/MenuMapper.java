package com.wisenc.wizex.mapper.wizexapi.admin.menu;

import java.util.List;

import com.wisenc.wizex.api.admin.menu.service.MenuVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @Class Name : MenuMapper.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 27.   유지우			최초생성
 *
 * @author 유지우
 * @since 2022. 10. 27.
 * @version 1.0
 * @see
 */
@Mapper
public interface MenuMapper {

	/**
	 * 목록조회
	 * @param menuVO
	 * @return
	 */
	List<MenuVO> list(MenuVO menuSearchVO);

}
