package com.wisenc.wizex.api.admin.user.service;

import com.wisenc.wizex.framework.vo.FieldPrinter;

/**
 * @Class Name : UserVO.java
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
public class UserVO extends FieldPrinter {

	/** 사용자 아이디 */
	private String userId;

	/** 사용자명 */
	private String userNm;

	/** 비밀번호 */
	private String pwd;

	/** 권한 */
	private String auth;

	/** 정렬순서 */
	private Integer ord;

	/** 변경일자 */
	private String modiDt;

	/** 변경자 */
	private String modiUid;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getModiDt() {
		return modiDt;
	}

	public void setModiDt(String modiDt) {
		this.modiDt = modiDt;
	}

	public String getModiUid() {
		return modiUid;
	}

	public void setModiUid(String modiUid) {
		this.modiUid = modiUid;
	}

}
