package com.wisenc.wizex.api.admin.login.service;

import com.wisenc.wizex.api.admin.user.service.UserVO;

/**
 * @Class Name : LoginService.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 24.   유지우		최초생성
 *
 * @author 유지우
 * @since 2022. 10. 24.
 * @version 1.0
 * @see
 */
public interface LoginService {

	/**
	 * 로그인
	 * @param userVO
	 *
	 */
	String login(UserVO userVO);

}