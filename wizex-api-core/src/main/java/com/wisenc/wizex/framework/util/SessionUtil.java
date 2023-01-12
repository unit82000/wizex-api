package com.wisenc.wizex.framework.util;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisenc.wizex.api.admin.menu.service.MenuVO;
import com.wisenc.wizex.api.admin.user.service.UserVO;

/**
 * @Class Name : SessionUtil.java
 * @Description : 세션에 접근하기 위한 편의 장치.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2020. 1. 7.   		최초생성
 *
 * @author 김경석
 * @since 2020. 1. 7.
 * @version 1.0
 * @see
 */
public class SessionUtil implements Serializable{

	//------- 직렬화 추가 2021.12.09 양준모
	private static final long serialVersionUID = 1L;
	public static final String	USER_KEY		= "_user_key_";
	public static final String	MENUS_KEY		= "_menus_key_";
	public static final String	TIME_KEY		= "_time_key_";
	public static final String	APP_INFO_KEY	= "_app_info_key_";
	public static final String	CODE_KEY		= "_code_key_";

	/**
	 * get request.
	 *
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		try {
			return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * get response.
	 *
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		try {
			return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * get session.
	 *
	 * @return
	 */
	public static HttpSession getSession() {
		try {
			HttpSession session = getRequest().getSession();
			session.setMaxInactiveInterval(-1); // session time 제한없음

			return session;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * set user.
	 *
	 * @param userVO
	 */
	public static void setUser(UserVO userVO) {
		getSession().setAttribute(USER_KEY, userVO);
	}

	/**
	 * get user.
	 *
	 * @return
	 */
	public static UserVO getUser() {
		return (UserVO)getSession().getAttribute(USER_KEY);
	}

	/**
	 * set menus.
	 * @param menus
	 */
	public static void setMenus(List<MenuVO> menus) {
		getSession().setAttribute(MENUS_KEY, menus);
	}

	/**
	 * get menus.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MenuVO> getMenus(){
		return (List<MenuVO>)getSession().getAttribute(MENUS_KEY);
	}

	/**
	 * session invalidate.
	 */
	public static void invalidate() {
		getSession().invalidate();
	}
}
