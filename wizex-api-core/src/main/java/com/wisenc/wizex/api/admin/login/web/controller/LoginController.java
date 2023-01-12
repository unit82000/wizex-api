package com.wisenc.wizex.api.admin.login.web.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisenc.wizex.api.admin.login.service.LoginService;
import com.wisenc.wizex.api.admin.user.service.UserVO;
import com.wisenc.wizex.framework.util.SessionUtil;
import com.wisenc.wizex.framework.vo.VoidVO;

/**
 * @Class Name : LoginController.java
 * @Description : 로그인.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 21.   		최초생성
 *
 * @author 양준모
 * @since 2022. 10. 21.
 * @version 1.0
 * @see
 */
@Controller
public class LoginController {

	@Resource
	private LoginService loginService;

	/**
	 * 로그인 페이지로 이동.
	 *
	 * @return
	 */
	@RequestMapping(value = "/login/form.wizex", method = { RequestMethod.GET, RequestMethod.POST })
	public String goLogin() {
		return "login/form";
	}

	/**
	 * 로그인
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value = "/login/login.wizex", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<VoidVO> login(UserVO userVO) {
		String url = this.loginService.login(userVO);

		VoidVO	voidVO	= new VoidVO(url);

		return new ResponseEntity<VoidVO>(voidVO, HttpStatus.OK);
	}

	/**
	 * 메인 페이지로 이동.
	 *
	 * @param userVO
	 * @return
	 *
	 */
	@RequestMapping(value = "/main.wizex", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(UserVO userVO) {
		boolean isSession = (SessionUtil.getUser() == null ? false : true);

		if(!isSession) {
			SessionUtil.invalidate();
			return "redirect:/login/form.wizex";
		}
		return "main";
	}

	/**
	 * 로그아웃.
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value = "/login/logout.wizex", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		SessionUtil.invalidate();
		return "redirect:/login/form.wizex";
	}
}
