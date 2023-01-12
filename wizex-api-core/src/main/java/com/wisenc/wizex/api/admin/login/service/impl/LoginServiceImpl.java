package com.wisenc.wizex.api.admin.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wisenc.wizex.api.admin.login.service.LoginService;
import com.wisenc.wizex.api.admin.menu.service.MenuVO;
import com.wisenc.wizex.api.admin.user.service.UserVO;
import com.wisenc.wizex.framework.exception.WizexException;
import com.wisenc.wizex.framework.util.SessionUtil;
import com.wisenc.wizex.mapper.wizexapi.admin.login.LoginMapper;
import com.wisenc.wizex.mapper.wizexapi.admin.menu.MenuMapper;

/**
 * @Class Name : LoginServiceImpl.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 26.   		유지우			최초생성
 *
 * @author 유지우
 * @since 2022. 10. 26.
 * @version 1.0
 * @see
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private LoginMapper loginMapper;

	@Resource
	private MenuMapper menuMapper;

	private static final Logger Logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public String login(UserVO userVO) {

		// 사용자 정보 조회
		UserVO  validUser   = this.loginMapper.get(userVO);

		if (validUser == null) // 사용자 정보 없음.
			throw new WizexException("wizex.admin.login.invalid");

		if(Logger.isDebugEnabled()) {
			Logger.debug("###### LOGIN SUCCESS ######");
			Logger.debug("{}", validUser);
		}

		MenuVO menuSearchVO = new MenuVO();
		menuSearchVO.setUperMenuId(null);

		List<MenuVO> menus = menuMapper.list(menuSearchVO);

		for(MenuVO menu : menus) {
			if(menu.getChildCount() > 0) {
				listMenus(menu, menuSearchVO);
			}
		}
		SessionUtil.setUser(validUser);
		SessionUtil.setMenus(menus);

		return "/main.wizex";
	}

	/**
	 * 하위 메뉴 목록 조회
	 * @param menuVO
	 */
	private void listMenus(MenuVO menuVO, MenuVO menuSearchVO) {
		menuSearchVO.setUperMenuId(menuVO.getMenuId());

		List<MenuVO> menus = menuMapper.list(menuSearchVO);
		menuVO.setChildren(menus);

		for(MenuVO	menu : menus) {
			if(menu.getChildCount() > 0) {
				this.listMenus(menu, menuSearchVO);
			}
		}
	}

}
