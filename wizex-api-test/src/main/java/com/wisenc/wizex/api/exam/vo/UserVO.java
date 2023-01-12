package com.wisenc.wizex.api.exam.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class UserVO implements Serializable {
	private static final long serialVersionUID = 4953895770063740753L;

	private String userId;

	private String userNm;

	private String pwd;

	private String auth;

	private Date modiDt;

	private String modiUid;

	@XmlElement
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement
	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	@XmlElement
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@XmlElement
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@XmlElement
	public Date getModiDt() {
		return modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	@XmlElement
	public String getModiUid() {
		return modiUid;
	}

	public void setModiUid(String modiUid) {
		this.modiUid = modiUid;
	}
}
