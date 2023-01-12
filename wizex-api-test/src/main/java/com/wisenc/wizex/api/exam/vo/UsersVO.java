package com.wisenc.wizex.api.exam.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wisenc.wizex.framework.vo.ResponseVO;

@XmlRootElement(name = "response")
public class UsersVO extends ResponseVO implements Serializable {
	private static final long serialVersionUID = -6944682187200853173L;

	private List<UserVO> users;

	@XmlElement(name = "user")
	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

}
